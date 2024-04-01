package net.handytrack.employee;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import net.handytrack.customer.customer;
import net.handytrack.database.DBmanipulation;
import java.awt.event.*;

public class EmployeeGUI implements ActionListener{
    private JFrame fr;
    private JLabel lname,lsurname,lcontact,lemail;
    private JTextField tname, tsurname, tcontact, temail;
    private JPanel p1,p2,p3,p4,p5;
    private JButton done;
    private String name, surname, contact, email;
    private employee emp;

    public EmployeeGUI(){
        fr = new JFrame("Employee Info Updater");
        lname = new JLabel("Name : ");
        lsurname = new JLabel("Surname : ");
        lcontact = new JLabel("Tel : ");
        lemail = new JLabel("Email : ");
        tname = new JTextField(19);
        tsurname = new JTextField(19);
        tcontact = new JTextField(21);
        temail = new JTextField(20);
        p1 = new JPanel();
        p2 =new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        done = new JButton("SUBMIT");
        fr.setLayout(new GridLayout(5,1));
        p1.add(lname);
        p1.add(tname);
        p2.add(lsurname);
        p2.add(tsurname);
        p4.add(lcontact);
        p4.add(tcontact);
        p5.add(lemail);
        p5.add(temail);
        p3.add(done);

        fr.add(p1);
        fr.add(p2);
        fr.add(p4);
        fr.add(p5);
        fr.add(p3);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setVisible(false);
        fr.pack();

    }

//    public customer getCustomer() {
//        return customer;
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(done)){
//            DBmanipulation.getInstance().getUpdate("UPDATE customer SET name = '%s' WHERE name ='%s'");
//            this.name = tname.getText();


        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new EmployeeGUI();
    }

    public JTextField getTcontact() {
        return tcontact;
    }
    public JTextField getTname() {
        return tname;
    }
    public JTextField getTemail() {
        return temail;
    }
    public JTextField getTsurname() {
        return tsurname;
    }
    public JButton getDone() {
        return done;
    }
    public JFrame  getFrame(){return fr;}
}
