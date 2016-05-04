package de.kreth.formatter.textdecorator;

import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;


public class UnderlineDecorator extends PrintableStringDecorator.AbstractPrintableStringDecorator {

   @Override
   public void print(AttributedString as, String text, Graphics graphics, int posX, int posY) {      
      as.addAttribute(TextAttribute.UNDERLINE, Integer.valueOf(text.length()));      
   }

   @Override
   public boolean isManipulatingFont() {
      return false;
   }

}
