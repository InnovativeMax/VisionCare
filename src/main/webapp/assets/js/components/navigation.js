/*
==========================================================
VisionCare ERP
File        : navigation.js
Description : Navigation Handler
Version     : 2.0
==========================================================
*/

document.addEventListener("DOMContentLoaded", function () {
    console.log("Navigation.js Loaded");
    // ------------------------------------
    // Button Navigation
    // ------------------------------------

    document.addEventListener("click", function (event) {

        const button = event.target.closest("[data-href]");

        if (!button) {
            return;
        }

        window.location.href = button.dataset.href;

    });

    // ------------------------------------
    // Sidebar Submenus
    // ------------------------------------

    const submenuToggles = document.querySelectorAll(".has-submenu > a");

    submenuToggles.forEach(toggle => {

        toggle.addEventListener("click", function (e) {

            e.preventDefault();

            this.parentElement.classList.toggle("open");

        });

    });

});