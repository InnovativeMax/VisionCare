document.addEventListener(
    "DOMContentLoaded",
    () => {

        const toast =
            document.querySelector(".toast");

        if (!toast) {

            return;

        }

        setTimeout(() => {

            toast.classList.add("show");

        }, 150);

        setTimeout(() => {

            toast.classList.remove("show");

        }, 3500);

        setTimeout(() => {

            toast.remove();

        }, 4000);

    }
);