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

        <!-- ===================================================== -->
        <!-- Card Body -->
        <!-- ===================================================== -->

        <div class="card-body">

            <div class="form-grid two-column">

                <!-- Invoice Number -->

                <div class="form-group">

                    <label class="form-label">

                        Invoice Number

                    </label>

                    <input
                            class="form-control"
                            type="text"
                            name="invoiceNumber"
                            value="${bill.invoiceNumber}"
                            readonly>

                </div>

                <!-- Bill Date -->

                <div class="form-group">

                    <label class="form-label">

                        Bill Date

                    </label>

                    <input
                            class="form-control"
                            type="date"
                            name="billDate"
                            value="${bill.billDate}"
                            readonly>

                </div>

            </div>

            <div class="form-grid one-column mt-4">

                <!-- Customer -->

                <div class="form-group">

                    <label class="form-label">

                        Customer

                        <span class="required">*</span>

                    </label>

                    <select
                            class="form-control"
                            name="customerId"
                            required>

                        <option value="">

                            Select Customer

                        </option>

                        <c:forEach
                                items="${customers}"
                                var="customer">

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