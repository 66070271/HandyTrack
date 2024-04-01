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
    private String selectSort, time;

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

        search = new JButton("Search");
        sdefault = new JButton("Set Default");
        searchtf = new JTextField(20);
        lb1 = new JLabel("Sorted by Name or Track Number : ");
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
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(150);
        table.getColumnModel().getColumn(3).setPreferredWidth(20);
        table.getColumnModel().getColumn(4).setPreferredWidth(10);
        table.getColumnModel().getColumn(5).setPreferredWidth(10);
        table.getColumnModel().getColumn(6).setPreferredWidth(1);


        // Custom TableCellRenderer for button in column 5
        TableColumn deleteColumn = table.getColumnModel().getColumn(6); // Action column index is 5
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor());

        fr.add(scrollPane, BorderLayout.CENTER);

        pn1.add(done);
        fr.add(pn1, BorderLayout.SOUTH);
        pn1.setBackground(new Color(210, 224, 251));
        pn2.setBackground(new Color(210, 224, 251));
        blank1.setBackground(new Color(210, 224, 251));
        blank2.setBackground(new Color(210, 224, 251));

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(1400, 750);
        fr.setVisible(true);

/////////////////////////////// Listener for Event  ////////////////////////////////
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
                String rname = rs.getString("NameR");
                String radd = rs.getString("Road");
                String dadd = rs.getString("District");
                String padd = rs.getString("Province");
                String zadd = rs.getString("Zip");
                String pnum = rs.getString("contactNum");
                String gtype = rs.getString("Type");
                String gstatus = rs.getString("Status");
                String address = (radd + ", " + dadd + ", " + padd + ", " + zadd);
                String[] row = {trackn, rname, address, pnum, gtype, gstatus, "Update"};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setModel(model);
    }

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
        } else if (e.getSource().equals(done)) {
            fr.dispose();
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            selectSort = (String) statussort.getSelectedItem();
            model.setRowCount(0);
            if (selectSort.equals("-") & searchtf.getText().equals("")) {
                String sortt = "SELECT * FROM product;";
                setTable(sortt);
            } else {
                String sortt = String.format("SELECT * FROM product WHERE Status = '%s'", selectSort);
                setTable(sortt);
            }
        }
    }

    //////////////////// BUTTON ON 7th COLUMN ////////////////
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

    class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
        private JButton button;

        public ButtonEditor() {
            button = new JButton("Update");
            button.addActionListener(e -> {
                fireEditingStopped();
                int selectedRow = table.getSelectedRow();
                String currentstats = model.getValueAt(selectedRow, 5).toString();
                String tracknm = model.getValueAt(selectedRow, 0).toString();

                String rname = model.getValueAt(selectedRow, 1).toString();
                String address = model.getValueAt(selectedRow, 2).toString();
                String contact = model.getValueAt(selectedRow, 3).toString();
                String type = model.getValueAt(selectedRow, 4).toString();
                String status = model.getValueAt(selectedRow, 5).toString();

                sc.getTrack().setText(String.format("<html><b>Track : %s<b/><html>", tracknm));
                sc.getReceiver().setText(String.format("Receiver : %s", rname));
                sc.getContact().setText(String.format("Contact : %s", contact));
                sc.getAddress().setText(String.format("Address : %s", address));
                sc.getTypes().setText(String.format("Type : %s", type));
                sc.getStatuss().setText(String.format("Current Status : %s", status));

                //// RadioButton
                if (currentstats.equals("Receive")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(true);
                    sc.getInTransits().setEnabled(false);
                    sc.getCompletes().setEnabled(false);
                    sc.getDeliverys().setEnabled(false);
                } else if (currentstats.equals("Sorting")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(true);
                    sc.getDeliverys().setEnabled(false);
                    sc.getCompletes().setEnabled(false);
                } else if (currentstats.equals("In Transit")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getDeliverys().setEnabled(true);
                    sc.getCompletes().setEnabled(false);
                } else if (currentstats.equals("Delivery")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getDeliverys().setEnabled(false);
                    sc.getCompletes().setEnabled(true);
                } else if (currentstats.equals("Complete")) {
                    sc.getReceives().setEnabled(false);
                    sc.getSortings().setEnabled(false);
                    sc.getInTransits().setEnabled(false);
                    sc.getDeliverys().setEnabled(false);
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
            return "Update";
        }
    }

}