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
        System.out.println("===== PRODUCT SERVLET =====");
        String action = request.getParameter("action");

        if ("new".equals(action)) {
            showProductForm(request, response);
            return;
        }

        showProductList(request, response);
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

        saveProduct(request, response);
    }

    /*
    ==========================================================
    Product List
    ==========================================================
    */

    private void showProductList(HttpServletRequest request,
                                 HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products =
                productService.getProductsByStatus("active");

        request.setAttribute("products", products);

        // Temporary pagination values
        request.setAttribute("currentPage", 1);
        request.setAttribute("totalPages", 1);
        request.setAttribute("totalRecords", products.size());
        request.setAttribute("pageSize", PAGE_SIZE);
        request.setAttribute("paginationQuery", "");
        request.setAttribute("entityName", "product");

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

}