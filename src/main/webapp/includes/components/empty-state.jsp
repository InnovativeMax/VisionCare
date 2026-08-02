<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String icon = request.getParameter("icon");
    String title = request.getParameter("title");
    String description = request.getParameter("description");
    String buttonLabel = request.getParameter("buttonLabel");
    String buttonLink = request.getParameter("buttonLink");

    if (icon == null || icon.isBlank())
        icon = "bi-inbox";

    if (title == null || title.isBlank())
        title = "No Records Found";

    if (description == null)
        description = "";

    if (buttonLabel == null)
        buttonLabel = "";

    if (buttonLink == null)
        buttonLink = "#";
%>

<div class="card">

    <div class="empty-state">

        <i class="bi <%= icon %> empty-state-icon"></i>

        <h3 class="empty-state-title">

            <%= title %>

        </h3>

        <% if (!description.isBlank()) { %>

        <p class="empty-state-description">

            <%= description %>

        </p>

        <% } %>

        <% if (!buttonLabel.isBlank()) { %>

        <div class="empty-state-actions">

            <a href="<%= buttonLink %>"
               class="btn btn-primary">

                <i class="bi bi-plus-lg"></i>

                <%= buttonLabel %>

            </a>

        </div>

        <% } %>

    </div>

</div>