package net.handytrack.statusupdater;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBmanipulation;
import net.handytrack.database.DBquery;
import net.handytrack.tracker.TrackInfo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    private String selectSort, time, timereceive, timesort, timetransit, timedelivery, timecomplete;

    public DeliveryMan() {
        fr = new JFrame("Delivery Status Updater");
        statussort = new JComboBox();
        statussort.addItem("-");
        statussort.addItem("Receive");
        statussort.addItem("Sorting");
        statussort.addItem("In Transit");
        statussort.addItem("Delivery");
        statussort.addItem("Complete");
        statussort.setSelectedIndex(0);

        sc = new StatusChanger();

        String[] columnNames = {"Track Number", "Receiver", "Address", "Contact", "Type", "Status", "Action"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override ///ทำให้ Column อื่นๆที่ไม่ได้ Set ไว้แก้ไขไม่ได้
            public boolean isCellEditable(int row, int column) {
                // Make columns 6 editable
                return column == 6;
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


/////////////////////////////////// JTable Set /////////////////////////////////////////////
        String sql = "SELECT * FROM product;";
        setTable(sql);


/////////////////////////////// ขนาด Column ของ JTable  ////////////////////////////////
//        table.getColumnModel().getColumn(0).setPreferredWidth(10);
//        table.getColumnModel().getColumn(1).setPreferredWidth(20);
//        table.getColumnModel().getColumn(2).setPreferredWidth(90);
//        table.getColumnModel().getColumn(3).setPreferredWidth(50);
//        table.getColumnModel().getColumn(4).setPreferredWidth(40);
//        table.getColumnModel().getColumn(5).setPreferredWidth(50);


        // Custom TableCellRenderer for button in column 5
        TableColumn deleteColumn = table.getColumnModel().getColumn(6); // Action column index is 5
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
//        done.setFont(new Font("Arial", Font.BOLD, 14));

        search.addActionListener(this);
        sdefault.addActionListener(this);
        statussort.addItemListener(this);
        sc.getDone().addActionListener(this);

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new DeliveryMan();
    }

    ///////////////////////////// SQL ////////////////////////////////////
    public void setTable(String sql) {
        try {
            ResultSet rs = DBquery.getInstance().getSelect(sql);
            while (rs.next()) {
                String trackn = rs.getString("TrackNum");
//                String id = rs.getString("ID");
                String rname = rs.getString("NameR");
                String radd = rs.getString("Road");
                String dadd = rs.getString("District");
                String padd = rs.getString("Province");
                String zadd = rs.getString("Zip");
                String pnum = rs.getString("contactNum");
                String gtype = rs.getString("Type");
                String gstatus = rs.getString("Status");
                String address = (radd + ", " + dadd + ", " + padd + ", " + zadd);
                String[] row = {trackn, rname, address, pnum, gtype, gstatus, "Change"};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setModel(model);
    }

    /////////////////////////////// Get Time for Status Chagner GUI ////////////////////////////////////////////
//    public String getSorttime(int state, String s) {
//        String sql = String.format("SELECT * FROM trackinfo WHERE TrackNum = '%s'", s);
//        try {
//            ResultSet rs = DBquery.getInstance().getSelect(sql);
//            if (rs.next()) {
//                String timereceive = rs.getString("Recieved");
//                String timesort = rs.getString("Sorting");
//                String timetransit = rs.getString("Transit");
//                String timedelivery = rs.getString("Delivery");
//                String timecomplete = rs.getString("Finish");
//                if (state == 1) {
//                    return timereceive;
//                } else if (state == 2) {
//                    return timesort;
//                } else if (state == 3) {
//                    return timetransit;
//                } else if (state == 4) {
//                    return timedelivery;
//                } else if (state == 5) {
//                    return timecomplete;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "Error";
//    }

    ///////////////////////////// EVENT (sort function) ////////////////////////////////////
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(search)) {
            String kw = searchtf.getText();

            if (statussort.getSelectedIndex() == 0) {
                model.setRowCount(0);
                String searchh = String.format("SELECT * FROM product WHERE NameR = '%s' OR TrackNum = '%s'", kw, kw);
                setTable(searchh);
            } else if (kw.equals("")) {
                model.setRowCount(0);
                String sql = "SELECT * FROM product;";
                setTable(sql);
            } else {
                model.setRowCount(0);
                String searchh = String.format("SELECT * FROM product WHERE (NameR = '%s' OR TrackNum = '%s') AND Status = '%s'", kw, kw, selectSort);
                setTable(searchh);
            }
        } else if (e.getSource().equals(sdefault)) {
            searchtf.setText("");
            statussort.setSelectedIndex(0);
            model.setRowCount(0);
            String sql = "SELECT * FROM product;";
            setTable(sql);
        } else if (e.getSource().equals(sc.getDone())) {
            sc.getFr().dispose();

            int selectedRow = table.getSelectedRow();
            String data1 = model.getValueAt(selectedRow, 0).toString();

            if (!sc.getStatus().equals("null")) {
                table.setValueAt(sc.getStatus(), selectedRow, 5);
            } else {
                System.out.println(sc.getStatus());
            }
            String sql = String.format("UPDATE product SET Status = '%s' WHERE TrackNum = '%s'", sc.getStatus(), data1);
            DBmanipulation.getInstance().getUpdate(sql);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            time = dtf.format(LocalDateTime.now());

            if (sc.getStatus().equals("Receive")) {
                String statustime = String.format("UPDATE trackinfo SET Recieved = '%s' WHERE TrackNum = '%s'", time, data1);
                DBmanipulation.getInstance().getUpdate(statustime);
            } else if (sc.getStatus().equals("Sorting")) {
                String statustime = String.format("UPDATE trackinfo SET Sorting = '%s' WHERE TrackNum = '%s'", time, data1);
                DBmanipulation.getInstance().getUpdate(statustime);
            } else if (sc.getStatus().equals("In Transit")) {
                String statustime = String.format("UPDATE trackinfo SET Transit = '%s' WHERE TrackNum = '%s'", time, data1);
                DBmanipulation.getInstance().getUpdate(statustime);
            } else if (sc.getStatus().equals("Delivery")) {
                String statustime = String.format("UPDATE trackinfo SET Delivery = '%s' WHERE TrackNum = '%s'", time, data1);
                DBmanipulation.getInstance().getUpdate(statustime);
            } else if (sc.getStatus().equals("Complete")) {
                String statustime = String.format("UPDATE trackinfo SET Finish = '%s' WHERE TrackNum = '%s'", time, data1);
                DBmanipulation.getInstance().getUpdate(statustime);
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
//            String searchh = String.format("SELECT * FROM product WHERE (NameR = '%s' OR ID = '%s') AND Status = '%s'", kw, kw, selectSort);
//            setTable(searchh);
            if (selectSort.equals("-") & searchtf.getText().equals("")) {
                String sortt = "SELECT * FROM product;";
                setTable(sortt);
            } else {
                String sortt = String.format("SELECT * FROM product WHERE Status = '%s'", selectSort);
                setTable(sortt);
            }
//            if (statussort.getSelectedIndex() == 0 & searchtf.getText().equals("")) {
//                String sss = "SELECT * FROM product";
//                setTable(sss);
//            }
//            else if (statussort.getSelectedIndex() == 0 & searchtf.getText().equals("") != true) {
//                String sss = "SELECT * FROM product WHERE NameR = '%s' OR ID = '%s'";
//                setTable(sss);
//        }
        }
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
                fireEditingStopped();
                int selectedRow = table.getSelectedRow();
                String currentstats = model.getValueAt(selectedRow, 5).toString();
                String tracknm = model.getValueAt(selectedRow, 0).toString();
//                String rtime = getSorttime(1, tracknm);
//                String stime = getSorttime(2, tracknm);
//                String ttime = getSorttime(3, tracknm);
//                String dtime = getSorttime(4, tracknm);
//                String ctime = getSorttime(5, tracknm);


                /// RIGHT HERE (Time in CurrentStats GUI)
                if (currentstats.equals("Receive")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(true);
                    sc.getInTransits().setEnabled(false);
                    sc.getCompletes().setEnabled(false);
                    sc.getDeliverys().setEnabled(false);
//                    sc.getRtime().setText(rtime);
                    sc.getStime().setText("");
                    sc.getTtime().setText("");
                    sc.getDtime().setText("");
                    sc.getCtime().setText("");
                } else if (currentstats.equals("Sorting")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(true);
                    sc.getDeliverys().setEnabled(false);
                    sc.getCompletes().setEnabled(false);
//                    sc.getRtime().setText(rtime);
//                    sc.getStime().setText(stime);
                    sc.getTtime().setText("");
                    sc.getDtime().setText("");
                    sc.getCtime().setText("");
                } else if (currentstats.equals("In Transit")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getDeliverys().setEnabled(true);
                    sc.getCompletes().setEnabled(false);
//                    sc.getRtime().setText(rtime);
//                    sc.getStime().setText(stime);
//                    sc.getTtime().setText(ttime);
                    sc.getDtime().setText("");
                    sc.getCtime().setText("");
                } else if (currentstats.equals("Delivery")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getDeliverys().setEnabled(false);
                    sc.getCompletes().setEnabled(true);
//                    sc.getRtime().setText(rtime);
//                    sc.getStime().setText(stime);
//                    sc.getTtime().setText(ttime);
//                    sc.getDtime().setText(dtime);
                    sc.getCtime().setText("");
                } else if (currentstats.equals("Complete")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getDeliverys().setEnabled(false);
                    sc.getCompletes().setEnabled(false);
//                    sc.getRtime().setText(rtime);
//                    sc.getStime().setText(stime);
//                    sc.getTtime().setText(ttime);
//                    sc.getDtime().setText(dtime);
//                    sc.getCtime().setText(ctime);
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