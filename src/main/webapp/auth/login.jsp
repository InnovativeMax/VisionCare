<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="UTF-8">

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">

    <title>VisionCare ERP | Login</title>

    <!-- Core -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/core/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/core/typography.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/core/common.css">

    <!-- Components -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/components/buttons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/components/forms.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/components/cards.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/components/tables.css">

    <!-- Layout -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/layout.css">

    <!-- Utilities -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/utilities/utilities.css">

    <!-- Page -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/pages/login.css">

</head>

<body>

<main class="login-page">

    <div class="login-container">

        <div class="card login-card">

            <header class="login-header text-center">

                <div class="login-logo">

                    👓

                </div>

                <h1 class="login-title">
                    VisionCare ERP
                </h1>

                <p class="login-subtitle">
                    Optical Shop Management System
                </p>

            </header>

            <form
                    id="loginForm"
                    action="${pageContext.request.contextPath}/auth/login"
                    method="post">

                <div class="form-group">

                    <label class="form-label required">
                        Username
                    </label>

                    <input
                            type="text"
                            name="username"
                            class="form-control"
                            placeholder="Enter Username"
                            autocomplete="username"
                            required>

                </div>

                <div class="form-group">

                    <label class="form-label required">
                        Password
                    </label>

                    <input
                            type="password"
                            id="password"
                            name="password"
                            class="form-control"
                            placeholder="Enter Password"
                            autocomplete="current-password"
                            required>

                </div>

                <div class="form-check mb-lg">

                    <input
                            type="checkbox"
                            id="rememberMe"
                            name="rememberMe">

                    <label for="rememberMe">

                        Remember Me

                    </label>

                </div>

                <button
                        type="submit"
                        class="btn btn-primary btn-block">

                    Sign In

                </button>

            </form>

            <footer class="login-footer text-center">

                <small>

                    Version 1.0.0

                </small>

                <br>

                <small>

                    © VisionCare ERP

                </small>

            </footer>

        </div>

    </div>

</main>

<script src="${pageContext.request.contextPath}/assets/js/login.js"></script>

</body>

</html>