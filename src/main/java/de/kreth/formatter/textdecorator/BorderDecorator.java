package de.kreth.formatter.textdecorator;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.text.AttributedString;


public class BorderDecorator extends PrintableStringDecorator.AbstractPrintableStringDecorator {

   @Override
   public void print(AttributedString as, String text, Graphics graphics, int posX, int posY) {

      FontMetrics fontMetrics = graphics.getFontMetrics();
      int width = fontMetrics.stringWidth(text) + 3;
      int height = fontMetrics.getHeight() + 4;
      int x = posX -2;
      int y = posY +1;
      if(x<0)
         x = 0;
      graphics.drawRect(x, y, width, height);
   }

   @Override
   public boolean isManipulatingFont() {
      return false;
   }

}
