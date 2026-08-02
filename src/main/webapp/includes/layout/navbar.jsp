<%@ page import="com.visioncare.model.User" %>
<%@ page import="com.visioncare.constants.ApplicationConstants" %>

<%
    User navbarUser = (User) session.getAttribute(
            ApplicationConstants.LOGGED_IN_USER);
%>

<nav class="navbar">

    <div class="navbar-left">

        <div class="navbar-logo">

            <i class="bi bi-eyeglasses"></i>

        </div>

        <div class="navbar-title">

            VisionCare ERP

        </div>

    </div>

    <div class="navbar-right">

        <button
                id="themeToggle"
                class="btn btn-icon"
                title="Toggle Theme">

            <i class="bi bi-moon"></i>

        </button>

        <span class="navbar-user">

            <%= navbarUser.getFullName() %>

        </span>

    </div>

</nav>