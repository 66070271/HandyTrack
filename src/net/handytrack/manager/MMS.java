/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package net.handytrack.manager;

import net.handytrack.HandyCell.ScrollPaneWin111;
import net.handytrack.HandyCell.TableActionCellEditor;
import net.handytrack.HandyCell.TableActionCellRender;
import net.handytrack.HandyCell.TableActionEvent;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.regex.PatternSyntaxException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;


/**
 *
 * @author FookNaRak
 */
public class MMS extends JFrame implements ActionListener {

    private JButton submitButton;
    private JPanel p1;
    public MMS() {
        initComponents();
        submitButton = new JButton("submit");
        p1 = new JPanel();
        //Border roundedBorder = BorderFactory.createLineBorder(Color.white, 2, true);
        //txtSearch.setBorder(roundedBorder);
        
        
        
        TableActionEvent event = new TableActionEvent() {
            @Override
            public void onSetting(int row) {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1) {
                    Object[] rowData = new Object[4];
                    //create JFrame
                    JFrame frame2 = new JFrame("Selected Row Data");
                    //create JFrame
                    frame2.setLayout(new GridLayout(model.getColumnCount(), 2));
                    for(int i=0; i<4; i++) {
                        rowData[i] = model.getValueAt(selectedRow, i).toString();
                        frame2.add(new JLabel(model.getColumnName(i) + ":"));
                        frame2.add(new JTextField(rowData[i].toString()));
                        
                    }
                    //create JFrame
                    frame2.add(p1);
                    frame2.add(submitButton);
                    frame2.pack();
                    frame2.setVisible(true);
                    frame2.setLocation(950, 200);
                    //create JFrame
                    
                    System.out.println("Selected row" + java.util.Arrays.toString(rowData));
                } else {
                    System.out.println("No Row Selected");
                }
            }

            @Override
            public void onDelete(int row) {
                if(table.isEditing()) {
                    table.getCellEditor().stopCellEditing();
                }
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(row);
            }

            @Override
            public void onPrint(int row) {
                DefaultTableModel model = (DefaultTableModel)table.getModel();
                int selectedRow = table.getSelectedRow();
                if(selectedRow != -1) {
                    Object[] rowData = new Object[10];

                    for(int i=0; i<10; i++) {
                        rowData[i] = model.getValueAt(selectedRow, i).toString();
                        
                    }
                    //setData to Another JFrame
                    JFrame keyData = new KeyData();
                    JLabel txtID = (JLabel) keyData.getContentPane().getComponent(17);
                    JLabel txtSenderName = (JLabel) keyData.getContentPane().getComponent(19);
                    JLabel txtReciverName = (JLabel) keyData.getContentPane().getComponent(11);
                    JLabel txtRoad = (JLabel) keyData.getContentPane().getComponent(10);
                    JLabel txtZipCode = (JLabel) keyData.getContentPane().getComponent(9);
                    JLabel txtDistrict = (JLabel) keyData.getContentPane().getComponent(12);
                    JLabel txtProvince = (JLabel) keyData.getContentPane().getComponent(13);
                    JLabel txtCost = (JLabel) keyData.getContentPane().getComponent(3);
                    JLabel txtType = (JLabel) keyData.getContentPane().getComponent(1);
                    JLabel txtTel = (JLabel) keyData.getContentPane().getComponent(16);
                    
                    txtID.setText(rowData[0].toString());
                    txtSenderName.setText(rowData[1].toString());
                    txtReciverName.setText(rowData[2].toString());
                    txtRoad.setText(rowData[3].toString());
                    txtZipCode.setText(rowData[4].toString());
                    txtDistrict.setText(rowData[5].toString());
                    txtProvince.setText(rowData[6].toString());
                    txtCost.setText(rowData[7].toString());
                    txtType.setText(rowData[8].toString());
                    txtTel.setText(rowData[9].toString());
                    ///////////////////////////////////////////////////////////////////////////////
                    keyData.setVisible(true);
                    printFrame(keyData);
                    
                } else {
                    System.out.println("No Row Selected");
                }
            }
            public void printFrame(JFrame frame) {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(new Printable() {
                    @Override
                    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                        if (pageIndex > 0) {
                            return Printable.NO_SUCH_PAGE;
                        }
                        Graphics2D g2d = (Graphics2D) graphics;
                    frame.print(g2d);
                    return Printable.PAGE_EXISTS;
                    }
                });
                boolean doPrint = job.printDialog();
                if(doPrint) {
                    try {
                        job.print();
                    } catch (PrinterException ex) {
                        ex.printStackTrace();
                    }
                }
            }
            
        };
        
        
        
        table.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
        table.getColumnModel().getColumn(10).setCellEditor(new TableActionCellEditor(event));
        
        //addListener addDataButton//
        AddData.addActionListener(this);
        submitButton.addActionListener(this);
        
        //DataTest//
        DefaultTableModel model = (DefaultTableModel)table.getModel();
        model.addRow(new Object[]{"1234567890", "Fook", "Mon", "Radkrabang", "12345", "Chalong Krung", "Bangkok", "100", "Freeze","9876543210"});
        model.addRow(new Object[]{"0987654321", "Mart", "Mon", "Radkrabang", "12345", "Chalong Krung", "Bangkok", "100", "Freeze","0123456789"});
        model.addRow(new Object[]{"1234567890", "Poom", "Mon", "Radkrabang", "12345", "Chalong Krung", "Bangkok", "100", "Freeze","0123456789"});
        model.addRow(new Object[]{"1234567890", "zeun", "Mon", "Radkrabang", "12345", "Chalong Krung", "Bangkok", "100", "Freeze","0123456789"});
        //DataTest//
        
        //SortingData in Row//
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(txtSearch.getText());
            }
            
            });
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent ev) {
        if(ev.getSource().equals(AddData)) {
            //use JFrame created by Auto
        }
        //create button on jframe
        if(ev.getSource().equals(submitButton)) {
            
        }
    }
    public void search(String text) {
        try{
            TableRowSorter<DefaultTableModel> rowSorter = (TableRowSorter<DefaultTableModel>) table.getRowSorter();
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        } catch (PatternSyntaxException e) {
            System.err.println("Invalid regex pattern: " + e.getMessage());
        }
    }

    
