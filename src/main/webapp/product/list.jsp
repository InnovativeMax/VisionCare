<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="/includes/layout/header.jsp" %>
<%@ include file="/includes/layout/navbar.jsp" %>

<div class="app-layout">

    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <!-- Page Header -->
            <section class="page-section">

                <jsp:include page="/includes/components/page-header.jsp">
                    <jsp:param name="title" value="Products"/>
                    <jsp:param name="description"
                               value="Organize your product catalog, pricing and inventory."/>
                    <jsp:param name="buttonLabel" value="Add Product"/>
                    <jsp:param name="buttonLink"
                               value="${pageContext.request.contextPath}/products?action=new"/>
                </jsp:include>

            </section>

            <!-- Search & Filters -->
            <section class="page-section">

                <jsp:include page="/includes/components/filter-bar.jsp"/>

            </section>

            <!-- Product Table -->
            <section class="page-section">

                <jsp:include page="/includes/tables/product-table.jsp"/>

            </section>

            <!-- Pagination -->
            <section class="page-section">

                <jsp:include page="/includes/components/pagination.jsp"/>

            </section>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>