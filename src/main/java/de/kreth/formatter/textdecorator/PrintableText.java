package de.kreth.formatter.textdecorator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.text.AttributedString;
import java.util.*;


public class PrintableText implements Printable, PrintableStringDecorator {

   private String text;
   private List<PrintableStringDecorator> decorators;
   
   
   public PrintableText(String text) {
      super();
      this.text = text;
      decorators = new ArrayList<>();
   }

   public boolean add(PrintableStringDecorator e) {
      return decorators.add(e);
   }

   public boolean remove(PrintableStringDecorator o) {
      return decorators.remove(o);
   }

   public boolean addAll(Collection<? extends PrintableStringDecorator> c) {
      return decorators.addAll(c);
   }

   @Override
   public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

      if (pageFormat != null && pageIndex > 0) {
           return NO_SUCH_PAGE;
      }
      
      if(this.text != null) {
         
         if(pageFormat != null) {
            ((Graphics2D)graphics).translate(pageFormat.getImageableX(), pageFormat.getImageableY());
         }
         
         AttributedString as = new AttributedString(text);
         print(as, text, graphics, 10, 10);
         
      }
      
      return PAGE_EXISTS;
   }

   @Override
   public void print(AttributedString as, String text, Graphics graphics, int posX, int posY) {
      
      decorators.sort(new Comparator<PrintableStringDecorator>(){

         @Override
         public int compare(PrintableStringDecorator o1, PrintableStringDecorator o2) {
            if(o1.isManipulatingFont() != o2.isManipulatingFont())
               return Boolean.compare(o2.isManipulatingFont(), o1.isManipulatingFont());
            
            return o1.name().compareTo(o2.name());
         }});
      
      for (PrintableStringDecorator de: decorators) {
         de.print(as, text, graphics, posX, posY);
      }
      
      graphics.drawString(as.getIterator(), posX, posY + graphics.getFontMetrics().getHeight() - 1);
      
   }

   @Override
   public boolean isManipulatingFont() {
      return false;
   }

   @Override
   public String name() {
      return getClass().getSimpleName();
   }

   @Override
   public String toString() {
      return text;
   }
}
