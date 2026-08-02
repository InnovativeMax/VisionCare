<!-- ======================================================
Confirmation Modal
======================================================= -->

<div
        id="confirmationModal"
        class="modal">

    <div class="modal-content">

        <div class="modal-header">

            <h3 id="modalTitle">

                Confirmation

            </h3>

        </div>

        <div class="modal-body">

            <p id="modalMessage">

                Are you sure?

            </p>

        </div>

        <div class="modal-footer">

            <button
                    type="button"
                    class="btn btn-secondary"
                    id="modalCancel">

                Cancel

            </button>

            <form
                    id="modalForm"
                    method="post">

                <input
                        type="hidden"
                        id="modalId"
                        name="id">

                <button
                        type="submit"
                        class="btn btn-danger">

                    Confirm

                </button>

            </form>

        </div>

    </div>

</div>