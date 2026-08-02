<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.visioncare.model.Customer" %>

<%
    Customer customer =
            (Customer) request.getAttribute("customer");
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

                        Customer Details

                    </h1>

                    <p class="page-description">

                        View customer information.

                    </p>

                </div>

            </div>

            <!-- ======================================================
                 General Information
            ======================================================= -->

            <div class="card">

                <div class="card-header">

                    <h2>

                        <i class="bi bi-person-vcard"></i>

                        General Information

                    </h2>

                </div>

                <div class="card-body">

                    <div class="details-grid">

                        <div class="details-label">

                            Customer Code

                        </div>

                        <div class="details-value">

                            <%= customer.getCustomerCode() %>

                        </div>

                        <div class="details-label">

                            Full Name

                        </div>

                        <div class="details-value">

                            <%= customer.getFullName() %>

                        </div>

                        <div class="details-label">

                            Mobile Number

                        </div>

                        <div class="details-value">

                            <%= customer.getMobileNumber() %>

                        </div>

                        <div class="details-label">

                            Email

                        </div>

                        <div class="details-value">

                            <%= customer.getEmail() != null &&
                                    !customer.getEmail().isBlank()
                                    ? customer.getEmail()
                                    : "-" %>

                        </div>

                        <div class="details-label">

                            Date of Birth

                        </div>

                        <div class="details-value">

                            <%= customer.getDateOfBirth() != null
                                    ? customer.getDateOfBirth()
                                    : "-" %>

                        </div>

                        <div class="details-label">

                            Gender

                        </div>

                        <div class="details-value">

                            <%= customer.getGender() != null
                                    ? customer.getGender()
                                    : "-" %>

                        </div>

                        <div class="details-label">

                            Status

                        </div>

                        <div class="details-value">

                            <span class="badge <%= customer.getActive()
                                    ? "badge-success"
                                    : "badge-danger" %>">

                                <%= customer.getActive()
                                        ? "Active"
                                        : "Inactive" %>

                            </span>

                        </div>

                    </div>

                </div>

            </div>

            <!-- ======================================================
            Address
            ======================================================= -->

            <div class="card details-section">

                <div class="card-header">

                    <h2>

                        <i class="bi bi-geo-alt"></i>

                        Address

                    </h2>

                </div>

                <div class="card-body">

                    <div class="details-grid">

                        <div class="details-label">

                            Address Line 1

                        </div>

                        <div class="details-value">

                            <%= customer.getAddressLine1() != null
                                    ? customer.getAddressLine1()
                                    : "-" %>

                        </div>

                        <div class="details-label">

                            Address Line 2

                        </div>

                        <div class="details-value">

                            <%= customer.getAddressLine2() != null
                                    ? customer.getAddressLine2()
                                    : "-" %>

                        </div>

                        <div class="details-label">

                            City

                        </div>

                        <div class="details-value">

                            <%= customer.getCity() != null
                                    ? customer.getCity()
                                    : "-" %>

                        </div>

                        <div class="details-label">

                            State

                        </div>

                        <div class="details-value">

                            <%= customer.getState() != null
                                    ? customer.getState()
                                    : "-" %>

                        </div>

                        <div class="details-label">

                            Pincode

                        </div>

                        <div class="details-value">

                            <%= customer.getPincode() != null
                                    ? customer.getPincode()
                                    : "-" %>

                        </div>

                    </div>

                </div>

            </div>

            <!-- ======================================================
            Notes
            ======================================================= -->

            <div class="card details-section">

                <div class="card-header">

                    <h2>

                        <i class="bi bi-journal-text"></i>

                        Notes

                    </h2>

                </div>

                <div class="card-body">

                    <div class="details-value">

                        <%= customer.getNotes() != null &&
                                !customer.getNotes().isBlank()
                                ? customer.getNotes()
                                : "No additional notes available." %>

                    </div>

                </div>

            </div>

            <!-- ======================================================
            Actions
            ======================================================= -->

            <div class="form-actions details-actions">

                <button
                        type="button"
                        class="btn btn-secondary"
                        data-href="${pageContext.request.contextPath}/customers">

                    <i class="bi bi-arrow-left"></i>

                    Customers

                </button>

                <button
                        type="button"
                        class="btn btn-primary"
                        data-href="<%= request.getContextPath() %>/customers/edit?id=<%= customer.getId() %>">

                    <i class="bi bi-pencil-fill"></i>

                    Edit

                </button>

            </div>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>