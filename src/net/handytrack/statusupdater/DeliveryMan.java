package net.handytrack.statusupdater;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import java.sql.*;
import java.awt.event.*;
import java.time.format.*;
import java.time.*;

public class DeliveryMan implements ActionListener, ItemListener {
    private JFrame fr;
    private JTable table;
    private DefaultTableModel model;
    private JComboBox statussort;
    private JScrollPane scrollPane;
    private JPanel pn1, pn2, blank1, blank2;
    private JButton done, search, sdefault;
    private JTextField searchtf;
    private JLabel lb1, lb2;
    private StatusChanger sc;
    
    private DBconnect db;
    private String selectSort, selectStatus, time, timereceive, timesort, timetransit, timecomplete;

    public DeliveryMan() {
        fr = new JFrame("Delivery Status Updater");
        statussort = new JComboBox();
        statussort.addItem("-");
        statussort.addItem("Receive");
        statussort.addItem("Sorting");
        statussort.addItem("In Transit");
        statussort.addItem("Complete");
        statussort.setSelectedIndex(0);
        
        sc = new StatusChanger();
        
        String[] columnNames = {"ID", "Receiver", "Address", "Contact", "Status", "Action"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override ///ทำให้ Column อื่นๆที่ไม่ได้ Set ไว้แก้ไขไม่ได้
            public boolean isCellEditable(int row, int column) {
                // Make columns 4 and 5 editable
                return column == 5;
            }
        };
        
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        pn1 = new JPanel();
        pn2 = new JPanel();
        
        blank1 = new JPanel();
        blank2 = new JPanel();
        fr.add(blank1, BorderLayout.EAST);
        fr.add(blank2, BorderLayout.WEST);
        
        done = new JButton("Done!");        
        
        search = new JButton("Seach");
        sdefault = new JButton("Set Default");
        searchtf = new JTextField(20);
        lb1 = new JLabel("Sorted by Name or ID : ");
        lb2 = new JLabel("Sorted by Status : ");
         fr.add(pn2, BorderLayout.NORTH);
         pn2.add(lb2);
         pn2.add(statussort);
         pn2.add(lb1);
         pn2.add(searchtf);
         pn2.add(search);
         pn2.add(sdefault);


/////////////////////////////////// DateTime /////////////////////////////////////////////
        String sql = "SELECT * FROM handytrack.deliveryman;";
        setTable(sql);
        
        
/////////////////////////////// ขนาด Column ของ JTable  ////////////////////////////////
//        table.getColumnModel().getColumn(0).setPreferredWidth(10);
//        table.getColumnModel().getColumn(1).setPreferredWidth(20);
//        table.getColumnModel().getColumn(2).setPreferredWidth(90);
//        table.getColumnModel().getColumn(3).setPreferredWidth(50);
//        table.getColumnModel().getColumn(4).setPreferredWidth(40);
//        table.getColumnModel().getColumn(5).setPreferredWidth(50);

//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//        time = dtf.format(LocalDateTime.now());
//        System.out.println(time);

//        TableColumn statusColumn = table.getColumnModel().getColumn(4); // Status column index is now 4
//        statusColumn.setCellEditor(new DefaultCellEditor(status));
            
        // Custom TableCellRenderer for button in column 5
        TableColumn deleteColumn = table.getColumnModel().getColumn(5); // Action column index is 5
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor());

        fr.add(scrollPane, BorderLayout.CENTER);

        pn1.add(done);
        fr.add(pn1, BorderLayout.SOUTH);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        fr.pack();
        fr.setSize(1200, 600);
//        fr.setSize(1920, 1080);
        fr.setVisible(true);
        
/////////////////////////////// Set Font  ////////////////////////////////
        done.setFont(new Font("Arial", Font.BOLD, 14));
        