//
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new JPanel();
        scrollPaneWin1111 = new ScrollPaneWin111();
        table = new javax.swing.JTable();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        txtSearch = new JTextField();
        AddData = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Package");
        setBackground(new Color(210, 224, 251));
        setLocation(new java.awt.Point(300, 100));

        jPanel1.setBackground(new Color(210, 224, 251));
        jPanel1.setLayout(new java.awt.BorderLayout());

        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Sender Name", "Reciver Name", "Road", "Zip Code", "District", "Province", "Cost", "Type", "Tel.", "Config"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setOpaque(false);
        table.setRowHeight(50);
        table.setSelectionBackground(new Color(210, 224, 251));
        table.getTableHeader().setReorderingAllowed(false);
        scrollPaneWin1111.setViewportView(table);

        jPanel1.add(scrollPaneWin1111, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(new Color(210, 224, 251));

        jLabel1.setIcon(new javax.swing.ImageIcon("resources/image/icons8-magnifying-glass-15.png")); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(35, 35));
        jLabel1.setMinimumSize(new java.awt.Dimension(35, 35));

        AddData.setBackground(new Color(210, 224, 251));
        AddData.setIcon(new javax.swing.ImageIcon("resources/image/icons8-magnifying-glass-35.png")); // NOI18N
        AddData.setBorderPainted(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 927, Short.MAX_VALUE)
                .addComponent(AddData)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AddData, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        }  catch(Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
}
        //</editor-fold>
        //</editor-fold>
        //UIManager.put(args, args)

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MMS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton AddData;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private ScrollPaneWin111 scrollPaneWin1111;
    private javax.swing.JTable table;
    private JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}