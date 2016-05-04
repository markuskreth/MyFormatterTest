package de.kreth.formatter.textdecorator;

import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;


public class StrikeThroughDecorator extends PrintableStringDecorator.AbstractPrintableStringDecorator {

   @Override
   public void print(AttributedString as, String text, Graphics graphics, int posX, int posY) { 
      as.addAttribute(TextAttribute.STRIKETHROUGH  , Boolean.TRUE);
   }

   @Override
   public boolean isManipulatingFont() {
      return false;
   }

}
