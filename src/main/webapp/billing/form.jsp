<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<%@ include file="/includes/layout/header.jsp" %>

<div class="app-layout">

    <%@ include file="/includes/layout/sidebar.jsp" %>

    <main class="page">

        <div class="container">

            <!-- ===================================================== -->
            <!-- Page Header                                            -->
            <!-- ===================================================== -->

            <jsp:include page="/includes/components/page-header.jsp">

                <jsp:param
                        name="title"
                        value="New Sale"/>

                <jsp:param
                        name="description"
                        value="Create customer sales invoice."/>

            </jsp:include>

            <!-- ===================================================== -->
            <!-- Billing Form                                           -->
            <!-- ===================================================== -->

            <form
                    id="billingForm"
                    method="post"
                    action="${pageContext.request.contextPath}/billing?action=save">

                <!-- Invoice Information -->

                <jsp:include
                        page="/includes/forms/billing-invoice-section.jsp"/>

                <!-- Billing Content -->

                <div class="billing-layout">

                    <!-- Left Side -->

                    <div class="billing-main">

                        <jsp:include
                                page="/includes/forms/billing-products-section.jsp"/>

                    </div>

                    <!-- Right Side -->

                    <aside class="billing-sidebar">

                        <jsp:include
                                page="/includes/forms/billing-summary-section.jsp"/>

                    </aside>

                </div>

            </form>

        </div>

    </main>

</div>

<script
        src="${pageContext.request.contextPath}/assets/js/pages/billing.js">
</script>

<%@ include file="/includes/layout/footer.jsp" %>