package de.kreth.formatter;


public class MyFormatter {

   /**
    * Double have no padding and a precision of 6
    */
   public static final int DEFAULT_WIDTH = -1;
   private int width = DEFAULT_WIDTH;
   private int precision = DEFAULT_WIDTH;
      
   public int getPrecision() {
      return precision;
   }
   
   public void setPrecision(int precision) {
      this.precision = precision;
   }

   public int getWidth() {
      return width;
   }
   
   public void setWidth(int width) {
      this.width = width;
   }

   public String format(Object object) {

      String format = "";
      
      if (width != DEFAULT_WIDTH || precision != DEFAULT_WIDTH){
         if(width != DEFAULT_WIDTH)
            format += width;
         
         if(precision != DEFAULT_WIDTH) {
            format += "." + precision;
         }
         
      }
      
      if(object instanceof Double){
         if(format.length()>0 && !format.startsWith("."))
            format = " " + format;
            
         return String.format("%" + format + "f", object);
      }
      
      return object.toString();
   }
}
