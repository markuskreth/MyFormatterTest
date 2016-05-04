package de.kreth.formatter.textdecorator;

import java.awt.Graphics;
import java.text.AttributedString;

public interface PrintableStringDecorator {

   /**
    * 
    * @param as
    * @param text
    * @param graphics
    * @param pageFormat
    * @param pageIndex
    * @param posX Start Position
    * @param posY Start Position
    */
   void print(AttributedString as, String text, Graphics graphics, int posX, int posY);

   /**
    * Manipulating Font in {@link AttributedString} and {@link Graphics} if true;
    * @return Manipulating Font?;
    */
   boolean isManipulatingFont();
   
   String name();
   
   public static abstract class AbstractPrintableStringDecorator implements PrintableStringDecorator{
      
      @Override
      public String name() {
         return getClass().getSimpleName();
      }
      
   }
}
