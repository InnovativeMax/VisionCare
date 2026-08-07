<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/includes/layout/header.jsp" %>

<div class="app-layout">

    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <section class="page-section">

                <jsp:include page="/includes/components/page-header.jsp">

                    <jsp:param
                            name="title"
                            value="Invoice"/>

                    <jsp:param
                            name="description"
                            value="View customer invoice."/>

                </jsp:include>

            </section>

            <section class="page-section">

                <div class="card">

                    <div class="card-header">

                        <h3>
                            ${bill.invoiceNumber}
                        </h3>

                    </div>

                    <div class="card-body">

                        <div class="form-grid two-column">

                            <div>

                                <strong>Customer</strong>

                                <br>

                                ${bill.customer.customerCode}
                                -
                                ${bill.customer.fullName}

                            </div>

                            <div>

                                <strong>Date</strong>

                                <br>

                                ${bill.billDate}

                            </div>

                        </div>

                    </div>

                </div>

            </section>

            <section class="page-section">

                <div class="card">

                    <div class="card-header">

                        <h3>Products</h3>

                    </div>

                    <div class="card-body">

                        <table class="table">

                            <thead>

                            <tr>

                                <th>Product</th>

                                <th>Qty</th>

                                <th>Price</th>

                                <th>Total</th>

                            </tr>

                            </thead>

                            <tbody>

                            <c:forEach items="${bill.items}" var="item">

                                <tr>

                                    <td>

                                            ${item.product.productCode}

                                        -

                                            ${item.product.productName}

                                    </td>

                                    <td>

                                            ${item.quantity}

                                    </td>

                                    <td>

                                        ₹${item.unitPrice}

                                    </td>

                                    <td>

                                        ₹${item.lineTotal}

                                    </td>

                                </tr>

                            </c:forEach>

                            </tbody>

                        </table>

                    </div>

                </div>

            </section>

            <section class="page-section">

                <div class="card">

                    <div class="card-header">

                        <h3>Summary</h3>

                    </div>

                    <div class="card-body">

                        <table class="table">

                            <tr>

                                <td>Subtotal</td>

                                <td>₹${bill.subtotal}</td>

                            </tr>

                            <tr>

                                <td>Discount</td>

                                <td>₹${bill.discount}</td>

                            </tr>

                            <tr>

                                <th>Grand Total</th>

                                <th>₹${bill.totalAmount}</th>

                            </tr>

                            <tr>

                                <td>Status</td>

                                <td>

                                    <span class="badge badge-success">

                                        ${bill.status}

                                    </span>

                                </td>

                            </tr>

                        </table>

                    </div>

                </div>

            </section>

            <section class="page-section">

                <div style="display:flex;justify-content:space-between;">

                    <a
                            href="${pageContext.request.contextPath}/billing"
                            class="btn btn-secondary">

                        ← Back

                    </a>

                    <button
                            class="btn btn-primary"
                            onclick="window.print()">

                        🖨 Print Invoice

                    </button>

                </div>

            </section>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>