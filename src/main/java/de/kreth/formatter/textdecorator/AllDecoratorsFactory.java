package de.kreth.formatter.textdecorator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class AllDecoratorsFactory extends AbstractDecoratorFactory {

   @Override
   protected Collection<PrintableStringDecorator> usedGenerators() {
      List<PrintableStringDecorator> decorators = new ArrayList<>();
      decorators.add(new BoldFontDecorator());
      decorators.add(new ItalicFontDecorator());
      decorators.add(new UnderlineDecorator());
      decorators.add(new StrikeThroughDecorator());
      decorators.add(new BorderDecorator());
      decorators.add(new IntentTextDecorator(5));
      decorators.add(FontSizeDecorator.NORMAL);
      decorators.add(FontSizeDecorator.SMALL);
      decorators.add(FontSizeDecorator.BIG);
      decorators.add(FontSizeDecorator.VERYBIG);
      return decorators;
   }

}
