<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!-- ==========================================================
Validation Errors
=========================================================== -->

<c:if test="${not empty errors}">

    <div class="alert alert-danger">

        <ul>

            <c:forEach var="error" items="${errors}">

                <li>${error}</li>

            </c:forEach>

        </ul>

    </div>

</c:if>

<!-- ==========================================================
Product Information
=========================================================== -->

<div class="form-section">

    <h3 class="form-section-title">
        Product Information
    </h3>

    <div class="form-grid">

        <div class="form-group">

            <label for="productCode" class="form-label">
                Product Code
            </label>

            <input
                    id="productCode"
                    type="text"
                    class="form-control"
                    name="productCode"
                    value="${product.productCode}"
                    readonly>

        </div>

        <div class="form-group">

            <label for="productName" class="form-label required">
                Product Name
            </label>

            <input
                    id="productName"
                    type="text"
                    class="form-control"
                    name="productName"
                    value="${product.productName}"
                    placeholder="Enter product name"
                    autocomplete="off"
                    required>

        </div>

        <div class="form-group">

            <label for="category" class="form-label required">
                Category
            </label>

            <select
                    id="category"
                    name="category"
                    class="form-control"
                    required>

                <option value="">
                    Select Category
                </option>

                <c:forEach var="category" items="${categories}">

                    <option value="${category}"
                        ${product.category == category ? 'selected' : ''}>

                            ${category}

                    </option>

                </c:forEach>

            </select>

        </div>

        <div class="form-group">

            <label for="brand" class="form-label required">
                Brand
            </label>

            <input
                    id="brand"
                    type="text"
                    class="form-control"
                    name="brand"
                    value="${product.brand}"
                    placeholder="Enter brand"
                    autocomplete="organization"
                    required>

        </div>

    </div>

</div>

<!-- ==========================================================
Pricing
=========================================================== -->

<div class="form-section">

    <h3 class="form-section-title">
        Pricing
    </h3>

    <div class="form-grid">

        <div class="form-group">

            <label for="costPrice" class="form-label required">
                Cost Price
            </label>

            <input
                    id="costPrice"
                    type="number"
                    class="form-control"
                    name="costPrice"
                    value="${product.costPrice}"
                    min="0"
                    step="0.01"
                    inputmode="decimal"
                    required>

        </div>

        <div class="form-group">

            <label for="sellingPrice" class="form-label required">
                Selling Price
            </label>

            <input
                    id="sellingPrice"
                    type="number"
                    class="form-control"
                    name="sellingPrice"
                    value="${product.sellingPrice}"
                    min="0"
                    step="0.01"
                    inputmode="decimal"
                    required>

        </div>

    </div>

</div>

<!-- ==========================================================
Inventory
=========================================================== -->

<div class="form-section">

    <h3 class="form-section-title">
        Inventory
    </h3>

    <div class="form-grid">

        <div class="form-group">

            <label for="stockQuantity" class="form-label">
                Opening Stock
            </label>

            <input
                    id="stockQuantity"
                    type="number"
                    class="form-control"
                    name="stockQuantity"
                    value="${product.stockQuantity}"
                    min="0"
                    inputmode="numeric">

        </div>

        <div class="form-group">

            <label for="reorderLevel" class="form-label">
                Reorder Level
            </label>

            <input
                    id="reorderLevel"
                    type="number"
                    class="form-control"
                    name="reorderLevel"
                    value="${product.reorderLevel}"
                    min="0"
                    inputmode="numeric">

        </div>

    </div>

</div>

<!-- ==========================================================
Additional Information
=========================================================== -->

<div class="form-section">

    <h3 class="form-section-title">
        Additional Information
    </h3>

    <div class="form-group">

        <label for="description" class="form-label">
            Description
        </label>

        <textarea
                id="description"
                class="form-control"
                name="description"
                rows="4"
                placeholder="Enter product description">${product.description}</textarea>

    </div>

    <div class="form-check">

        <input
                id="active"
                type="checkbox"
                name="active"
                value="true"
        ${product == null || product.active ? 'checked' : ''}>

        <label for="active">

            Active Product

        </label>

    </div>

</div>