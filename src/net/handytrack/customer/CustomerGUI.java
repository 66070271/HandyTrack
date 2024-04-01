package net.handytrack.customer;
import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import net.handytrack.database.DBmanipulation;
import net.handytrack.database.DBquery;
import net.handytrack.tracker.RealTrack;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerGUI implements ActionListener{
    private JFrame fr;
    private JLabel lname,lcontact;
    private JTextField tname,tcontact;
    private JPanel p1,p2,p3;
    private JButton done;
    private customer customer;
    private ArrayList<String> data;
    private CustomerManagement value;
    public CustomerGUI(){
        fr = new JFrame();
        lname = new JLabel("Name : ");
        lcontact = new JLabel("Tel : ");
        tname = new JTextField(19);
        tcontact = new JTextField(20);
        p1 = new JPanel();
        p2 =new JPanel();
        p3 = new JPanel();
        done = new JButton("SUBMIT");
        fr.setLayout(new GridLayout(3,1));
        p1.add(lname);
        p1.add(tname);
        p2.add(lcontact);
        p2.add(tcontact);
        p3.add(done);

        value = new CustomerManagement();
        data = new ArrayList<String>();

        fr.add(p1);
        fr.add(p2);
        fr.add(p3);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setVisible(true);
        fr.pack();

        done.addActionListener(this);
    }

    public customer getCustomer() {
        return customer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(done)){
            if((!tname.getText().equals("")) && (!tcontact.getText().equals(""))) {
                updatesql();
            } else {
                System.out.println(1);
            }
        }
    }

    public void updatesql() {
        try {
        ResultSet rs = DBquery.getInstance().getSelect("SELECT* FROM customer");
        while (rs.next()) {
            String gname = rs.getString("name");
            String gcontact = rs.getString("tel");
            String name = tname.getText();
            String contact = tcontact.getText();
            String sql2 = String.format("UPDATE customer SET name = '%s' WHERE name = '%s'",gname,name);
            String sql3 = String.format("UPDATE customer SET tel = '%s' WHERE tel = '%s'",gcontact,contact);
            DBmanipulation.getInstance().getUpdate(sql2);
            DBmanipulation.getInstance().getUpdate(sql3);
            String sql4 = "SELECT* FROM customer";
            value.setTable(sql4);
        }
        } catch (SQLException e) {

        }
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new CustomerGUI();
    }
}
