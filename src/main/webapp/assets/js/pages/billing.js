document.addEventListener("DOMContentLoaded", () => {

    initializeBilling();

});

function initializeBilling() {

    document
        .querySelectorAll("#productTable tbody tr")
        .forEach(bindRow);

    document
        .getElementById("discount")
        ?.addEventListener("input", calculateSummary);

    document
        .getElementById("addRow")
        .addEventListener("click", addRow);

    updateRemoveButtons();
}

function bindRow(row) {

    row.querySelector(".productSelect")
        .addEventListener("change", () => {

            calculateRow(row);

        });

    row.querySelector(".qty")
        .addEventListener("input", () => {

            calculateRow(row);

        });

    row.querySelector(".removeRow")
        .addEventListener("click", () => {

            row.remove();

            calculateSummary();

            updateRemoveButtons();

        });

}

function calculateRow(row) {

    const product =
        row.querySelector(".productSelect");

    const qty =
        row.querySelector(".qty");

    const price =
        row.querySelector(".price");

    const total =
        row.querySelector(".lineTotal");

    // No product selected
    if (product.value === "") {

        price.value = "";
        total.value = "";

        calculateSummary();

        return;

    }

    // Prevent duplicate products
    const duplicate = [...document.querySelectorAll(".productSelect")]
        .filter(select => select !== product)
        .some(select => select.value === product.value);

    if (duplicate) {

        alert("This product has already been added.");

        product.selectedIndex = 0;

        price.value = "";
        total.value = "";

        calculateSummary();

        return;

    }

    const unitPrice =
        Number(
            product.selectedOptions[0].dataset.price || 0
        );

    const stock =
        Number(
            product.selectedOptions[0].dataset.stock || 0
        );

    let quantity =
        Number(qty.value || 1);

    // Minimum quantity = 1
    if (quantity < 1) {

        quantity = 1;
        qty.value = 1;

    }

    // Quantity cannot exceed stock
    if (quantity > stock) {

        alert("Only " + stock + " item(s) available in stock.");

        quantity = stock;
        qty.value = stock;

    }

    // Update price
    price.value =
        unitPrice.toFixed(2);

    // Update line total
    total.value =
        (unitPrice * quantity).toFixed(2);

    // Refresh summary
    calculateSummary();

}

function calculateSummary() {

    let subtotal = 0;

    document
        .querySelectorAll(".lineTotal")
        .forEach(input => {

            subtotal += Number(input.value || 0);

        });

    const discountField =
        document.getElementById("discount");

    let discount =
        Number(discountField.value || 0);

    // Discount cannot be negative
    if (discount < 0) {

        discount = 0;
        discountField.value = "0.00";

    }

    // Discount cannot exceed subtotal
    if (discount > subtotal) {

        discount = subtotal;
        discountField.value = subtotal.toFixed(2);

    }

    const grandTotal =
        Math.max(subtotal - discount, 0);

    document.getElementById("subTotal").value =
        subtotal.toFixed(2);

    document.getElementById("grandTotal").value =
        grandTotal.toFixed(2);

    const grandTotalDisplay =
        document.getElementById("grandTotalDisplay");

    if (grandTotalDisplay) {

        grandTotalDisplay.textContent =
            (subtotal - discount).toFixed(2);

    }

    const saveButton =
        document.querySelector("button[type='submit']");

    // Disable Save if no valid sale
    saveButton.disabled =
        subtotal <= 0 || grandTotal <= 0;

}

function addRow() {

    const tbody =
        document.querySelector("#productTable tbody");

    const row =
        tbody.rows[0].cloneNode(true);

    row.querySelector(".productSelect").selectedIndex = 0;

    row.querySelector(".qty").value = 1;

    row.querySelector(".price").value = "";

    row.querySelector(".lineTotal").value = "";

    bindRow(row);

    tbody.appendChild(row);

    updateRemoveButtons();

}

function updateRemoveButtons() {

    const rows =
        document.querySelectorAll("#productTable tbody tr");

    rows.forEach((row, index) => {

        const btn =
            row.querySelector(".removeRow");

        btn.style.display =
            rows.length === 1 && index === 0
                ? "none"
                : "";

    });

}