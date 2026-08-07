<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:choose>

    <c:when test="${not empty bills}">

        <div class="card">

            <div class="product-table-wrapper">

                <table class="table product-table">

                    <thead>

                    <tr>

                        <th>Invoice</th>
                        <th>Customer</th>
                        <th>Date</th>
                        <th>Total</th>
                        <th>Status</th>
                        <th>Actions</th>

                    </tr>

                    </thead>

                    <tbody>

                    <c:forEach var="bill" items="${bills}">

                        <tr>

                            <!-- Invoice -->
                            <td>

                                <a class="table-link"
                                   href="${pageContext.request.contextPath}/billing?action=view&id=${bill.id}">

                                        ${bill.invoiceNumber}

                                </a>

                            </td>

                            <!-- Customer -->
                            <td>

                                    ${bill.customer.fullName}

                            </td>

                            <!-- Bill Date -->
                            <td>

                                    ${bill.billDate}

                            </td>

                            <!-- Total -->
                            <td class="text-end">

                                ₹${bill.totalAmount}

                            </td>

                            <!-- Status -->
                            <td>

                                    <span class="status-badge
                                    ${bill.status eq 'PAID'
                                        ? 'status-active'
                                        : 'status-inactive'}">

                                            ${bill.status}

                                    </span>

                            </td>

                            <!-- Actions -->
                            <td class="text-center">

                                <details class="action-menu">

                                    <summary class="action-menu-btn">

                                        <i class="bi bi-three-dots-vertical"></i>

                                    </summary>

                                    <div class="action-menu-dropdown">

                                        <a href="${pageContext.request.contextPath}/billing?action=view&id=${bill.id}">

                                            <i class="bi bi-eye"></i>

                                            View

                                        </a>

                                        <a href="#"
                                           onclick="window.print();return false;">

                                            <i class="bi bi-printer"></i>

                                            Print

                                        </a>

                                    </div>

                                </details>

                            </td>

                        </tr>

                    </c:forEach>

                    </tbody>

                </table>
            </div>

        </div>

    </c:when>

    <c:otherwise>

        <jsp:include page="/includes/components/empty-state.jsp">

            <jsp:param
                    name="title"
                    value="No Bills Found"/>

            <jsp:param
                    name="description"
                    value="Create your first bill to begin billing customers."/>

        </jsp:include>

    </c:otherwise>

</c:choose>

<script>

    document.addEventListener("click", function (event) {

        document.querySelectorAll(".action-menu").forEach(function (menu) {

            if (!menu.contains(event.target)) {

                menu.removeAttribute("open");

            }

        });

    });

</script>