<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.visioncare.model.Customer" %>

<%
    List<Customer> customers =
            (List<Customer>) request.getAttribute("customers");
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

                        Customers

                    </h1>

                    <p class="page-description">

                        Manage customer information.

                    </p>

                </div>

                <button
                        type="button"
                        class="btn btn-primary"
                        data-href="${pageContext.request.contextPath}/customers/add">

                    <i class="bi bi-plus-lg"></i>

                    Add Customer

                </button>

            </div>

            <!-- ======================================================
                Customer Filter
            ======================================================= -->

            <div class="filter-bar">

                <form
                        method="get"
                        action="${pageContext.request.contextPath}/customers"
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
                                placeholder="Search by customer code, name or mobile..."
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

                            <th>Customer Code</th>

                            <th>Name</th>

                            <th>Mobile</th>

                            <th>City</th>

                            <th>Status</th>

                            <th class="text-center">Actions</th>

                        </tr>

                        </thead>

                        <tbody>

                        <%
                            if (customers == null || customers.isEmpty()) {
                        %>

                        <tr>

                            <td colspan="6"
                                style="text-align:center;">

                                No customers found.

                            </td>

                        </tr>

                        <%
                        } else {

                            for (Customer customer : customers) {
                        %>

                        <tr>

                            <td>

                                <a href="${pageContext.request.contextPath}/customers/view?id=<%= customer.getId() %>"
                                   class="table-link">

                                    <%= customer.getCustomerCode() %>

                                </a>

                            </td>

                            <td>

                                <%= customer.getFullName() %>

                            </td>

                            <td>

                                <%= customer.getMobileNumber() %>

                            </td>

                            <td>

                                <%= customer.getCity() %>

                            </td>

                            <td>

                                <span class="badge <%= customer.getActive()
                                        ? "badge-success"
                                        : "badge-secondary" %>">

                                    <%= customer.getActive()
                                            ? "Active"
                                            : "Inactive" %>

                                </span>

                            </td>

                            <!-- Actions -->

                            <td class="text-center">

                                <a href="${pageContext.request.contextPath}/customers/edit?id=<%= customer.getId() %>"
                                   class="action-btn action-edit"
                                   title="Edit Customer">

                                    <i class="bi bi-pencil-square"></i>

                                </a>

                                <button
                                        type="button"
                                        class="action-btn action-delete"
                                        title="Deactivate Customer"
                                        onclick="showConfirmation(
                                                'Deactivate Customer',
                                                'Are you sure you want to deactivate this customer?',
                                                '${pageContext.request.contextPath}/customers/delete',
                                                '<%= customer.getId() %>'
                                                )">

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