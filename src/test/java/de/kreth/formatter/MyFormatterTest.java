package de.kreth.formatter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("boxing")
public class MyFormatterTest {

   private MyFormatter formatter;
   
   @Before
   public void setUp() throws Exception {
      this.formatter = new MyFormatter();
   }

   @Test
   public void testSystemFormatter() {
      String formatted = String.format("%f", 1.012345);
      assertEquals("1,012345", formatted);
      formatted = String.format("%.3f", 1.012345);
      assertEquals("1,012", formatted);
      formatted = String.format("%.10f", 1.0123456);
      assertEquals("1,0123456000", formatted);
   }

   @Test
   public void testSystemPadding() {
      String formatted = String.format("%-10f", 1.012345);
      assertEquals("1,012345  ", formatted);
      formatted = String.format("%10.3f", 1.012345);
      assertEquals("     1,012", formatted);
   }

   @Test
   public void testMyDefaultDecimalPadding() {
      String formatted = formatter.format(1.012345);
      assertEquals("1,012345", formatted);
   }

   @Test
   public void testMyDecimalPrecision2() {
      formatter.setPrecision(2);
      String formatted = formatter.format(1.012345);
      assertEquals("1,01", formatted);
   }

   @Test
   public void testMyDecimalPadding10() {
      formatter.setWidth(10);
      String formatted = formatter.format(1.0123);
      assertEquals("    1,0123", formatted);
   }

   @Test
   public void testMyDefaultStringPadding() {
      String input = "This is the inputstring";
      
      String formatted = formatter.format(input);
      assertEquals(input, formatted);
   }

   @Test
   public void testMyDefaultIntegerPadding() {      
      String formatted = formatter.format(123);
      assertEquals("123", formatted);
   }

}

