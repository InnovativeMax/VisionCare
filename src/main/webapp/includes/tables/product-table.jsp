<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<c:choose>

    <c:when test="${not empty products}">

        <div class="card">

            <table class="table">

                <thead>

                <tr>

                    <th>Code</th>
                    <th>Product</th>
                    <th>Category</th>
                    <th>Brand</th>
                    <th class="text-end">Price</th>
                    <th class="text-center">Stock</th>
                    <th class="text-center">Status</th>
                    <th class="text-center">Actions</th>

                </tr>

                </thead>

                <tbody>

                <c:forEach var="product" items="${products}">

                    <tr>

                        <td>${product.productCode}</td>

                        <td>${product.productName}</td>

                        <td>${product.category}</td>

                        <td>${product.brand}</td>

                        <td class="text-end">
                            <fmt:formatNumber
                                    value="${product.sellingPrice}"
                                    type="number"
                                    minFractionDigits="2"
                                    maxFractionDigits="2"/>
                        </td>

                        <td class="text-center">
                                ${product.stockQuantity}
                        </td>

                        <td class="text-center">

                            <span class="badge ${product.active ? 'badge-success' : 'badge-danger'}">

                                    ${product.active ? 'Active' : 'Inactive'}

                            </span>

                        </td>

                        <td class="text-center">

                            <a href="${pageContext.request.contextPath}/products?action=view&id=${product.id}"
                               class="btn btn-sm btn-outline">

                                View

                            </a>

                            <a href="${pageContext.request.contextPath}/products?action=edit&id=${product.id}"
                               class="btn btn-sm btn-outline">

                                Edit

                            </a>

                        </td>

                    </tr>

                </c:forEach>

                </tbody>

            </table>

        </div>

    </c:when>

    <c:otherwise>

        <jsp:include page="/includes/components/empty-state.jsp">

            <jsp:param
                    name="title"
                    value="No Products Found"/>

            <jsp:param
                    name="description"
                    value="There are no products available. Click 'Add Product' to create your first product."/>

        </jsp:include>

    </c:otherwise>

</c:choose>