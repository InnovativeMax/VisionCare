<%@ page import="java.util.List" %>

<%
  List<String> validationErrors =
          (List<String>) request.getAttribute(
                  "validationErrors"
          );
%>

<%
  if (validationErrors != null &&
          !validationErrors.isEmpty()) {
%>

<div class="alert alert-danger">

  <div class="alert-title">

    <i class="bi bi-exclamation-triangle-fill"></i>

    Please correct the following errors

  </div>

  <ul>

    <%
      for (String error : validationErrors) {
    %>

    <li>

      <%= error %>

    </li>

    <%
      }
    %>

  </ul>

</div>

<%
  }
%>