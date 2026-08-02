<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.visioncare.model.User" %>
<%@ page import="com.visioncare.constants.ApplicationConstants" %>

<%
    User loggedInUser =
            (User) session.getAttribute(
                    ApplicationConstants.LOGGED_IN_USER
            );
%>

<%@ include file="/includes/layout/header.jsp" %>

<%@ include file="/includes/layout/navbar.jsp" %>

<div class="app-layout">
    <%
        request.setAttribute(
                "activeMenu",
                "dashboard"
        );
    %>
    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <!-- ======================================================
                 Welcome Card
            ======================================================= -->

            <div class="card">

                <div class="card-header">

                    <div>

                        <h1 class="card-title">

                            Good Morning,
                            <%= loggedInUser.getFullName() %>

                        </h1>

                        <p class="card-subtitle">

                            Welcome back to VisionCare ERP.

                        </p>

                    </div>

                </div>

                <div class="card-body">

                    <p>

                        <strong>Employee</strong><br>

                        <%= loggedInUser.getFullName() %>

                    </p>

                    <p>

                        <strong>Username</strong><br>

                        <%= loggedInUser.getUsername() %>

                    </p>

                    <p>

                        <strong>User Code</strong><br>

                        <%= loggedInUser.getUserCode() %>

                    </p>

                    <p>

                        <strong>Role ID</strong><br>

                        <%= loggedInUser.getRoleId() %>

                    </p>

                    <p>

                        <strong>Theme</strong><br>

                        <%= loggedInUser.getTheme() %>

                    </p>

                </div>

            </div>

            <!-- ======================================================
                 Dashboard Statistics
            ======================================================= -->

            <div class="stats-grid">

                <div class="stat-card">

                    <div class="stat-title">

                        <i class="bi bi-people"></i>

                        Customers

                    </div>

                    <div class="stat-value">

                        0

                    </div>

                </div>

                <div class="stat-card">

                    <div class="stat-title">

                        <i class="bi bi-box-seam"></i>

                        Products

                    </div>

                    <div class="stat-value">

                        0

                    </div>

                </div>

                <div class="stat-card">

                    <div class="stat-title">

                        <i class="bi bi-currency-rupee"></i>

                        Today's Sales

                    </div>

                    <div class="stat-value">

                        ₹0

                    </div>

                </div>

                <div class="stat-card">

                    <div class="stat-title">

                        <i class="bi bi-exclamation-triangle"></i>

                        Low Stock

                    </div>

                    <div class="stat-value">

                        0

                    </div>

                </div>

            </div>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>