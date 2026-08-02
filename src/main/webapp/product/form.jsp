<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/includes/layout/header.jsp" %>
<%@ include file="/includes/layout/navbar.jsp" %>

<div class="app-layout">

    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <jsp:include page="/includes/components/page-header.jsp">

                <jsp:param
                        name="title"
                        value="Add Product"/>

                <jsp:param
                        name="description"
                        value="Create a new product for your catalog."/>

            </jsp:include>

            <div class="card">

                <div class="card-body">

                    <form
                            action="${pageContext.request.contextPath}/products"
                            method="post"
                            novalidate>

                        <jsp:include page="/includes/forms/product-form-fields.jsp"/>

                        <div class="form-actions">

                            <button
                                    type="submit"
                                    class="btn btn-primary">

                                <i class="bi bi-check-lg"></i>

                                Save Product

                            </button>

                            <a href="${pageContext.request.contextPath}/products"
                               class="btn btn-outline">

                                <i class="bi bi-arrow-left"></i>

                                Back to Products

                            </a>

                        </div>

                    </form>

                </div>

            </div>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>