<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ page import="com.visioncare.model.Customer" %>

<%@ include file="/includes/layout/header.jsp" %>

<%@ include file="/includes/layout/navbar.jsp" %>

<%
    Customer customer =
            (Customer) request.getAttribute("customer");

    boolean editMode =
            customer != null &&
                    customer.getId() != null;

    String pageTitle =
            editMode
                    ? "Edit Customer"
                    : "Add Customer";

    String pageDescription =
            editMode
                    ? "Update customer information."
                    : "Register a new customer in VisionCare ERP.";

    String submitButtonText =
            editMode
                    ? "Update Customer"
                    : "Save Customer";

    String formAction =
            editMode
                    ? request.getContextPath() + "/customers/edit"
                    : request.getContextPath() + "/customers/add";
%>

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

                        <%= pageTitle %>

                    </h1>

                    <p class="page-description">

                        <%= pageDescription %>

                    </p>

                </div>

            </div>

            <!-- ======================================================
                 Validation Errors
            ======================================================= -->

            <%@ include file="/includes/components/alert.jsp" %>

            <!-- ======================================================
                 Customer Form
            ======================================================= -->

            <div class="card">

                <div class="card-body">

                    <form action="<%= formAction %>"
                          method="post">

                        <% if (editMode) { %>

                        <input
                                type="hidden"
                                name="id"
                                value="<%= customer.getId() %>">

                        <% } %>

                        <!-- ==================================================
                             Basic Information
                        =================================================== -->

                        <div class="form-grid">

                            <div class="form-group">

                                <label>

                                    Full Name *

                                </label>

                                <input
                                        type="text"
                                        class="form-control"
                                        name="fullName"
                                        placeholder="Enter full name"
                                        required
                                        value="<%= customer != null &&
                                                customer.getFullName() != null
                                                ? customer.getFullName()
                                                : "" %>">

                            </div>

                            <div class="form-group">

                                <label>

                                    Mobile Number *

                                </label>

                                <input
                                        type="text"
                                        class="form-control"
                                        name="mobileNumber"
                                        placeholder="Enter mobile number"
                                        required
                                        value="<%= customer != null &&
                                                customer.getMobileNumber() != null
                                                ? customer.getMobileNumber()
                                                : "" %>">

                            </div>

                        </div>

                        <div class="form-grid">

                            <div class="form-group">

                                <label>

                                    Email

                                </label>

                                <input
                                        type="email"
                                        class="form-control"
                                        name="email"
                                        placeholder="Enter email address"
                                        value="<%= customer != null &&
                                                customer.getEmail() != null
                                                ? customer.getEmail()
                                                : "" %>">

                            </div>

                            <div class="form-group">

                                <label>

                                    Date of Birth

                                </label>

                                <input
                                        type="date"
                                        class="form-control"
                                        name="dateOfBirth"
                                        value="<%= customer != null &&
                                                customer.getDateOfBirth() != null
                                                ? customer.getDateOfBirth()
                                                : "" %>">

                            </div>

                        </div>

                        <div class="form-grid">

                            <div class="form-group">

                                <label>

                                    Gender

                                </label>

                                <select
                                        class="form-control"
                                        name="gender">

                                    <option value="">

                                        Select Gender

                                    </option>

                                    <option value="Male"
                                            <%= customer != null &&
                                                    customer.getGender() != null &&
                                                    customer.getGender().equals("Male")
                                                    ? "selected"
                                                    : "" %>>

                                        Male

                                    </option>

                                    <option value="Female"
                                            <%= customer != null &&
                                                    customer.getGender() != null &&
                                                    customer.getGender().equals("Female")
                                                    ? "selected"
                                                    : "" %>>

                                        Female

                                    </option>

                                    <option value="Other"
                                            <%= customer != null &&
                                                    customer.getGender() != null &&
                                                    customer.getGender().equals("Other")
                                                    ? "selected"
                                                    : "" %>>

                                        Other

                                    </option>

                                </select>

                            </div>

                        </div>

                        <!-- ==================================================
                             Address Information
                        =================================================== -->

                        <div class="form-group">

                            <label>

                                Address Line 1

                            </label>

                            <input
                                    type="text"
                                    class="form-control"
                                    name="addressLine1"
                                    placeholder="House No., Street"
                                    value="<%= customer != null &&
                                            customer.getAddressLine1() != null
                                            ? customer.getAddressLine1()
                                            : "" %>">

                        </div>

                        <div class="form-group">

                            <label>

                                Address Line 2

                            </label>

                            <input
                                    type="text"
                                    class="form-control"
                                    name="addressLine2"
                                    placeholder="Apartment, Landmark"
                                    value="<%= customer != null &&
                                            customer.getAddressLine2() != null
                                            ? customer.getAddressLine2()
                                            : "" %>">

                        </div>

                        <div class="form-grid">

                            <div class="form-group">

                                <label>

                                    City

                                </label>

                                <input
                                        type="text"
                                        class="form-control"
                                        name="city"
                                        placeholder="Enter city"
                                        value="<%= customer != null &&
                                                customer.getCity() != null
                                                ? customer.getCity()
                                                : "" %>">

                            </div>

                            <div class="form-group">

                                <label>

                                    State

                                </label>

                                <input
                                        type="text"
                                        class="form-control"
                                        name="state"
                                        placeholder="Enter state"
                                        value="<%= customer != null &&
                                                customer.getState() != null
                                                ? customer.getState()
                                                : "" %>">

                            </div>

                            <div class="form-group">

                                <label>

                                    Pincode

                                </label>

                                <input
                                        type="text"
                                        class="form-control"
                                        name="pincode"
                                        placeholder="Enter pincode"
                                        value="<%= customer != null &&
                                                customer.getPincode() != null
                                                ? customer.getPincode()
                                                : "" %>">

                            </div>

                        </div>

                        <!-- ==================================================
                             Notes
                        =================================================== -->

                        <div class="form-group">

                            <label>

                                Notes

                            </label>

                            <textarea
                                    class="form-control"
                                    rows="4"
                                    name="notes"><%= customer != null &&
                                    customer.getNotes() != null
                                    ? customer.getNotes()
                                    : "" %></textarea>

                        </div>

                        <!-- ==================================================
                             Actions
                        =================================================== -->

                        <div class="form-actions">

                            <button
                                    type="button"
                                    class="btn btn-secondary"
                                    onclick="window.location.href='${pageContext.request.contextPath}/customers'">

                                Cancel

                            </button>

                            <button
                                    type="submit"
                                    class="btn btn-primary">

                                <%= submitButtonText %>

                            </button>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>