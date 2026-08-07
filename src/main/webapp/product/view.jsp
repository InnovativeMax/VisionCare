<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/includes/layout/header.jsp" %>
<%@ include file="/includes/layout/navbar.jsp" %>

<div class="app-layout">

    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <jsp:include page="/includes/components/page-header.jsp">

                <jsp:param
                        name="title"
                        value="Product Details"/>

                <jsp:param
                        name="description"
                        value="View product information."/>

            </jsp:include>

            <div class="card">

                <div class="card-header">

                    <h2 class="card-title">

                        ${product.productName}

                    </h2>

                    <span class="badge ${product.active ? 'badge-success' : 'badge-danger'}">

                        ${product.active ? 'Active' : 'Inactive'}

                    </span>

                </div>

                <div class="card-body">

                    <!-- Product Information -->

                    <div class="form-section">

                        <h3 class="form-section-title">

                            Product Information

                        </h3>

                        <div class="form-grid">

                            <div class="form-group">

                                <label class="form-label">

                                    Product Code

                                </label>

                                <div class="detail-value">

                                    ${product.productCode}

                                </div>

                            </div>

                            <div class="form-group">

                                <label class="form-label">

                                    Product Name

                                </label>

                                <div class="detail-value">

                                    ${product.productName}

                                </div>

                            </div>

                            <div class="form-group">

                                <label class="form-label">

                                    Category

                                </label>

                                <div class="detail-value">

                                    ${product.category}

                                </div>

                            </div>

                            <div class="form-group">

                                <label class="form-label">

                                    Brand

                                </label>

                                <div class="detail-value">

                                    ${product.brand}

                                </div>

                            </div>

                        </div>

                    </div>

                    <!-- Pricing -->

                    <div class="form-section">

                        <h3 class="form-section-title">

                            Pricing

                        </h3>

                        <div class="form-grid">

                            <div class="form-group">

                                <label class="form-label">

                                    Cost Price

                                </label>

                                <div class="detail-value">

                                    Rs. ${product.costPrice}

                                </div>

                            </div>

                            <div class="form-group">

                                <label class="form-label">

                                    Selling Price

                                </label>

                                <div class="detail-value">

                                    Rs. ${product.sellingPrice}

                                </div>

                            </div>

                        </div>

                    </div>

                    <!-- Inventory -->

                    <div class="form-section">

                        <h3 class="form-section-title">

                            Inventory

                        </h3>

                        <div class="form-grid">

                            <div class="form-group">

                                <label class="form-label">

                                    Current Stock

                                </label>

                                <div class="detail-value">

                                    ${product.stockQuantity}

                                </div>

                            </div>

                            <div class="form-group">

                                <label class="form-label">

                                    Reorder Level

                                </label>

                                <div class="detail-value">

                                    ${product.reorderLevel}

                                </div>

                            </div>

                        </div>

                    </div>

                    <!-- Description -->

                    <div class="form-section">

                        <h3 class="form-section-title">

                            Description

                        </h3>

                        <div class="detail-value">

                            ${empty product.description ? 'No description available.' : product.description}

                        </div>

                    </div>

                </div>

                <div class="card-footer">

                    <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}"
                       class="btn btn-primary">

                        <i class="bi bi-pencil-square"></i>

                        Edit Product

                    </a>

                    <a href="${pageContext.request.contextPath}/products"
                       class="btn btn-outline">

                        <i class="bi bi-arrow-left"></i>

                        Back

                    </a>

                </div>

            </div>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>