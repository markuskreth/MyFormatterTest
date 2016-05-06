package de.kreth.formatter.testui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import de.kreth.formatter.table.Column;
import de.kreth.formatter.table.Column.VerticalAlign;
import de.kreth.formatter.table.Table;


public class TestPrintTableFrame extends JFrame implements ActionListener {

   private static final long serialVersionUID = 7776203471672169923L;
   
   private JPanel contentPane;
   private JTextField textFieldA1;
   private JTextField textFieldB1;
   private JTextField textFieldC1;
   private JTextField textFieldA2;
   private JTextField textFieldB2;
   private JTextField textFieldC2;
   private JTextField textFieldA3;
   private JTextField textFieldB3;
   private JTextField textFieldC3;
   private JPanel outputPane;
   
   private Table table;
      
   /**
    * Launch the application.
    */
   public static void main(String[] args) {

//      TestPrintTableFrame frame = new TestPrintTableFrame();
//      frame.setVisible(true);
//      
      EventQueue.invokeLater(new Runnable() {

         @Override
         public void run() {
            try {
               TestPrintTableFrame frame = new TestPrintTableFrame();
               frame.setVisible(true);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public TestPrintTableFrame() {
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 635, 443);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(new BorderLayout(0, 0));
      setContentPane(contentPane);
      
      JPanel panelInput = new JPanel();
      contentPane.add(panelInput, BorderLayout.WEST);
      panelInput.setLayout(new GridLayout(3, 3, 0, 0));
      
      textFieldA1 = new JTextField();
      panelInput.add(textFieldA1);
      textFieldA1.setColumns(10);
      textFieldA1.setText("Markus");
      
      textFieldB1 = new JTextField();
      panelInput.add(textFieldB1);
      textFieldB1.setColumns(10);
      textFieldB1.setText("21.08.1973");
      
      textFieldC1 = new JTextField();
      panelInput.add(textFieldC1);
      textFieldC1.setColumns(10);
      textFieldC1.setText("180.0");
      
      textFieldA2 = new JTextField();
      panelInput.add(textFieldA2);
      textFieldA2.setColumns(10);
      textFieldA2.setText("Jasmin");
      
      textFieldB2 = new JTextField();
      panelInput.add(textFieldB2);
      textFieldB2.setColumns(10);
      textFieldB2.setText("14.09.1986");
      
      textFieldC2 = new JTextField();
      panelInput.add(textFieldC2);
      textFieldC2.setColumns(10);
      textFieldC2.setText("-76.871681");
      
      textFieldA3 = new JTextField();
      panelInput.add(textFieldA3);
      textFieldA3.setColumns(10);
      textFieldA3.setText("Anna Langenhagen");
      
      textFieldB3 = new JTextField();
      panelInput.add(textFieldB3);
      textFieldB3.setColumns(10);
      textFieldB3.setText(getClass().getName());
      
      textFieldC3 = new JTextField();
      panelInput.add(textFieldC3);
      textFieldC3.setColumns(10);
      textFieldC3.setText(String.valueOf(Integer.MAX_VALUE));
      
      outputPane = new MyPane();
      contentPane.add(outputPane, BorderLayout.CENTER);
      outputPane.setLayout(null);
      
      JPanel southPane = new JPanel(new GridLayout(1, 2));

      JButton b = new JButton("Print!");
      b.setActionCommand("print");
      b.addActionListener(this);
      southPane.add(b);
      
      b = new JButton("Paint!");
      b.setActionCommand("paint");
      b.addActionListener(this);
      southPane.add(b);
      
      contentPane.add(southPane, BorderLayout.SOUTH);
      
      table = new Table();
      table.setPrintHeader(true);
      
      Column colA = new Column.Builder().setColumnName("A").setColumnWidth(10).setVerticalAlign(VerticalAlign.RIGHT).build();
      Column colB = new Column.Builder().setColumnName("B").setColumnWidth(10).setVerticalAlign(VerticalAlign.RIGHT).build();
      Column colC = new Column.Builder().setColumnName("C").setColumnWidth(15).setVerticalAlign(VerticalAlign.CENTER).setFormat(NumberFormat.getNumberInstance()).build();
      table.add(colA);
      table.add(colB);
      table.add(colC);
      
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      table.clearRows();
      
      Object[] row1 = new Object[3];
      row1[0] = textFieldA1.getText();
      row1[1] = textFieldB1.getText();
      row1[2] = Double.valueOf(textFieldC1.getText());
      table.add(row1);
      
      Object[] row2 = new Object[3];
      row2[0] = textFieldA2.getText();
      row2[1] = textFieldB2.getText();      
      row2[2] = Double.valueOf(textFieldC2.getText());
      table.add(row2);
      
      Object[] row3 = new Object[3];
      row3[0] = textFieldA3.getText();
      row3[1] = textFieldB3.getText();
      row3[2] = Double.valueOf(textFieldC3.getText());
      table.add(row3);

      if(e.getActionCommand().equals("paint"))
         outputPane.repaint();

      if(e.getActionCommand().equals("print"))
         paintTable();
      
   }
   
   private void paintTable() {

      PrinterJob pj = PrinterJob.getPrinterJob();
      if (pj.printDialog()) {
         PageFormat pf = pj.defaultPage();
         pj.setPrintable(table, pf);
         
            new Thread(new Runnable() {
               
               
               @Override
               public void run() {
                  try {
                     pj.print();                     
                  } catch (PrinterException e) {
                     outputPane.add(new JTextField(e.toString()));
                  }
               }
            }).start();
      }
   }

   private class MyPane extends JPanel {
      
      
      private static final long serialVersionUID = 3916298199304507906L;
      
      @Override
      protected void paintComponent(Graphics g) {         
         Graphics create = g.create();
         super.paintComponent(create);
         table.print(create);
      }
      
   }

}
