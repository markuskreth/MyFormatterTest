package de.kreth.formatter.textdecorator;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;


public class ItalicFontDecorator extends PrintableStringDecorator.AbstractPrintableStringDecorator {

   @Override
   public void print(AttributedString as, String text, Graphics graphics, int posX, int posY) {
      Font font = graphics.getFont();
      Font myFont = new Font(font.getFontName(),  Font.ITALIC|font.getStyle(), font.getSize());
      
      graphics.setFont(myFont);
      as.addAttribute(TextAttribute.FONT, myFont);
   }

   @Override
   public boolean isManipulatingFont() {
      return true;
   }

}
