<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="/includes/layout/header.jsp" %>
<%@ include file="/includes/layout/navbar.jsp" %>

<div class="app-layout">

    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <!-- Page Header -->

            <section class="page-section">

                <jsp:include page="/includes/components/page-header.jsp">

                    <jsp:param
                            name="title"
                            value="New Sale"/>

                    <jsp:param
                            name="description"
                            value="Create customer sales invoice."/>

                </jsp:include>

            </section>

            <form method="post"
                  action="${pageContext.request.contextPath}/billing?action=save">

                <!-- Invoice Card -->


                <div class="card">

                    <div class="card-header">

                        <div class="card-header">

                            <div class="section-title">

                                <div class="section-icon">

                                    <i class="bi bi-receipt"></i>

                                </div>

                                <div>

                                    <h3>Invoice Information</h3>

                                    <p class="section-description">
                                        Customer and invoice details.
                                    </p>

                                </div>

                            </div>

                        </div>

                    </div>

                    <div class="card-body">

                        <div class="form-grid three-column">

                            <!-- Invoice Number -->

                            <div class="form-group">

                                <label>Invoice Number</label>

                                <input type="text"
                                       name="invoiceNumber"
                                       value="${bill.invoiceNumber}"
                                       readonly>

                            </div>

                            <!-- Date -->

                            <div class="form-group">

                                <label>Bill Date</label>

                                <input type="date"
                                       name="billDate"
                                       value="${bill.billDate}"
                                       readonly>

                            </div>

                            <!-- Customer -->

                            <div class="form-group">

                                <label>Customer <span class="required">*</span></label>

                                <select name="customerId" required>

                                    <option value="">Select Customer</option>

                                    <c:forEach items="${customers}" var="customer">

                                        <option value="${customer.id}">

                                                ${customer.customerCode}
                                            -
                                                ${customer.fullName}

                                        </option>

                                    </c:forEach>

                                </select>

                            </div>

                        </div>

                    </div>

                </div>

                </section>

                <section class="page-section">

                </section>

                <section class="page-section">

                    <div class="card">

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

                        <div class="card-body">

                            <table class="table" id="productTable">

                                <thead>

                                <tr>

                                    <th style="width:40%">Product</th>
                                    <th>Qty</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                    <th></th>

                                </tr>

                                </thead>

                                <tbody>

                                <tr>

                                    <td>

                                        <select
                                                class="productSelect"
                                                name="productId[]">

                                            <option value="">Select Product</option>

                                            <c:forEach items="${products}" var="product">

                                                <option
                                                        value="${product.id}"
                                                        data-price="${product.sellingPrice}"
                                                        data-stock="${product.stockQuantity}">

                                                        ${product.productCode}
                                                    -
                                                        ${product.productName}

                                                </option>

                                            </c:forEach>

                                        </select>

                                    </td>

                                    <td>

                                        <input type="number"
                                               name="quantity[]"
                                               class="qty"
                                               value="1"
                                               min="1">

                                    </td>

                                    <td>

                                        <input type="number"
                                               name="unitPrice[]"
                                               class="price"
                                               readonly>

                                    </td>

                                    <td>

                                        <input type="number"
                                               name="lineTotal[]"
                                               class="lineTotal"
                                               readonly>

                                    </td>

                                    <td>

                                        <button
                                                type="button"
                                                class="removeRow btn btn-outline-danger btn-sm">

                                            <i class="bi bi-trash"></i>

                                        </button>

                                    </td>

                                </tr>

                                </tbody>

                            </table>

                            <button
                                    id="addRow"
                                    type="button"
                                    class="btn btn-outline-primary">

                                <i class="bi bi-plus-circle"></i>

                                Add Product

                            </button>

                        </div>

                    </div>

                </section>

                <section class="page-section">

                </section>

                <section class="page-section">

                    <jsp:include
                            page="/includes/forms/billing-summary.jsp"/>

                </section>

                <section class="page-section">

                </section>

            </form>

        </div>

    </main>

</div>
<script>

    document.addEventListener("DOMContentLoaded", function () {

        initializeRows();

    });

    function initializeRows() {

        document
            .querySelectorAll("#productTable tbody tr")
            .forEach(bindRow);

    }

    function bindRow(row) {

        const product =
            row.querySelector(".productSelect");

        const qty =
            row.querySelector(".qty");

        product.addEventListener(
            "change",
            function () {

                updateRow(row);

            }
        );

        qty.addEventListener(
            "input",
            function () {

                updateRow(row);

            }
        );

    }

    function updateRow(row) {

        const product =
            row.querySelector(".productSelect");

        const qty =
            row.querySelector(".qty");

        const price =
            row.querySelector(".price");

        const total =
            row.querySelector(".lineTotal");

        const unitPrice =
            Number(
                product.selectedOptions[0]
                    ?.dataset.price || 0
            );

        price.value =
            unitPrice.toFixed(2);

        total.value =
            (
                unitPrice *
                Number(qty.value)
            ).toFixed(2);

    }

</script>

<script src="${pageContext.request.contextPath}/assets/js/pages/billing.js"></script>

<%@ include file="/includes/layout/footer.jsp" %>