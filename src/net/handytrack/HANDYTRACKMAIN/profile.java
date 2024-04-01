package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class profile extends JPanel implements Serializable {

    private circle c;
    private JLabel lname, ltel, email, allname, logo;
    private JPanel p1, p2, p3, p4;
    private int keyuser;

    public profile() {
        // ปรับ
        this.setLayout(new GridLayout(3, 1));
        this.setBackground(Color.WHITE);
        lname = new JLabel("Welcome", (int) Component.CENTER_ALIGNMENT);
        ltel = new JLabel("TEL :", (int) Component.CENTER_ALIGNMENT);
        c = new circle();
        c.setSize(new Dimension(150, 150));
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        email = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        allname = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        logo = new JLabel("HandyTrack", new ImageIcon("resources/Picture/smalllogo.png"), (int) Component.CENTER_ALIGNMENT);
        logo.setFont(new Font("Aerial", Font.PLAIN, 28));

        p1.setLayout(new GridLayout(2, 1));
        p2.add(logo);
        p2.setBackground(new Color(171, 196, 255));
        p1.setBackground(new Color(193, 211, 254));
        p1.add(p2);
        p1.add(lname);
        p4.setLayout(new GridLayout(4, 1));
        p4.add(allname);
        p4.add(ltel);
        p4.add(email);
        p3.setBackground(new Color(215, 227, 252));
        p4.add(p3);
        p4.setBackground(new Color(215,227,252));
        this.add(p1);
        this.add(c);
        this.add(p4);

        logo.setForeground(new Color(0,0,16));
        email.setForeground(new Color(0,0,16));
        allname.setForeground(new Color(0,0,16));
        ltel.setForeground(new Color(0,0,16));
        lname.setForeground(new Color(0,0,16));

    }

    public JLabel getLname() {
        return lname;
    }

    public JLabel getLtel() {
        return ltel;
    }

    public JLabel getEmail() {
        return email;
    }

    public JLabel getAllname() {
        return allname;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void settel(String s) {
        ltel.setText(s);
    }

    public void setKeyuser(int i) {
        this.keyuser = i;
    }

    public void setLname(String s){lname.setText(s);}
}
