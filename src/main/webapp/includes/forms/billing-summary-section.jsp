<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<section class="page-section">

    <div class="card">

        <!-- ========================================= -->
        <!-- Card Header -->
        <!-- ========================================= -->

        <div class="card-header">

            <div class="section-title">

                <div class="section-icon">

                    <i class="bi bi-credit-card"></i>

                </div>

                <div>

                    <h3>Order Summary</h3>

                    <p class="section-description">

                        Review totals before saving the sale.

                    </p>

                </div>

            </div>

        </div>

        <!-- ========================================= -->
        <!-- Card Body -->
        <!-- ========================================= -->

        <div class="card-body">

            <div class="form-group">

                <label class="form-label">
                    Subtotal
                </label>

                <input
                        class="form-control"
                        type="text"
                        id="subTotal"
                        name="subTotal"
                        readonly
                        value="0.00">

            </div>

            <div class="form-group mt-3">

                <label class="form-label">
                    Discount
                </label>

                <input
                        class="form-control"
                        type="number"
                        id="discount"
                        name="discount"
                        value="0.00"
                        min="0"
                        step="0.01">

            </div>

            <div class="grand-total-card">

                <span>

                    Grand Total

                </span>

                <h2>

                    ₹<span id="grandTotalDisplay">

                        0.00

                    </span>

                </h2>

            </div>

            <!-- Hidden value submitted to servlet -->

            <input
                    type="hidden"
                    id="grandTotal"
                    name="totalAmount"
                    value="0.00">

        </div>

        <!-- ========================================= -->
        <!-- Footer -->
        <!-- ========================================= -->

        <div class="card-footer">

            <button
                    type="submit"
                    class="btn btn-primary w-100">

                <i class="bi bi-check-circle"></i>

                Save Sale

            </button>

        </div>

    </div>

</section>