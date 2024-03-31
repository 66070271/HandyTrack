package net.handytrack.customer;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBmanipulation;
import net.handytrack.database.DBquery;

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

public class CustomerManagement implements ActionListener {
    private JFrame fr;
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JPanel pn1, pn2, blank1, blank2;
    private JButton done, search, sdefault;
    private JTextField searchtf;
    private JLabel lb2;

    public CustomerManagement() {
        fr = new JFrame("Customer Updater");
        String[] columnNames = { "Customer",  "Contact",  "Action"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override ///ทำให้ Column อื่นๆที่ไม่ได้ Set ไว้แก้ไขไม่ได้
            public boolean isCellEditable(int row, int column) {
                // Make columns 4 and 5 editable
                return column == 2;
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
        lb2 = new JLabel("Sorted by Customer Name or Phone Number : ");
        fr.add(pn2, BorderLayout.NORTH);
        pn2.add(lb2);
        pn2.add(searchtf);
        pn2.add(search);
        pn2.add(sdefault);


/////////////////////////////////// JTable Set /////////////////////////////////////////////
        String sql = "SELECT * FROM customer;";
        setTable(sql);

/////////////////////////////// ขนาด Column ของ JTable  ////////////////////////////////
//        table.getColumnModel().getColumn(0).setPreferredWidth(10);
//        table.getColumnModel().getColumn(1).setPreferredWidth(20);
//        table.getColumnModel().getColumn(2).setPreferredWidth(90);
//        table.getColumnModel().getColumn(3).setPreferredWidth(50);
//        table.getColumnModel().getColumn(4).setPreferredWidth(40);
//        table.getColumnModel().getColumn(5).setPreferredWidth(50);

        // Custom TableCellRenderer for button in column 5
        TableColumn deleteColumn = table.getColumnModel().getColumn(2); // Action column index is 5
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor());

        fr.add(scrollPane, BorderLayout.CENTER);

        pn1.add(done);
        fr.add(pn1, BorderLayout.SOUTH);

        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        fr.pack();
        fr.setSize(800, 600);
        fr.setVisible(true);

/////////////////////////////// Set Font  ////////////////////////////////
//        done.setFont(new Font("Arial", Font.BOLD, 14));

        search.addActionListener(this);
        sdefault.addActionListener(this);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new CustomerManagement();
    }

    ///////////////////////////// SQL ////////////////////////////////////
    public void setTable(String sql) {
        try {
            ResultSet rs = DBquery.getInstance().getSelect(sql);
            while (rs.next()) {
                String sname = rs.getString("NameS");
                String pnum = rs.getString("contactNum");
                String[] row = { sname,  pnum,  "Edit"};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setModel(model);
    }

    ///////////////////////////// EVENT (sort function) ////////////////////////////////////
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(search)) {
            String kw = searchtf.getText();
            if (kw.equals("")) {
                model.setRowCount(0);
                String sql = "SELECT * FROM customer;";
                setTable(sql);
            } else {
                model.setRowCount(0);
                String searchh = String.format("SELECT * FROM customer WHERE (NameS = '%s' OR contactNum = '%s')", kw, kw);
                setTable(searchh);
            }
        } else if (e.getSource().equals(sdefault)) {
            searchtf.setText("");
            model.setRowCount(0);
            String sql = "SELECT * FROM customer;";
            setTable(sql);
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
            button = new JButton("Edit");
            button.addActionListener(e -> {
//                fireEditingStopped();
                int selectedRow = table.getSelectedRow();
                /// RIGHT HERE (Time in CurrentStats GUI)
                new CustomerGUI();
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