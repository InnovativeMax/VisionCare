<%
    String activeMenu = (String) request.getAttribute("activeMenu");
    String activeSection = (String) request.getAttribute("activeSection");
%>

<aside class="sidebar">

    <ul class="sidebar-menu">

        <li>
            <a href="${pageContext.request.contextPath}/dashboard"
               class="<%= "dashboard".equals(activeMenu) ? "active" : "" %>">
                <i class="bi bi-speedometer2"></i> Dashboard
            </a>
        </li>

        <li class="has-submenu <%= "masters".equals(activeSection) ? "open" : "" %>">

            <a href="#">
                <i class="bi bi-building"></i>
                Business
                <i class="bi bi-chevron-down submenu-arrow"></i>
            </a>

            <ul class="submenu">

                <li>
                    <a href="${pageContext.request.contextPath}/customers"
                       class="<%= "customers".equals(activeMenu) ? "active" : "" %>">
                        <i class="bi bi-people"></i> Customers
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/products"
                       class="<%= "products".equals(activeMenu) ? "active" : "" %>">
                        <i class="bi bi-box-seam"></i> Products
                    </a>
                </li>

                <%--                <li>--%>
                <%--                    <a href="#">--%>
                <%--                        <i class="bi bi-person-gear"></i> Users--%>
                <%--                    </a>--%>
                <%--                </li>--%>

            </ul>

        </li>

        <%--        <li>--%>
        <%--            <a href="#">--%>
        <%--                <i class="bi bi-box-seam"></i> Inventory--%>
        <%--            </a>--%>
        <%--        </li>--%>

        <li class="has-submenu <%= "billing".equals(activeSection) ? "open" : "" %>">

            <a href="#">
                <i class="bi bi-receipt"></i>
                Billing
                <i class="bi bi-chevron-down submenu-arrow"></i>
            </a>

            <ul class="submenu">

                <li>

                    <a href="${pageContext.request.contextPath}/billing"
                       class="<%= "billing".equals(activeMenu) ? "active" : "" %>">

                        <i class="bi bi-cart-check"></i>
                        Sales

                    </a>

                </li>

            </ul>

        </li>

        <li class="has-submenu <%= "reports".equals(activeSection) ? "open" : "" %>">

            <a href="#">
                <i class="bi bi-bar-chart"></i>
                Reports
                <i class="bi bi-chevron-down submenu-arrow"></i>
            </a>

            <ul class="submenu">

                <li>

                    <a href="${pageContext.request.contextPath}/reports/sales"
                       target="_blank"
                       rel="noopener"
                       class="<%= "sales-report".equals(activeMenu) ? "active" : "" %>">

                        <i class="bi bi-file-earmark-pdf"></i>
                        Sales Report

                    </a>

                </li>

                <li>

                    <a href="${pageContext.request.contextPath}/reports/inventory"
                       target="_blank"
                       rel="noopener"
                       class="<%= "inventory-report".equals(activeMenu) ? "active" : "" %>">

                        <i class="bi bi-file-earmark-bar-graph"></i>
                        Inventory Report

                    </a>

                </li>

            </ul>

        </li>

        <%--        <li>--%>
        <%--            <a href="#">--%>
        <%--                <i class="bi bi-gear"></i> Settings--%>
        <%--            </a>--%>
        <%--        </li>--%>

    </ul>

    <div class="sidebar-footer">

        <a href="${pageContext.request.contextPath}/logout">

            <i class="bi bi-box-arrow-right"></i> Logout

        </a>

    </div>

</aside>