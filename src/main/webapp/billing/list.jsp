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

                    <jsp:param
                            name="title"
                            value="Cash Bills"/>

                    <jsp:param
                            name="description"
                            value="Create and manage customer invoices."/>

                    <jsp:param
                            name="buttonLabel"
                            value="New Bill"/>

                    <jsp:param
                            name="buttonLink"
                            value="${pageContext.request.contextPath}/billing?action=new"/>

                </jsp:include>

            </section>

            <!-- Search -->
            <section class="page-section">

                <jsp:include page="/includes/components/filter-bar.jsp"/>

            </section>

            <!-- Bills -->
            <section class="page-section">

                <jsp:include page="/includes/tables/bill-table.jsp"/>

            </section>

        </div>

    </main>

</div>

<%@ include file="/includes/layout/footer.jsp" %>