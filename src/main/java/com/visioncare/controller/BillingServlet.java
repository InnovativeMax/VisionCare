package com.visioncare.controller;

import com.visioncare.model.Bill;
import com.visioncare.model.BillItem;
import com.visioncare.service.BillingService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/billing")
public class BillingServlet extends HttpServlet {

    private final BillingService billingService =
            new BillingService();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        if (action == null || action.isBlank()) {

            action = "list";

        }

        switch (action) {

            case "new":

                showBillForm(
                        request,
                        response
                );

                break;

            case "view":

                showBillView(
                        request,
                        response
                );

                break;

            default:

                showBillList(
                        request,
                        response
                );

                break;

        }

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action =
                request.getParameter("action");

        if (action == null || action.isBlank()) {

            action = "save";

        }

        switch (action) {

            case "save":

                saveBill(
                        request,
                        response
                );

                break;

            default:

                response.sendRedirect(
                        request.getContextPath()
                                + "/billing"
                );

                break;

        }

    }

    private void showBillList(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "bills",
                billingService.getAllBills()
        );

        request.setAttribute(
                "activeMenu",
                "billing"
        );

        request.setAttribute(
                "activeSection",
                "billing"
        );

        request.getRequestDispatcher("/billing/list.jsp")
                .forward(request, response);

    }

    private void showBillForm(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        Bill bill = new Bill();

        bill.setInvoiceNumber(
                billingService.generateInvoiceNumber()
        );

        bill.setBillDate(
                LocalDate.now()
        );

        request.setAttribute(
                "bill",
                bill
        );

        request.setAttribute(
                "customers",
                billingService.getActiveCustomers()
        );

        request.setAttribute(
                "products",
                billingService.getActiveProducts()
        );

        request.setAttribute(
                "activeMenu",
                "billing"
        );

        request.setAttribute(
                "activeSection",
                "billing"
        );

        request.getRequestDispatcher("/billing/form.jsp")
                .forward(request, response);

    }

    private void showBillView(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(
                request.getParameter("id")
        );

        Bill bill =
                billingService.getBill(id);

        if (bill == null) {

            response.sendRedirect(
                    request.getContextPath()
                            + "/billing"
            );

            return;

        }

        request.setAttribute(
                "bill",
                bill
        );

        request.setAttribute(
                "activeMenu",
                "billing"
        );

        request.setAttribute(
                "activeSection",
                "billing"
        );

        request.getRequestDispatcher("/billing/view.jsp")
                .forward(request, response);

    }

    private void saveBill(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        try {

            Bill bill = new Bill();

            bill.setInvoiceNumber(
                    request.getParameter("invoiceNumber")
            );

            bill.setBillDate(
                    LocalDate.parse(
                            request.getParameter("billDate")
                    )
            );

            bill.setCustomerId(
                    Long.parseLong(
                            request.getParameter("customerId")
                    )
            );

            bill.setDiscount(
                    new BigDecimal(
                            request.getParameter("discount")
                    )
            );

            bill.setTotalAmount(
                    new BigDecimal(
                            request.getParameter("totalAmount")
                    )
            );

            bill.setSubtotal(
                    new BigDecimal(
                            request.getParameter("subTotal")
                    )
            );

            List<BillItem> items = new ArrayList<>();

            String[] productIds =
                    request.getParameterValues("productId[]");

            String[] quantities =
                    request.getParameterValues("quantity[]");

            String[] prices =
                    request.getParameterValues("unitPrice[]");

            String[] totals =
                    request.getParameterValues("lineTotal[]");

            for (int i = 0; i < productIds.length; i++) {

                if (productIds[i] == null || productIds[i].isBlank()) {
                    continue;
                }

                BillItem item = new BillItem();

                item.setProductId(
                        Integer.parseInt(productIds[i])
                );

                item.setQuantity(
                        Integer.parseInt(quantities[i])
                );

                item.setUnitPrice(
                        new BigDecimal(prices[i])
                );

                item.setLineTotal(
                        new BigDecimal(totals[i])
                );

                items.add(item);

            }

            bill.setItems(items);

            billingService.saveBill(bill);

            request.getSession().setAttribute(
                    "successMessage",
                    "Sale created successfully."
            );

            response.sendRedirect(
                    request.getContextPath() + "/billing"
            );

        } catch (Exception exception) {

            exception.printStackTrace();

            request.getSession().setAttribute(
                    "errorMessage",
                    "Failed to create sale."
            );

            response.sendRedirect(
                    request.getContextPath()
                            + "/billing?action=new"
            );

        }
    }
}
