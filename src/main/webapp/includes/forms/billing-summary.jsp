<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="card">

    <div class="card-header">

        <h3>Order Summary</h3>

    </div>

    <div class="card-body">

        <div class="form-grid two-column">

            <div class="form-group">

                <label>Subtotal</label>

                <input
                        type="number"
                        id="subTotal"
                        name="subTotal"
                        readonly
                        value="0.00">

            </div>

            <div class="form-group">

                <label>Discount</label>

                <input
                        type="number"
                        id="discount"
                        name="discount"
                        value="0.00"
                        min="0"
                        step="0.01">

            </div>

            <div class="form-group">

                <label>Grand Total</label>

                <input
                        type="number"
                        id="grandTotal"
                        name="totalAmount"
                        readonly
                        value="0.00">

            </div>

        </div>

        <div style="margin-top:24px; text-align:right;">

            <button
                    type="submit"
                    class="btn btn-primary">

                Save Sale

            </button>

        </div>

    </div>

</div>