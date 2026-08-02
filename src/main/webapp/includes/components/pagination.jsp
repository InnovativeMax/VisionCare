<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Integer currentPage = (Integer) request.getAttribute("currentPage");
    Integer totalPages = (Integer) request.getAttribute("totalPages");
    Integer totalRecords = (Integer) request.getAttribute("totalRecords");
    Integer pageSize = (Integer) request.getAttribute("pageSize");

    String paginationQuery =
            (String) request.getAttribute("paginationQuery");

    String entityName =
            (String) request.getAttribute("entityName");

    if (entityName == null)
        entityName = "record";

    if (currentPage == null) currentPage = 1;
    if (totalPages == null) totalPages = 1;
    if (totalRecords == null) totalRecords = 0;
    if (pageSize == null) pageSize = 10;
    if (paginationQuery == null) paginationQuery = "";

    int startRecord = 0;
    int endRecord = 0;

    if (totalRecords > 0) {

        startRecord = ((currentPage - 1) * pageSize) + 1;

        endRecord = Math.min(currentPage * pageSize, totalRecords);

    }

    int startPage = Math.max(1, currentPage - 2);
    int endPage = Math.min(totalPages, currentPage + 2);
%>

<% if (totalPages > 1) { %>

<nav class="pagination-container" aria-label="Pagination">

    <div class="pagination-info">

        Showing

        <strong><%= startRecord %>–<%= endRecord %>
        </strong>

        of

        <strong><%= totalRecords %>
        </strong>

        <%= entityName %><%= totalRecords == 1 ? "" : "s" %>

    </div>

    <div class="pagination-links">

        <% if (currentPage > 1) { %>

        <a href="?page=<%= currentPage - 1 %><%= paginationQuery %>">

            &laquo; Previous

        </a>

        <% } %>

        <% if (startPage > 1) { %>

        <a href="?page=1<%= paginationQuery %>">1</a>

        <% if (startPage > 2) { %>

        <span class="pagination-ellipsis">…</span>

        <% } %>

        <% } %>

        <% for (int i = startPage; i <= endPage; i++) { %>

        <% if (i == currentPage) { %>

        <span class="active-page" aria-current="page">

                    <%= i %>

                </span>

        <% } else { %>

        <a href="?page=<%= i %><%= paginationQuery %>">

            <%= i %>

        </a>

        <% } %>

        <% } %>

        <% if (endPage < totalPages) { %>

        <% if (endPage < totalPages - 1) { %>

        <span class="pagination-ellipsis">…</span>

        <% } %>

        <a href="?page=<%= totalPages %><%= paginationQuery %>">

            <%= totalPages %>

        </a>

        <% } %>

        <% if (currentPage < totalPages) { %>

        <a href="?page=<%= currentPage + 1 %><%= paginationQuery %>">

            Next &raquo;

        </a>

        <% } %>

    </div>

</nav>

<% } %>