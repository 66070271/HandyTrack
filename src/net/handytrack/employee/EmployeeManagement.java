package net.handytrack.employee;
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
import java.sql.*;
public class EmployeeManagement implements ActionListener {
//    private ResultSet rs;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;
    private JFrame frame;
    private JPanel panel1, panel2, space1, space2;
    private JButton search, sdefault;
    private JTextField searchtf;
    private JLabel lb2;
    private EmployeeUpdaterGUI egui;

    public EmployeeManagement() {
        frame = new JFrame("Employee Management");

        panel1 = new JPanel();
        panel2 = new JPanel();
        table = new JTable();
        space1 = new JPanel();
        space2 = new JPanel();
        egui = new EmployeeUpdaterGUI();
        String[] col = {"Username", "Name", "Surname", "Telephone", "Email", "Job position", "Setting"};
        model = new DefaultTableModel(col, 0) {
            @Override ///ทำให้ Column อื่นๆที่ไม่ได้ Set ไว้แก้ไขไม่ได้
            public boolean isCellEditable(int row, int column) {
                // Make columns 6 editable
                return column == 6;
            }
        };

        table = new JTable(model);
        scrollPane = new JScrollPane(table);

        search = new JButton("Search");
        sdefault = new JButton("Set Default");
        searchtf = new JTextField(20);
        lb2 = new JLabel("Sorted by Username or Name : ");
        frame.add(panel2, BorderLayout.NORTH);
        panel2.add(lb2);
        panel2.add(searchtf);
        panel2.add(search);
        panel2.add(sdefault);

        String sql = "SELECT * FROM login";
        setTable(sql);

        TableColumn deleteColumn = table.getColumnModel().getColumn(6); // Action column index is 5
        deleteColumn.setCellRenderer(new ButtonRenderer());
        deleteColumn.setCellEditor(new ButtonEditor());

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1400, 750);
        frame.setLocation(300,200);

        search.addActionListener(this);
        sdefault.addActionListener(this);
        egui.getDone().addActionListener(this);

        table.setRowSelectionAllowed(true);
        table.setCellSelectionEnabled(true);
    }

    public void setTable(String sql) {
        try {
            ResultSet rs = DBquery.getInstance().getSelect(sql);
            while (rs.next()) {
                String user = rs.getString("username");
                String name = rs.getString("name");
                String surname = rs.getString("surename");
                String tel = rs.getString("tel");
                String mail = rs.getString("email");
                String job = rs.getString("jobposition");
                String[] row = {user, name, surname, tel, mail, job, "Update"};
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
            if (kw.equals("")) {
                model.setRowCount(0);
                String sql = "SELECT * FROM login;";
                setTable(sql);
            } else {
                model.setRowCount(0);
                String searchh = String.format("SELECT * FROM login WHERE (username = '%s' OR name = '%s')", kw, kw);
                setTable(searchh);
            }
        } else if (e.getSource().equals(sdefault)) {
            searchtf.setText("");
            model.setRowCount(0);
            String sql = "SELECT * FROM login;";
            setTable(sql);
        }else if(e.getSource().equals(egui.getDone())) {
            egui.getFrame().dispose();
            int selectedRow = table.getSelectedRow();
            table.setValueAt(egui.getTname().getText(), selectedRow, 1);
            table.setValueAt(egui.getTsurname().getText(), selectedRow, 2);
            table.setValueAt(egui.getTcontact().getText(), selectedRow, 3);
            table.setValueAt(egui.getTemail().getText(), selectedRow, 4);

            String username = model.getValueAt(selectedRow, 0).toString();
            String name = model.getValueAt(selectedRow, 1).toString();
            String surname = model.getValueAt(selectedRow, 2).toString();
            String phonenum = model.getValueAt(selectedRow, 3).toString();
            String email = model.getValueAt(selectedRow, 4).toString();
            String sql = String.format("UPDATE login SET name = '%s', surename = '%s', tel = '%s', email = '%s' WHERE username = '%s'", name, surname, phonenum, email, username);
            DBmanipulation.getInstance().getUpdate(sql);
            egui.getTname().setText("");
            egui.getTsurname().setText("");
            egui.getTcontact().setText("");
            egui.getTemail().setText("");
        }
    }
//    public String getTel() {
//        return this.tel;
//    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new EmployeeManagement();
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
            button = new JButton("Update");
            button.addActionListener(e -> {
//                fireEditingStopped();
                /// RIGHT HERE (Time in CurrentStats GUI)
                egui.getFrame().setVisible(true);
                int selectedRow = table.getSelectedRow();
                System.out.println(selectedRow);
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