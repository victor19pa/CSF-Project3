package com.project3.ecommerce.util;

import com.project3.ecommerce.models.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.ContentDisposition;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("views/admin/ShowProducts.xlsx") // ruta del html que lista todos los clientes diferenciarlo con nombre adicional al de pdf
public class ListProductsExcel extends AbstractXlsxView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook,
          HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setHeader("Content-Disposition", "attachment; filename=\"product-list.xlsx\"");
        Sheet sheet = workbook.createSheet("Products");

        Row titleRow = sheet.createRow(0);
        Cell cell = titleRow.createCell(0);
        cell.setCellValue("General Product List");

        Row dataRow = sheet.createRow(2);
        String[] columns = {"ID", "Category Name", "Category Status", "Product Name", "Stock", "Description", "Price"};

        for (int i=0; i < columns.length; i++){
            cell = dataRow.createCell(i);
            cell.setCellValue(columns[i]);
        }

        List<Product> productList = (List<Product>) model.get("products"); //llave del metodo donde se enlistan los productos

        int numRow = 3;
        for (Product product : productList){
            dataRow = sheet.createRow(numRow);

            dataRow.createCell(0).setCellValue(product.getId());
            dataRow.createCell(1).setCellValue(product.getCategory().getName());
            dataRow.createCell(2).setCellValue(product.getCategory().getStatus());
            dataRow.createCell(3).setCellValue(product.getName());
            dataRow.createCell(4).setCellValue(product.getStock());
            dataRow.createCell(5).setCellValue(product.getDescription());
            dataRow.createCell(6).setCellValue(product.getPrice());

            numRow++;
        }
    }
}
