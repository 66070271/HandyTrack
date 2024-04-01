package net.handytrack.customer;
import java.awt.*;
import javax.swing.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import net.handytrack.database.DBmanipulation;
import net.handytrack.tracker.RealTrack;
import java.awt.event.*;
public class CustomerGUI implements ActionListener{
    private JFrame fr;
    private JLabel lname,lcontact;
    private JTextField tname,tcontact;
    private JPanel p1,p2,p3;
    private JButton done;
    private customer customer;
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

        fr.add(p1);
        fr.add(p2);
        fr.add(p3);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setVisible(true);
        fr.pack();
    }

    public customer getCustomer() {
        return customer;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(done)){
            DBmanipulation.getInstance().getUpdate("UPDATE customer SET name = '%s' WHERE name ='%s'");
            DBmanipulation.getInstance().disconnect();
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
