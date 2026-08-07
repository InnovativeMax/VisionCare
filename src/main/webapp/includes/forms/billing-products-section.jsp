<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<section class="page-section">

    <div class="card">

        <!-- ===================================================== -->
        <!-- Card Header -->
        <!-- ===================================================== -->

        <div class="card-header">

            <div class="section-title">

                <div class="section-icon">

                    <i class="bi bi-box-seam"></i>

                </div>

                <div>

                    <h3>Products</h3>

                    <p class="section-description">

                        Add one or more products to this invoice.

                    </p>

                </div>

            </div>

        </div>

        <!-- ===================================================== -->
        <!-- Card Body -->
        <!-- ===================================================== -->

        <div class="card-body">

            <div class="product-table-wrapper">

                <table
                        id="productTable"
                        class="table product-table">

                    <thead>

                    <tr>

                        <th style="width:42%">

                            Product

                        </th>

                        <th style="width:12%">

                            Qty

                        </th>

                        <th style="width:16%">

                            Unit Price

                        </th>

                        <th style="width:16%">

                            Total

                        </th>

                        <th style="width:14%">

                            Action

                        </th>

                    </tr>

                    </thead>

                    <tbody>

                    <tr>

                        <!-- ====================================== -->
                        <!-- Product -->
                        <!-- ====================================== -->

                        <td>

                            <select
                                    class="form-control productSelect"
                                    name="productId[]"
                                    required>

                                <option value="">

                                    Select Product

                                </option>

                                <c:forEach
                                        items="${products}"
                                        var="product">

                                    <option

                                            value="${product.id}"
                                            data-price="${product.sellingPrice}"
                                            data-stock="${product.stockQuantity}">
                                            ${product.productCode} - ${product.productName}

                                    </option>

                                </c:forEach>

                            </select>

                        </td>

                        <!-- ====================================== -->
                        <!-- Quantity -->
                        <!-- ====================================== -->

                        <td>

                            <input
                                    class="form-control qty"
                                    type="number"
                                    name="quantity[]"
                                    value="1"
                                    min="1"
                                    required>

                        </td>

                        <!-- ====================================== -->
                        <!-- Unit Price -->
                        <!-- ====================================== -->

                        <td>

                            <input
                                    class="form-control price"
                                    type="text"
                                    name="unitPrice[]"
                                    readonly>

                        </td>

                        <!-- ====================================== -->
                        <!-- Line Total -->
                        <!-- ====================================== -->

                        <td>

                            <input
                                    class="form-control lineTotal"
                                    type="text"
                                    name="lineTotal[]"
                                    readonly>

                        </td>

                        <!-- ====================================== -->
                        <!-- Action -->
                        <!-- ====================================== -->

                        <td class="text-center">

                            <button
                                    type="button"
                                    class="removeRow btn btn-outline-danger btn-sm">

                                <i class="bi bi-trash"></i>

                            </button>

                        </td>

                    </tr>

                    </tbody>

                </table>

                <!-- ===================================================== -->
                <!-- Add Product -->
                <!-- ===================================================== -->

                <div class="card-footer d-flex justify-content-center">

                    <button
                            id="addRow"
                            type="button"
                            class="btn btn-outline-primary">

                        <i class="bi bi-plus-circle"></i>

                        Add Product

                    </button>

                </div>

            </div>

        </div>

    </div>

</section>