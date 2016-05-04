package de.kreth.formatter.textdecorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class FontDecorators extends AbstractDecoratorFactory {

   @Override
   protected Collection<PrintableStringDecorator> usedGenerators() {
      List<PrintableStringDecorator> result = new ArrayList<>();
      for (PrintableStringDecorator deco: new AllDecoratorsFactory().usedGenerators()) {
         if (deco.isManipulatingFont())
            result.add(deco);
      }
      
      return result;
   }

}
