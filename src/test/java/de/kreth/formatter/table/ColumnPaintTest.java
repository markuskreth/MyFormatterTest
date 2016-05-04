package de.kreth.formatter.table;

import static org.mockito.Mockito.mock;

import java.awt.Graphics;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class ColumnPaintTest {

   private Graphics g;
   
   @BeforeClass
   public static void setUpBeforeClass() throws Exception {}

   @Before
   public void setUp() throws Exception {
      g = mock(Graphics.class);
   }

   @Test
   public void test() {
      Rectangle bounds = new Rectangle(0, 0, 20, 10);
//      Column col = new Column();
//      col.paint(g, bounds);
   }

}
