package de.kreth.formatter.textdecorator;

import java.awt.Graphics;
import java.text.AttributedString;

public class IntentTextDecorator extends PrintableStringDecorator.AbstractPrintableStringDecorator {

   private final int intent;

   public IntentTextDecorator(int intent) {
      super();
      this.intent = intent;
   }

   @Override
   public void print(AttributedString as, String text, Graphics graphics, int posX, int posY) {
      graphics.translate(intent, graphics.getClipBounds().y);
   }

   @Override
   public boolean isManipulatingFont() {
      return true;      // Even it doesn't manipulate the Font, it manipuates the Position in a way, it must be sorted first
   }

}
