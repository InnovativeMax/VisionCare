package com.visioncare.controller;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.visioncare.dao.ProductDAO;
import com.visioncare.model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/reports/inventory")
public class InventoryReportServlet extends HttpServlet {

    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/pdf");
        response.setHeader(
                "Content-Disposition",
                "inline; filename=InventoryReport.pdf"
        );

        List<Product> products =
                productDAO.findByStatus("all");

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
                    "Inventory Report",
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

            cell = new PdfPCell(new Phrase("Code", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Product", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Category", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Price", headingFont));
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Stock", headingFont));
            table.addCell(cell);

            for (Product product : products) {

                table.addCell(
                        new Phrase(
                                product.getProductCode(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                product.getProductName(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                product.getCategory(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                "₹" + product.getSellingPrice(),
                                bodyFont
                        )
                );

                table.addCell(
                        new Phrase(
                                String.valueOf(product.getStockQuantity()),
                                bodyFont
                        )
                );
            }

            document.add(table);

            Paragraph total = new Paragraph(
                    "\n\nTotal Products : " + products.size(),
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