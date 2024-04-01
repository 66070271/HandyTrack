package net.handytrack.HANDYTRACKMAIN;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class ManageGUI {
    private JFrame fr;
    private JButton person,parcel,cus,update;
    public ManageGUI(){
        fr = new JFrame("Select");
        person = new JButton("Manage employee");
        parcel = new JButton("Manage Parcel");
        cus = new JButton("Manage Customer");
        update  = new JButton("Update Status");
        person.setForeground(new Color(0,0,16));
        parcel.setForeground(new Color(0,0,16));
        cus.setForeground(new Color(0,0,16));
        update.setForeground(new Color(0,0,16));
        fr.setLayout(new GridLayout(4,1));
        fr.add(person);
        fr.add(parcel);
        fr.add(cus);
        fr.add(update);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setSize(300,200);
        fr.setResizable(false);
        fr.setLocation(800,450);
    }

    public static void main(String[] args) {
        new ManageGUI();
    }
}

