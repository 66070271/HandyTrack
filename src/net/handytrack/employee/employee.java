package net.handytrack.employee;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.customer.CustomerGUI;
import net.handytrack.customer.CustomerManagement;
import net.handytrack.database.DBquery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class employee implements ActionListener {
    private String name;
    private String tel;
    private ResultSet rs;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;
    private JFrame frame;
    private JPanel panel1, panel2, space1, space2;
    private JButton done, search, sdefault;
    private JTextField searchtf;
    private JLabel lb2;

    public employee() {
        frame = new JFrame("Employee Management");

        panel1 = new JPanel();
        panel2 = new JPanel();
        table = new JTable();
        space1 = new JPanel();
        space2 = new JPanel();


        search = new JButton("Search");
        sdefault = new JButton("Set Default");
        searchtf = new JTextField(20);
        lb2 = new JLabel("Sorted by Customer Name or Phone Number : ");
        frame.add(panel2, BorderLayout.NORTH);
        panel2.add(lb2);
        panel2.add(searchtf);
        panel2.add(search);
        panel2.add(sdefault);

        String sql = "SELECT* FROM login";
        setTable(sql);

        JTable table = new JTable(model);
        scrollPane = new JScrollPane(table);

        TableColumn deleteColumn = table.getColumnModel().getColumn(6); // Action column index is 5
        deleteColumn.setCellRenderer(new employee.ButtonRenderer());
        deleteColumn.setCellEditor(new employee.ButtonEditor());

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        search.addActionListener(this);
        sdefault.addActionListener(this);
    }

    public void setTable(String sql) {
        String[] col = {"Username", "Name", "Surname", "Telephone", "Email", "Job position", "Setting"};
        model = new DefaultTableModel(col, 0);
        try {
            ResultSet rs = DBquery.getInstance().getSelect(sql);
            while (rs.next()) {
                String user = rs.getString("username");
                String name = rs.getString("name");
                String surname = rs.getString("surename");
                String tel = rs.getString("tel");
                String mail = rs.getString("email");
                String job = rs.getString("jobposition");
                String[] row = {user, name, surname, tel, mail, job};
                model.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setModel(model);
    }

    public String getTel() {
        return this.tel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new employee();
    }

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
                new EmployeeGUI();
            });
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return null;
        }

        @Override
        public Object getCellEditorValue() {
            return null;
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(search)) {
            String kw = searchtf.getText();
            if (kw.equals("")) {
                model.setRowCount(0);
                String sql = "SELECT * FROM login;";
                setTable(sql);
            } else {
                model.setRowCount(0);
                String searchh = String.format("SELECT * FROM login WHERE (username = '%s' OR tel = '%s')", kw, kw);
                setTable(searchh);
            }
        } else if (e.getSource().equals(sdefault)) {
            searchtf.setText("");
            model.setRowCount(0);
            String sql = "SELECT * FROM login;";
            setTable(sql);
        }
    }
}