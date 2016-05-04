package de.kreth.formatter.textdecorator;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class FontSizeDecorator implements PrintableStringDecorator {

   public static final FontSizeDecorator NORMAL = new FontSizeDecorator(12);
   public static final FontSizeDecorator SMALL = new FontSizeDecorator(9);
   public static final FontSizeDecorator BIG = new FontSizeDecorator(16);
   public static final FontSizeDecorator VERYBIG = new FontSizeDecorator(20);
   
   private final int value;
   
   private FontSizeDecorator(int value) {
      super();
      this.value = value;
   }

   @Override
   public void print(AttributedString as, String text, Graphics graphics, int posX, int posY) {
      Font font = graphics.getFont();

      Font myFont = new Font(font.getFontName(), font.getStyle(), value);
      
      graphics.setFont(myFont);
      as.addAttribute(TextAttribute.FONT, myFont);
   }

   @Override
   public boolean isManipulatingFont() {
      return true;
   }

   @Override
   public String name() {
      return getClass().getSimpleName() + " ("+ value +")";
   }

}
