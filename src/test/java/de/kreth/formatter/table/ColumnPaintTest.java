package de.kreth.formatter.table;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.kreth.formatter.table.Column.VerticalAlign;


public class ColumnPaintTest {

   private Graphics g;
   private FontMetrics metrics;
   
   @BeforeClass
   public static void setUpBeforeClass() throws Exception {}

   @Before
   public void setUp() throws Exception {
      g = mock(Graphics.class);
      metrics = mock(FontMetrics.class);
      when(g.getFontMetrics()).thenReturn(metrics);
   }

   @Test
   public void testPaintLeftText() {
	  when(metrics.stringWidth(anyString())).thenReturn(12);
      Rectangle bounds = new Rectangle(0, 0, 20, 10);
      Column col = new Column.Builder().setColumnName("Column 1").setVerticalAlign(VerticalAlign.LEFT).setColumnWidth(12).build();
      
      col.paint(g, bounds, "Content 1");
      verify(g).clipRect(0, 0, 20, 10);
      verify(g).drawString("Content 1", 0, 0);
   }

   @Test
   public void testPaintRightText() {
	  when(metrics.stringWidth(anyString())).thenReturn(12);
      Rectangle bounds = new Rectangle(0, 0, 20, 10);
      Column col = new Column.Builder().setColumnName("Column 1").setVerticalAlign(VerticalAlign.RIGHT).setColumnWidth(12).build();
      
      col.paint(g, bounds, "Content 1");
      verify(g).clipRect(0, 0, 20, 10);
      verify(g).drawString("Content 1", 8, 0);
   }

   @Test
   public void testPaintCenterText() {
	  when(metrics.stringWidth(anyString())).thenReturn(12);
      Rectangle bounds = new Rectangle(0, 0, 20, 10);
      Column col = new Column.Builder().setColumnName("Column 1").setVerticalAlign(VerticalAlign.CENTER).setColumnWidth(12).build();
      
      col.paint(g, bounds, "Content 1");
      verify(g).clipRect(0, 0, 20, 10);
      verify(g).drawString("Content 1", 4, 0);
   }

}
