/*
==========================================================
VisionCare ERP
File        : modal.js
Description : Reusable Confirmation Modal
Version     : 1.0
==========================================================
*/

const modal =
    document.getElementById("confirmationModal");

const modalTitle =
    document.getElementById("modalTitle");

const modalMessage =
    document.getElementById("modalMessage");

const modalForm =
    document.getElementById("modalForm");

const modalCancel =
    document.getElementById("modalCancel");

/*
==========================================================
Show Modal
==========================================================
*/

function showConfirmation(title, message, action, id) {

    modalTitle.textContent = title;

    modalMessage.textContent = message;

    modalForm.action = action;

    document.getElementById("modalId").value = id;

    modal.classList.add("show");

}

/*
==========================================================
Hide Modal
==========================================================
*/

function hideConfirmation() {

    modal.classList.remove("show");

}

/*
==========================================================
Events
==========================================================
*/

modalCancel.addEventListener(
    "click",
    hideConfirmation
);

modal.addEventListener(
    "click",
    function (event) {

        if (event.target === modal) {

            hideConfirmation();

        }

    }
);