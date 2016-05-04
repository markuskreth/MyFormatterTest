package de.kreth.formatter.testui;

import java.awt.*;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import de.kreth.formatter.textdecorator.*;

public class TestPrintFrame extends JFrame {

   AbstractDecoratorFactory factory = new AllDecoratorsFactory();
   
   /**
    * 
    */
   private static final long serialVersionUID = 6300662082534115115L;
   private InternalPrintPanel printPanel;
   private JTextField textInput;
   private CheckboxListRenderer renderer;
   private DefaultListModel<CheckboxListItem> listModel;
   private List<PrintableStringDecorator> selectedDecorators;

   public TestPrintFrame() {
      super(TestPrintFrame.class.getSimpleName());
      setBounds(20, 20, 500, 500);
      textInput = new JTextField();
      
      selectedDecorators = new ArrayList<>();
      listModel = new DefaultListModel<>();
      renderer = new CheckboxListRenderer();
      
      final JList<CheckboxListItem> jList = new JList<>(listModel);
      jList.setCellRenderer(renderer);     
      jList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
      jList.addMouseListener(new MouseAdapter() {
         
         @Override
         public void mouseClicked(MouseEvent event) {
 
            // Get index of item clicked
 
            int index = jList.locationToIndex(event.getPoint());
            CheckboxListItem item = listModel
                  .getElementAt(index);
 
            // Toggle selected state
 
            item.setSelected(!item.isSelected());
 
            if(item.isSelected)
               selectedDecorators.add(item.decorator);
            else
               selectedDecorators.remove(item.decorator);
            
            // Repaint cell 
            jList.repaint(jList.getCellBounds(index, index));
         }
      });
      JPanel mainPanel = new JPanel();
      mainPanel.setLayout(new BorderLayout());
      JPanel topPanel = new JPanel();
      topPanel.setLayout(new FlowLayout());
      textInput.setColumns(20);
      
      topPanel.add(textInput);
      
      final JButton printCmd = new JButton("Print!");
      printCmd.setEnabled(false);
      printCmd.addActionListener(new ActionListener() {
         
         
         @Override
         public void actionPerformed(ActionEvent e) {
            print();
         }
      });
      topPanel.add(printCmd);
      
      mainPanel.add(topPanel, BorderLayout.NORTH);
      this.printPanel = new InternalPrintPanel();
      
      mainPanel.add(printPanel, BorderLayout.CENTER);
      mainPanel.add(new JScrollPane(jList), BorderLayout.EAST);

      this.setContentPane(mainPanel);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      textInput.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            if(textInput.getText().length()>0)
               printCmd.setEnabled(true);
            
            PrintableText text = new PrintableText(textInput.getText());
            text.addAll(selectedDecorators);
            printPanel.setText(text);
         }
      });

      for (PrintableStringDecorator deco : factory.getDecorators())
         listModel.addElement(new CheckboxListItem(deco));
   }

   public void print() {

      PrintableText text = new PrintableText(textInput.getText());
      text.addAll(selectedDecorators);
      
      PrinterJob pj = PrinterJob.getPrinterJob();
      if (pj.printDialog()) {
         PageFormat pf = pj.defaultPage();
         pj.setPrintable(text, pf);
         
            new Thread(new Runnable() {
               
               
               @Override
               public void run() {
                  try {
                     pj.print();                     
                  } catch (PrinterException e) {
                     printPanel.setText(new PrintableText(e.toString()));
                  }
               }
            }).start();
      }
   }
   
   private class InternalPrintPanel extends JPanel {

      private static final long serialVersionUID = -3814420901381936250L;
      private PrintableText text;
      private PageFormat pg = PrinterJob.getPrinterJob().defaultPage();
      

      public void setText(PrintableText text) {
         this.text = text;
         repaint();
      }

      @Override
      protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         int width = Double.valueOf(pg.getImageableWidth()).intValue();
         int height = Double.valueOf(pg.getImageableHeight()).intValue();
         g.drawRect(10, 10, width, height);
         
         Graphics g2 = g.create(10, 10, width, height);
         
         if (text != null) {
            
            try {
               text.print(g2, null, 0);
            } catch (Exception e) {
               g.drawString(e.toString(), 0, 0);
            }
         }
      }
   }

   public static void main(String[] args) {
      new TestPrintFrame().setVisible(true);
   }
   // Represents items in the list that can be selected

   class CheckboxListItem {

      private PrintableStringDecorator decorator;
      private boolean isSelected = false;

      public CheckboxListItem(PrintableStringDecorator decorator) {
         this.decorator = decorator;
      }

      public boolean isSelected() {
         return isSelected;
      }

      public void setSelected(boolean isSelected) {
         this.isSelected = isSelected;
      }

      @Override
      public String toString() {
         return decorator.name();
      }
   }

   class CheckboxListRenderer extends JCheckBox implements ListCellRenderer<CheckboxListItem> {
            
      /**
       * 
       */
      private static final long serialVersionUID = 8306132138013157792L;

      @Override
      public Component getListCellRendererComponent(JList<? extends CheckboxListItem> list, CheckboxListItem value, int index, boolean isSelected, boolean cellHasFocus) {
         
         setEnabled(list.isEnabled());
         setSelected(value.isSelected());
         setFont(list.getFont());
         setBackground(list.getBackground());
         setForeground(list.getForeground());
         setText(value.toString());
         return this;
      }
   }
}
