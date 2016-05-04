package de.kreth.formatter.textdecorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractDecoratorFactory {
   
   private List<PrintableStringDecorator> decorators;
      
   public AbstractDecoratorFactory() {
      decorators = new ArrayList<>(usedGenerators());
   }
   
   public List<PrintableStringDecorator> getDecorators() {
      return decorators;
   }

   protected abstract Collection<PrintableStringDecorator> usedGenerators();
   
}
