package de.kreth.formatter.table;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;


public class Table implements Printable {

   private List<Column> columns;
   private List<Object[]> rows;
   public boolean printHeader = false;
   
   public Table() {
      columns = new ArrayList<>();
      rows = new ArrayList<>();
   }
   
   public boolean isPrintHeader() {
      return printHeader;
   }
   
   public void setPrintHeader(boolean printHeader) {
      this.printHeader = printHeader;
   }

   public boolean add(Column e) {
      return columns.add(e);
   }

   public boolean add(Object[] e) {
      return rows.add(e);
   }

   public void print(Graphics graphics){

      FontMetrics fontMetrics = graphics.getFontMetrics();
      int height = fontMetrics.getHeight() + 3;
      int width = fontMetrics.stringWidth("G") + 4;
            
      int posY = 0;
      
      if (printHeader) {

         int posX = 0;
         
         for (Column col :columns) {

            int tw = col.getColumnWidth() * width;
            
            graphics.drawRect(posX, posY, tw -1, height -1);            
            col.paint(graphics, new Rectangle(posX, posY, tw, height));
            posX += tw;
         }
         
         posY += height;
      }
      
      for (Object[] row : rows) {
         int posX = 0;
         int index = 0;
         
         for (Column col :columns) {

            int tw = col.getColumnWidth() * width;
            
            graphics.drawRect(posX, posY, tw -1, height -1);
            Object content;

            if (index < row.length)
            	content = row[index];
            else
            	content = "";
            
            col.paint(graphics, new Rectangle(posX, posY, tw, height), content);
            posX += tw;
            index++;
         }
         posY += height;
      }
      
   }
   
   @Override
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
      
      if(pageIndex > 0)
         return Printable.NO_SUCH_PAGE;

      if(pageFormat != null) {
         ((Graphics2D)graphics).translate(pageFormat.getImageableX(), pageFormat.getImageableY());
      }
      
      print(graphics);
      
      return Printable.PAGE_EXISTS;
   }

   public void clearRows() {
      rows.clear();
   }

   
}
