package com.visioncare.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.visioncare.dao.BillDAO;
import com.visioncare.model.Bill;
import com.visioncare.model.BillItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/billing/print")
public class PrintInvoiceServlet extends HttpServlet {

    private final BillDAO billDAO = new BillDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        Long id = Long.parseLong(request.getParameter("id"));

        Bill bill = billDAO.findById(id);

        if (bill == null) {
            response.sendRedirect(request.getContextPath() + "/billing");
            return;
        }

        response.setContentType("application/pdf");

        response.setHeader(
                "Content-Disposition",
                "inline; filename=Invoice-" +
                        bill.getInvoiceNumber() +
                        ".pdf"
        );

        Document document = new Document(
                PageSize.A4,
                36,
                36,
                40,
                36
        );

        try {

            PdfWriter.getInstance(
                    document,
                    response.getOutputStream()
            );

            document.open();

            Font titleFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            20
                    );

            Font headingFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA_BOLD,
                            12
                    );

            Font normalFont =
                    FontFactory.getFont(
                            FontFactory.HELVETICA,
                            11
                    );

            Paragraph company =
                    new Paragraph(
                            "VisionCare ERP",
                            titleFont
                    );

            company.setAlignment(
                    Element.ALIGN_CENTER
            );

            document.add(company);

            Paragraph invoiceTitle =
                    new Paragraph(
                            "TAX INVOICE",
                            headingFont
                    );

            invoiceTitle.setAlignment(
                    Element.ALIGN_CENTER
            );

            invoiceTitle.setSpacingAfter(25);

            document.add(invoiceTitle);

            PdfPTable infoTable =
                    new PdfPTable(2);

            infoTable.setWidthPercentage(100);

            infoTable.setWidths(
                    new float[]{1, 1}
            );

            PdfPCell left =
                    new PdfPCell();

            left.setBorder(Rectangle.NO_BORDER);

            left.addElement(
                    new Paragraph(
                            "Invoice No : "
                                    + bill.getInvoiceNumber(),
                            headingFont
                    )
            );

            left.addElement(
                    new Paragraph(
                            "Customer : "
                                    + bill.getCustomer().getCustomerCode()
                                    + " - "
                                    + bill.getCustomer().getFullName(),
                            normalFont
                    )
            );

            PdfPCell right =
                    new PdfPCell();

            right.setBorder(Rectangle.NO_BORDER);

            right.addElement(
                    new Paragraph(
                            "Date : "
                                    + bill.getBillDate(),
                            headingFont
                    )
            );

            right.setHorizontalAlignment(
                    Element.ALIGN_RIGHT
            );

            infoTable.addCell(left);
            infoTable.addCell(right);

            document.add(infoTable);

            document.add(
                    Chunk.NEWLINE
            );
            PdfPTable table = new PdfPTable(4);

            table.setWidthPercentage(100);

            table.setWidths(
                    new float[]{
                            6f,
                            2f,
                            2f,
                            2f
                    }
            );

            PdfPCell cell;

            cell = new PdfPCell(
                    new Phrase("Product", headingFont)
            );
            table.addCell(cell);

            cell = new PdfPCell(
                    new Phrase("Qty", headingFont)
            );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(
                    new Phrase("Unit Price", headingFont)
            );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            cell = new PdfPCell(
                    new Phrase("Total", headingFont)
            );
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);

            for (BillItem item : bill.getItems()) {

                table.addCell(
                        new Phrase(
                                item.getProduct().getProductCode()
                                        + " - "
                                        + item.getProduct().getProductName(),
                                normalFont
                        )
                );

                PdfPCell qtyCell = new PdfPCell(
                        new Phrase(
                                String.valueOf(item.getQuantity()),
                                normalFont
                        )
                );

                qtyCell.setHorizontalAlignment(
                        Element.ALIGN_CENTER
                );

                table.addCell(qtyCell);

                PdfPCell priceCell = new PdfPCell(
                        new Phrase(
                                "₹" + item.getUnitPrice(),
                                normalFont
                        )
                );

                priceCell.setHorizontalAlignment(
                        Element.ALIGN_RIGHT
                );

                table.addCell(priceCell);

                PdfPCell totalCell = new PdfPCell(
                        new Phrase(
                                "₹" + item.getLineTotal(),
                                normalFont
                        )
                );

                totalCell.setHorizontalAlignment(
                        Element.ALIGN_RIGHT
                );

                table.addCell(totalCell);
            }

            document.add(table);

            document.add(Chunk.NEWLINE);
            PdfPTable summaryTable = new PdfPTable(2);

            summaryTable.setWidthPercentage(40);
            summaryTable.setHorizontalAlignment(Element.ALIGN_RIGHT);

            summaryTable.setWidths(new float[]{2f, 2f});

            summaryTable.addCell(new Phrase("Subtotal", headingFont));
            summaryTable.addCell(new Phrase("₹" + bill.getSubtotal(), normalFont));

            summaryTable.addCell(new Phrase("Discount", headingFont));
            summaryTable.addCell(new Phrase("₹" + bill.getDiscount(), normalFont));

            summaryTable.addCell(new Phrase("Grand Total", headingFont));
            summaryTable.addCell(new Phrase("₹" + bill.getTotalAmount(), headingFont));

            document.add(summaryTable);

            document.add(Chunk.NEWLINE);

            Paragraph status = new Paragraph(
                    "Status : " + bill.getStatus(),
                    headingFont
            );

            document.add(status);

            document.add(Chunk.NEWLINE);

            Paragraph thankYou = new Paragraph(
                    "Thank you for choosing VisionCare ERP!",
                    normalFont
            );

            thankYou.setAlignment(Element.ALIGN_CENTER);

            document.add(thankYou);

            document.close();

        } catch (DocumentException exception) {

            throw new IOException(exception);

        }
    }
}