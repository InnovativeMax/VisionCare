<%
    String successMessage =
            (String) session.getAttribute("successMessage");

    String errorMessage =
            (String) session.getAttribute("errorMessage");

    String warningMessage =
            (String) session.getAttribute("warningMessage");

    String infoMessage =
            (String) session.getAttribute("infoMessage");

    String toastClass = null;

    String toastIcon = null;

    String toastMessage = null;

    if (successMessage != null) {

        toastClass = "toast-success";

        toastIcon = "bi-check-circle-fill";

        toastMessage = successMessage;

        session.removeAttribute("successMessage");

    } else if (errorMessage != null) {

        toastClass = "toast-error";

        toastIcon = "bi-x-circle-fill";

        toastMessage = errorMessage;

        session.removeAttribute("errorMessage");

    } else if (warningMessage != null) {

        toastClass = "toast-warning";

        toastIcon = "bi-exclamation-triangle-fill";

        toastMessage = warningMessage;

        session.removeAttribute("warningMessage");

    } else if (infoMessage != null) {

        toastClass = "toast-info";

        toastIcon = "bi-info-circle-fill";

        toastMessage = infoMessage;

        session.removeAttribute("infoMessage");

    }
%>

<% if (toastMessage != null) { %>

<div class="toast-container">

    <div class="toast <%= toastClass %>">

        <i class="bi <%= toastIcon %>"></i>

        <span>

            <%= toastMessage %>

        </span>

    </div>

</div>

<% } %>