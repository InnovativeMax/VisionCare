<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String title = request.getParameter("title");
    String description = request.getParameter("description");
    String buttonLabel = request.getParameter("buttonLabel");
    String buttonLink = request.getParameter("buttonLink");

    if (title == null) title = "";
    if (description == null) description = "";
    if (buttonLabel == null) buttonLabel = "";
    if (buttonLink == null) buttonLink = "#";
%>

<div class="page-header">

    <div class="page-header-info">

        <h1 class="page-title">
            <%= title %>
        </h1>

        <% if (!description.isBlank()) { %>
        <p class="page-description">
            <%= description %>
        </p>
        <% } %>

    </div>

    <% if (!buttonLabel.isBlank()) { %>

    <div class="page-header-actions">

        <a href="<%= buttonLink %>"
           class="btn btn-primary">

            <i class="bi bi-plus-lg"></i>

            <%= buttonLabel %>

        </a>

    </div>

    <% } %>

</div>