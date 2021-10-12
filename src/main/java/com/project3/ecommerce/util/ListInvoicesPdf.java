package com.project3.ecommerce.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project3.ecommerce.models.InvoiceDetails;
import com.project3.ecommerce.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("views/admin/ShowInvoices")
public class ListInvoicesPdf extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        @SuppressWarnings("unchecked")
        List<InvoiceDetails> invoiceDetailsList = (List<InvoiceDetails>) model.get("invoiceDetails"); //key de la ruta

        response.setHeader("Content-Disposition", "attachment; filename=\"Official-Invoice-List.pdf\"");

        //fonts
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Color.BLACK);
        Font fontColumnTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,Color.BLACK);
        Font fontDataCell = FontFactory.getFont(FontFactory.COURIER,10,Color.BLACK);

        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20,-20,30,20);

        document.open();

        PdfPCell cell = null;

        PdfPTable tableTitle = new PdfPTable(1);

        cell = new PdfPCell(new Phrase("Invoice Details", fontTitle));
        cell.setBorder(0);
        cell.setBackgroundColor(new Color(40,190,138));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPadding(30);

        tableTitle.addCell(cell);
        tableTitle.setSpacingAfter(30);

        PdfPTable invoiceTable = new PdfPTable(9);
        invoiceTable.setWidths(new float[]{0.5f, 1.5f, 1.5f, 1.5f, 1f, 1.5f, 1.5f, 1.5f, 1.5f});

        cell = new PdfPCell(new Phrase("ID", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Product Name", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Category", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Product Price", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Quantity", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Payment Type", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Subtotal", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Total", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Guest Name", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        invoiceTable.addCell(cell);

        for (InvoiceDetails invoiceDetails : invoiceDetailsList){
            cell = new PdfPCell(new Phrase(invoiceDetails.getId().toString(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getProduct().getName(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getProduct().getCategory().getName(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getProduct().getPrice().toString(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getQuantity().toString(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getInvoice().getPaymentType().getType(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getSubTotal().toString(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getInvoice().getTotalOrder().toString(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);

            cell = new PdfPCell(new Phrase(invoiceDetails.getInvoice().getGuest().getName(), fontDataCell));
            cell.setPadding(5);
            invoiceTable.addCell(cell);
        }
        document.add(tableTitle);
        document.add(invoiceTable);
    }
}
