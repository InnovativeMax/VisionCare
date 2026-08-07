package com.visioncare.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.visioncare.dao.BillDAO;
import com.visioncare.model.Bill;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/reports/sales")
public class SalesReportServlet extends HttpServlet {

    private final BillDAO billDAO = new BillDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "inline; filename=SalesReport.pdf"
        );

        List<Bill> bills = billDAO.findAll();

        Document document = new Document();

        try {

            PdfWriter.getInstance(
                    document,
                    response.getOutputStream()
            );

            document.open();

            Font titleFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    18
            );

            Font headingFont = FontFactory.getFont(
                    FontFactory.HELVETICA_BOLD,
                    12
            );

            Font bodyFont = FontFactory.getFont(
                    FontFactory.HELVETICA,
                    11
            );

            Paragraph company = new Paragraph(
                    "VisionCare ERP",
                    titleFont
            );

            company.setAlignment(Paragraph.ALIGN_CENTER);

            document.add(company);

            Paragraph reportTitle = new Paragraph(
                    "Sales Report",
                    headingFont
            );

            reportTitle.setAlignment(Paragraph.ALIGN_CENTER);
            reportTitle.setSpacingAfter(20);

            document.add(reportTitle);

            Paragraph generated = new Paragraph(
                    "Generated On : " + LocalDate.now(),
                    bodyFont
            );

            generated.setSpacingAfter(15);

            document.add(generated);

            PdfPTable table = new PdfPTable(5);

            table.setWidthPercentage(100);

            table.setWidths(
                    new float[]{
                            3f,
                            5f,
                            3f,
                            3f,
                            2f
                    }
            );

            PdfPCell cell;

            cell = new PdfPCell(new Phrase("Invoice", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Customer", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Date", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Total", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Status", headingFont));
            table.addCell(cell);

            BigDecimal grandTotal = BigDecimal.ZERO;

            for (Bill bill : bills) {

                table.addCell(
                        new Phrase(
                                bill.getInvoiceNumber(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                bill.getCustomer().getFullName(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                bill.getBillDate().toString(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                "₹" + bill.getTotalAmount(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                bill.getStatus(),
                                bodyFont
                        )
                );

                grandTotal =
                        grandTotal.add(
                                bill.getTotalAmount()
                        );
            }

            document.add(table);

            Paragraph total = new Paragraph(
                    "\n\nGrand Total : ₹" + grandTotal,
                    headingFont
            );

            total.setAlignment(Paragraph.ALIGN_RIGHT);

            document.add(total);

            document.close();

        } catch (DocumentException exception) {

            throw new IOException(exception);

        }
    }
}