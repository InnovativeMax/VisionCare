package com.visioncare.controller;

import com.visioncare.common.ProductCategories;
import com.visioncare.model.Product;
import com.visioncare.service.ProductService;
import com.visioncare.validator.ProductValidator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final ProductService productService =
            new ProductService();

    private final ProductValidator productValidator =
            new ProductValidator();

    private static final int PAGE_SIZE = 10;

    /*
    ==========================================================
    GET Requests
    ==========================================================
    */

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.isBlank()) {
            action = "list";
        }
        switch (action) {

            case "new":
                showProductForm(request, response);
                break;

            case "view":
                showProductView(request, response);
                break;

            case "edit":
                showEditForm(request, response);
                break;

            case "deactivate":
                deactivateProduct(request, response);
                break;

            default:
                showProductList(request, response);
                break;
        }
    }

    /*
    ==========================================================
    POST Requests
    ==========================================================
    */

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || action.isBlank()) {
            saveProduct(request, response);
            return;
        }

        switch (action) {

            case "update":
                updateProduct(request, response);
                break;

            default:
                saveProduct(request, response);
                break;
        }

    }

    /*
    ==========================================================
    Product List
    ==========================================================
    */

    private void showProductList(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        String search = request.getParameter("search");
        String status = request.getParameter("status");
        System.out.println("=================================");
        System.out.println("SEARCH = " + search);
        System.out.println("STATUS = " + status);
        System.out.println("=================================");

        if (search == null) {
            search = "";
        }

        if (status == null || status.isBlank()) {
            status = "active";
        }

        List<Product> products;

        if (search.isBlank()) {

            products = productService.getProductsByStatus(status);

        } else {

            products = productService.searchProducts(
                    search,
                    status
            );

        }

        request.setAttribute("products", products);

        request.setAttribute("search", search);
        request.setAttribute("status", status);

        request.setAttribute("activeMenu", "products");
        request.setAttribute("activeSection", "masters");

        request.getRequestDispatcher("/product/list.jsp")
                .forward(request, response);

    }

    /*
    ==========================================================
    Product Form
    ==========================================================
    */

    private void showProductForm(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();

        product.setProductCode(
                productService.generateProductCode()
        );

        request.setAttribute("product", product);

        request.setAttribute(
                "categories",
                ProductCategories.ALL
        );

        request.setAttribute("activeMenu", "products");
        request.setAttribute("activeSection", "masters");

        request.getRequestDispatcher("/product/form.jsp")
                .forward(request, response);
    }

    private void showProductView(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        Product product = productService.getProductById(id);

        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        request.setAttribute("product", product);

        request.setAttribute("activeMenu", "products");
        request.setAttribute("activeSection", "masters");

        request.getRequestDispatcher("/product/view.jsp")
                .forward(request, response);

    }

    private void showEditForm(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.valueOf(request.getParameter("id"));

        Product product = productService.getProductById(id);

        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/products");
            return;
        }

        request.setAttribute("product", product);

        request.setAttribute("categories", ProductCategories.ALL);

        request.setAttribute("activeMenu", "products");
        request.setAttribute("activeSection", "masters");

        request.getRequestDispatcher("/product/form.jsp")
                .forward(request, response);

    }

    private void deactivateProduct(HttpServletRequest request,
                                   HttpServletResponse response)
            throws IOException {

        Long id = Long.valueOf(request.getParameter("id"));

        productService.deactivateProduct(id);

        response.sendRedirect(
                request.getContextPath() +
                        "/products?success=deactivated");

    }

    /*
    ==========================================================
    Save Product
    ==========================================================
    */

    private void saveProduct(HttpServletRequest request,
                             HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();

        product.setProductCode(
                request.getParameter("productCode")
        );

        product.setProductName(
                request.getParameter("productName")
        );

        product.setCategory(
                request.getParameter("category")
        );

        product.setBrand(
                request.getParameter("brand")
        );

        product.setCostPrice(
                new BigDecimal(request.getParameter("costPrice"))
        );

        product.setSellingPrice(
                new BigDecimal(request.getParameter("sellingPrice"))
        );

        product.setStockQuantity(
                Integer.parseInt(
                        request.getParameter("stockQuantity")
                )
        );

        product.setReorderLevel(
                Integer.parseInt(
                        request.getParameter("reorderLevel")
                )
        );

        product.setDescription(
                request.getParameter("description")
        );

        product.setActive(
                request.getParameter("active") != null
        );

        List<String> errors =
                productValidator.validate(product);

        if (!errors.isEmpty()) {

            request.setAttribute("errors", errors);
            request.setAttribute("product", product);

            request.setAttribute(
                    "categories",
                    ProductCategories.ALL
            );

            request.setAttribute("activeMenu", "products");
            request.setAttribute("activeSection", "masters");

            request.getRequestDispatcher("/product/form.jsp")
                    .forward(request, response);

            return;
        }

        productService.saveProduct(product);

        response.sendRedirect(
                request.getContextPath()
                        + "/products?success=created"
        );
    }

    private void updateProduct(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        Product product = new Product();

        product.setId(Integer.parseInt(request.getParameter("id")));

        product.setProductCode(request.getParameter("productCode"));

        product.setProductName(request.getParameter("productName"));

        product.setCategory(request.getParameter("category"));

        product.setBrand(request.getParameter("brand"));

        product.setCostPrice(
                new BigDecimal(request.getParameter("costPrice")));

        product.setSellingPrice(
                new BigDecimal(request.getParameter("sellingPrice")));

        product.setStockQuantity(
                Integer.parseInt(request.getParameter("stockQuantity")));

        product.setReorderLevel(
                Integer.parseInt(request.getParameter("reorderLevel")));

        product.setDescription(request.getParameter("description"));

        product.setActive(request.getParameter("active") != null);

        List<String> errors = productValidator.validate(product);

        if (!errors.isEmpty()) {

            request.setAttribute("errors", errors);
            request.setAttribute("product", product);
            request.setAttribute("categories", ProductCategories.ALL);

            request.setAttribute("activeMenu", "products");
            request.setAttribute("activeSection", "masters");

            request.getRequestDispatcher("/product/form.jsp")
                    .forward(request, response);

            return;
        }

        productService.updateProduct(product);

        response.sendRedirect(
                request.getContextPath()
                        + "/products?action=view&id=" + product.getId());

    }

}