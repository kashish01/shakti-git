package com.shakti.service;

import java.awt.Color;
import java.io.IOException;
import java.util.List;


import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.shakti.model.ProductDetails;

import jakarta.servlet.http.HttpServletResponse;


public class PdfExporter {
   private List<ProductDetails> listProducts;

   public PdfExporter(List<ProductDetails> listProducts) {
	this.listProducts = listProducts;
   }

private void writeTableHeader(PdfPTable table) {
       PdfPCell cell = new PdfPCell();
       cell.setBackgroundColor(Color.BLUE);
       cell.setPadding(6);
        
       Font font = FontFactory.getFont(FontFactory.HELVETICA);
       font.setColor(Color.WHITE);
        
       cell.setPhrase(new Phrase("DATE", font));
       table.addCell(cell);
        
       cell.setPhrase(new Phrase("NAME", font));
       table.addCell(cell);
        
       cell.setPhrase(new Phrase("PRODUCT", font));
       table.addCell(cell);
        
       cell.setPhrase(new Phrase("QUANTITY", font));
       table.addCell(cell);
        
       cell.setPhrase(new Phrase("PRICE", font));
       table.addCell(cell); 
       
       cell.setPhrase(new Phrase("TOTAL", font));
       table.addCell(cell); 
   }
    
   private void writeTableData(PdfPTable table) {
	   int total = 0;
       for (ProductDetails product : listProducts) {
    	   Integer quantity=product.getQuantity(),price=product.getPrice();
    	   if(product.getQuantity()== null) {
        	   quantity=0;
        	   product.setQuantity(0);
           }
           if(product.getPrice()== null) { 
        	   price=0;
        	   product.setPrice(0);
           }
           table.addCell(String.valueOf(product.getActualDate()));
           table.addCell(product.getName());
           table.addCell(product.getProductName());
           table.addCell(String.valueOf(product.getQuantity()));
           table.addCell(String.valueOf(product.getPrice()));
           int result=quantity*price;
           total+=result;
           table.addCell(String.valueOf(result));
       }
       PdfPCell cell = new PdfPCell();
       Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       font.setSize(11);
       font.setColor(Color.BLACK);
       cell.setPhrase(new Phrase("Balance Amount", font));
       table.addCell(cell);
       
       table.addCell(" ");
       table.addCell(" ");
       table.addCell(" ");
       table.addCell(" ");
       table.addCell(String.valueOf(listProducts.get(0).getBalance()));
       
       cell.setPhrase(new Phrase("Grand Total", font));
       table.addCell(cell);
       table.addCell(" ");
       table.addCell(" ");
       table.addCell(" ");
       table.addCell(" ");
       font.setSize(14);
       cell.setPhrase(new Phrase(String.valueOf(total+listProducts.get(0).getBalance()), font));
       table.addCell(cell);
       
   }
    
   public void export(HttpServletResponse response,String date) throws DocumentException, IOException {
       Document document = new Document(PageSize.A4);
       PdfWriter writer =PdfWriter.getInstance(document, response.getOutputStream());
       HeaderFooter footer = new HeaderFooter(new Phrase("Shakti Embroidery"),false);
       footer.setAlignment(Element.ALIGN_RIGHT);
       footer.setBorder(Rectangle.NO_BORDER);
       document.setFooter(footer);
       writer.setFooter(footer); 
       document.open();
       Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       font.setSize(18);
       font.setColor(Color.BLUE);
        
       Paragraph p = new Paragraph(listProducts.get(0).getPartyName(), font);
       p.setAlignment(Paragraph.ALIGN_CENTER);
       document.add(p);
       
       Font font1 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
       font1.setSize(14);
       font1.setColor(Color.BLUE);
       Paragraph p1 = new Paragraph(date, font1);
       p1.setAlignment(Paragraph.ALIGN_RIGHT);
       
       document.add(p1);
        
       PdfPTable table = new PdfPTable(6);
       table.setWidthPercentage(100f);
       table.setWidths(new float[] {2.5f, 3.5f, 2.0f, 1.8f, 1.5f, 1.5f});
       table.setSpacingBefore(10);
        
       writeTableHeader(table);
       writeTableData(table);
      
       document.add(table);
       document.close();
        
   }
}
