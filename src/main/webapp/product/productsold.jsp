<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.visioncare.model.Product" %>

<%
    List<Product> products =
            (List<Product>) request.getAttribute("products");
%>

<%@ include file="/includes/layout/header.jsp" %>

<%@ include file="/includes/layout/navbar.jsp" %>

<div class="app-layout">

    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <!-- ======================================================
                 Page Header
            ======================================================= -->

            <div class="page-header">

                <div class="page-header-content">

                    <h1 class="page-title">

                        <i class="bi bi-box-seam"></i>

                        Products

                    </h1>

                    <p class="page-description">

                        Organize your product catalog, pricing and inventory.

                    </p>

                </div>

                <button
                        class="btn btn-primary"
                        data-href="${pageContext.request.contextPath}/product/product-form.jsp">

                    <i class="bi bi-plus-lg"></i>

                    Add Product

                </button>

            </div>

            <!-- ======================================================
                Customer Filter
            ======================================================= -->

            <div class="filter-bar">

                <form
                        method="get"
                        action="${pageContext.request.contextPath}/products"
                        class="filter-form">

                    <div class="filter-status">

            <span class="filter-title">

                Status

            </span>

                        <label class="filter-option">

                            <input
                                    type="radio"
                                    name="status"
                                    value="active"
                                <%= "active".equals(request.getAttribute("status")) ? "checked" : "" %>>

                            <span>Active</span>

                        </label>

                        <label class="filter-option">

                            <input
                                    type="radio"
                                    name="status"
                                    value="inactive"
                                <%= "inactive".equals(request.getAttribute("status")) ? "checked" : "" %>>

                            <span>Inactive</span>

                        </label>

                        <label class="filter-option">

                            <input
                                    type="radio"
                                    name="status"
                                    value="all"
                                <%= "all".equals(request.getAttribute("status")) ? "checked" : "" %>>

                            <span>Show All</span>

                        </label>

                    </div>

                    <div class="filter-search">

                        <i class="bi bi-search"></i>

                        <input
                                type="text"
                                name="keyword"
                                class="form-control"
                                placeholder="Search by product code, product name or brand..."
                                value="<%= request.getAttribute("keyword") == null
                            ? ""
                            : request.getAttribute("keyword") %>">

                    </div>

                    <button
                            type="submit"
                            class="btn btn-primary">

                        <i class="bi bi-search"></i>

                        Search

                    </button>

                </form>

            </div>

            <!-- ======================================================
                 Customer Table
            ======================================================= -->

            <div class="card">

                <div class="card-body">

                    <table class="table">

                        <thead>

                        <tr>

                            <th>Product Code</th>

                            <th>Product Name</th>

                            <th>Category</th>

                            <th>Brand</th>

                            <th>Selling Price</th>

                            <th>Stock</th>

                            <th>Status</th>

                            <th class="text-center">Actions</th>

                        </tr>

                        </thead>

                        <tbody>

                        <%
                            if (products == null || products.isEmpty()) {
                        %>

                        <tr>

                            <td colspan="8" class="text-center">

                                No products found.

                            </td>

                        </tr>

                        <%

                        } else {

                            for (Product product : products) {

                        %>

                        <tr>

                            <td>

                                <a href="${pageContext.request.contextPath}/products/view?id=<%= product.getId() %>"
                                   class="table-link">

                                    <%= product.getProductCode() %>

                                </a>

                            </td>

                            <td>

                                <%= product.getProductName() %>

                            </td>

                            <td>

                                <%= product.getCategory() %>

                            </td>

                            <td>

                                <%= product.getBrand() %>

                            </td>

                            <td>

                                Rs. <%= String.format("%,.2f", product.getSellingPrice()) %>

                            </td>

                            <td>

                                <%= product.getStockQuantity() %>

                            </td>

                            <td>

                                <span class="badge <%= product.isActive()
                                    ? "badge-success"
                                    : "badge-secondary" %>">

                                    <%= product.isActive()
                                            ? "Active"
                                            : "Inactive" %>

                                </span>

                            </td>

                            <td class="text-center">

                                <a href="${pageContext.request.contextPath}/products/edit?id=<%= product.getId() %>"
                                   class="action-btn action-edit"
                                   title="Edit Product">

                                    <i class="bi bi-pencil-square"></i>

                                </a>

                                <button
                                        type="button"
                                        class="action-btn action-delete"
                                        title="Deactivate Product"
                                        onclick="showConfirmation(
                                                'Deactivate Product',
                                                'Are you sure you want to deactivate this product?',
                                                '${pageContext.request.contextPath}/products/delete',
                                                '<%= product.getId() %>')">

                                    <i class="bi bi-trash"></i>

                                </button>

                            </td>

                        </tr>

                        <%

                                }

                            }

                        %>

                        </tbody>

                    </table>

                </div>

            </div>

            <jsp:include page="/includes/components/pagination.jsp"/>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>