package de.kreth.formatter.testdata;

import java.text.DateFormat;
import java.util.Date;

import de.kreth.formatter.ToStringLeveled;


public class MyDate extends Date implements ToStringLeveled {

   private static final long serialVersionUID = 7981836098498818616L;
   
   private Format toStringFormat = null;

   @Override
   public String toString(Format f) {
      return formatted(f);
   }

   @Override
   public void setToStringLevel(Format f) {
      this.toStringFormat = f;
   }

   @Override
   public String toString() {
      if(toStringFormat == null)
         return super.toString();
       
      return formatted(toStringFormat);
      
   }

   private String formatted(Format toStringFormat2) {
      switch (toStringFormat2) {
         case Short:
            return DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(this);
         case Medium:
            return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(this);
         case Debug:
         case Full:
         default:
            return DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).format(this);
      }
   }
   
}