        search.addActionListener(this);
        sdefault.addActionListener(this);
        statussort.addItemListener(this);
        sc.getDone().addActionListener(this);
        
    }
    
    
    ///////////////////////////// SQL ////////////////////////////////////
    public void setTable(String sql){
        try {
            db = new DBconnect();
            ResultSet rs = db.getConnect(sql);
            while (rs.next()){
                String id = rs.getString("ID");
                String rname = rs.getString("NameR");
                String radd = rs.getString("Road");
                String dadd = rs.getString("District");
                String padd = rs.getString("Province");
                String zadd = rs.getString("Zip");
                String pnum = rs.getString("contactNum");
                String gstatus = rs.getString("Status");
                String address = (radd +  ", " + dadd +  ", " + padd + ", " + zadd);
                String[] row = {id, rname, address, pnum, gstatus, "Change"};
                model.addRow(row);
                }
            } 
            catch (Exception e) {
                    e.printStackTrace();
         }
        table.setModel(model);
    }
    
    /////////////////////////////// Get Time for Status Chagner GUI ////////////////////////////////////////////
    public String getSorttime(int state) {
        int selectedRow = table.getSelectedRow();
        String data1 = model.getValueAt(selectedRow, 0).toString();
        String data2 = model.getValueAt(selectedRow, 1).toString();
        String sql = String.format("SELECT * FROM handytrack.deliveryman WHERE NameR = '%s' OR ID = '%s'", data1, data2);
        try {
            db = new DBconnect();
            ResultSet rs = db.getConnect(sql);
            while (rs.next()){
                timereceive = rs.getString("ReceiveTime");
                timesort = rs.getString("SortingTime");
                timetransit = rs.getString("InTransitTime");
                timecomplete = rs.getString("CompleteTime");
                }
            } 
            catch (Exception e) {
                    e.printStackTrace();
         }
        if (state == 1) {
            return timereceive;
        }
        else if (state == 2) {
            return timesort;
        }
        else if (state == 3) {
            return timetransit;
        } 
        else if (state == 4) {
            return timecomplete;
        } 
        return "Error";
    }
    
    ///////////////////////////// EVENT (sort function) ////////////////////////////////////
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(search)){
            String kw = searchtf.getText();
            
            if (statussort.getSelectedIndex() == 0) {
                model.setRowCount(0);
                String searchh = String.format("SELECT * FROM handytrack.deliveryman WHERE NameR = '%s' OR ID = '%s'", kw, kw);
                setTable(searchh);
            }
            else if (kw.equals("")) {
                model.setRowCount(0);
                String sql = "SELECT * FROM handytrack.deliveryman;";
                setTable(sql);
            }
            else {
                model.setRowCount(0);
                String searchh = String.format("SELECT * FROM handytrack.deliveryman WHERE (NameR = '%s' OR ID = '%s') AND Status = '%s'", kw, kw, selectSort);
                setTable(searchh);
            }
        }
        else if(e.getSource().equals(sdefault)){
            searchtf.setText("");
            statussort.setSelectedIndex(0);
            model.setRowCount(0);
            String sql = "SELECT * FROM handytrack.deliveryman;";
            setTable(sql);
            }
        else if(e.getSource().equals(sc.getDone())){
            sc.getFr().dispose(); 
            
            int selectedRow = table.getSelectedRow();
            String data1 = model.getValueAt(selectedRow, 0).toString();
            String data2 = model.getValueAt(selectedRow, 1).toString();
            
            if (!sc.getStatus().equals("null")) {
                table.setValueAt(sc.getStatus() , selectedRow, 4);
            }
            else {
                System.out.println(sc.getStatus());
            }
                String sql = String.format("UPDATE handytrack.deliveryman SET Status = '%s' WHERE (ID = '%s' AND NameR = '%s')", sc.getStatus(), data1, data2);
                db.getUpdate(sql);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                time = dtf.format(LocalDateTime.now());
                
                if (sc.getStatus().equals("Receive")){
                    String statustime = String.format("UPDATE handytrack.deliveryman SET ReceiveTime = '%s' WHERE (ID = '%s' AND NameR = '%s')", time, data1, data2);
                    db.getUpdate(statustime);
                    sc.getRtime().setText(getSorttime(1));
                }
                else if (sc.getStatus().equals("Sorting")){
                    String statustime = String.format("UPDATE handytrack.deliveryman SET SortingTime = '%s' WHERE (ID = '%s' AND NameR = '%s')", time, data1, data2);
                    db.getUpdate(statustime);
                }
                else if (sc.getStatus().equals("In Transit")){
                    String statustime = String.format("UPDATE handytrack.deliveryman SET InTransitTime = '%s' WHERE (ID = '%s' AND NameR = '%s')", time, data1, data2);
                    db.getUpdate(statustime);
                }
                else if (sc.getStatus().equals("Complete")){
                    String statustime = String.format("UPDATE handytrack.deliveryman SET CompleteTime = '%s' WHERE (ID = '%s' AND NameR = '%s')", time, data1, data2);
                    db.getUpdate(statustime);
                }
                
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
//            String selectSort = (String) statussort.getSelectedItem();
//            String kw = searchtf.getText();
            selectSort = (String) statussort.getSelectedItem();
            model.setRowCount(0);
//            String searchh = String.format("SELECT * FROM handytrack.deliveryman WHERE (NameR = '%s' OR ID = '%s') AND Status = '%s'", kw, kw, selectSort);
//            setTable(searchh);
            if(selectSort.equals("-") & searchtf.getText().equals("")) {
                String sortt = "SELECT * FROM handytrack.deliveryman;";
                setTable(sortt);
            }
            else {
                String sortt = String.format("SELECT * FROM handytrack.deliveryman WHERE Status = '%s'", selectSort);
                setTable(sortt);
            }
//            if (statussort.getSelectedIndex() == 0 & searchtf.getText().equals("")) {
//                String sss = "SELECT * FROM handytrack.deliveryman";
//                setTable(sss);
//            }
//            else if (statussort.getSelectedIndex() == 0 & searchtf.getText().equals("") != true) {
//                String sss = "SELECT * FROM handytrack.deliveryman WHERE NameR = '%s' OR ID = '%s'";
//                setTable(sss);
//        }   
    }
    }
    
    public static void main(String[] args) {
            try {
                UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
            }  catch(Exception ex ) {
                System.err.println( "Failed to initialize LaF" );
    } 
            new DeliveryMan();
    }
            
           
 //////////////////// FOR JUST DELETE BUTTON ON 5TH COLUMN ////////////////
    class ButtonRenderer extends JButton implements TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Custom TableCellEditor for button
    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;

        public ButtonEditor() {
            button = new JButton("Change");
            button.addActionListener(e -> {
//                fireEditingStopped();
                int selectedRow = table.getSelectedRow();
                String currentstats = model.getValueAt(selectedRow, 4).toString();
                if (currentstats.equals("Receive")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(true);
                    sc.getInTransits().setEnabled(false);
                    sc.getCompletes().setEnabled(false);
                } 
                else if (currentstats.equals("Sorting")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(true);
                    sc.getCompletes().setEnabled(false);
                }
                else if (currentstats.equals("In Transit")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getCompletes().setEnabled(true);
                }
                else if (currentstats.equals("Complete")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getCompletes().setEnabled(false);
                }
                sc.getFr().setVisible(true);
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }
    }

}