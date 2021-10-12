package com.project3.ecommerce.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import com.project3.ecommerce.models.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@Component("views/admin/ShowProducts") // ruta donde se muestra la lista de productos en el html
public class ListProductsPdf extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<Product> productList = (List<Product>) model.get("products"); //key de la ruta

        response.setHeader("Content-Disposition", "attachment; filename=\"Official-Product-List.pdf\"");

        //fonts
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20, Color.BLACK);
        Font fontColumnTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,12,Color.BLACK);
        Font fontDataCell = FontFactory.getFont(FontFactory.COURIER,10,Color.BLACK);

        document.setPageSize(PageSize.LETTER.rotate());
        document.setMargins(-20,-20,30,20);

        document.open();

        PdfPCell cell = null;

        PdfPTable tableTitle = new PdfPTable(1);

        cell = new PdfPCell(new Phrase("Product List", fontTitle));
        cell.setBorder(0);
        cell.setBackgroundColor(new Color(40,190,138));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setVerticalAlignment(PdfPCell.ALIGN_CENTER);
        cell.setPadding(30);

        tableTitle.addCell(cell);
        tableTitle.setSpacingAfter(30);

        PdfPTable productTable = new PdfPTable(7);
        productTable.setWidths(new float[]{0.8f, 2f, 2f, 1.8f, 1.2f, 4f, 1.5f});

        cell = new PdfPCell(new Phrase("ID", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Category Name", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Category Status", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Product Name", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Stock", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Description", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        productTable.addCell(cell);

        cell = new PdfPCell(new Phrase("Price", fontColumnTitle));
        cell.setBackgroundColor(Color.lightGray);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        cell.setPadding(10);
        productTable.addCell(cell);

        for(Product product : productList){
            cell = new PdfPCell(new Phrase(product.getId().toString(), fontDataCell));
            cell.setPadding(5);
            productTable.addCell(cell);

            cell = new PdfPCell(new Phrase(product.getCategory().getName(), fontDataCell));
            cell.setPadding(5);
            productTable.addCell(cell);

            cell = new PdfPCell(new Phrase(product.getCategory().getStatus(), fontDataCell));
            cell.setPadding(5);
            productTable.addCell(cell);

            cell = new PdfPCell(new Phrase(product.getName(), fontDataCell));
            cell.setPadding(5);
            productTable.addCell(cell);

            cell = new PdfPCell(new Phrase(product.getStock().toString(), fontDataCell));
            cell.setPadding(5);
            productTable.addCell(cell);

            cell = new PdfPCell(new Phrase(product.getDescription(), fontDataCell));
            cell.setPadding(5);
            productTable.addCell(cell);

            cell = new PdfPCell(new Phrase(product.getPrice().toString(), fontDataCell));
            cell.setPadding(5);
            productTable.addCell(cell);
        }
        document.add(tableTitle);
        document.add(productTable);
    }
}
