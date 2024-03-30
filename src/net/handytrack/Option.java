/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author marttpq
 */
package net.handytrack;

import javax.swing.*;
import java.awt.*;

public class Option {
    private JFrame fr;
    private JPanel p1, p2, p3, p4;
    private JLabel his, phone;
    private JButton exit;

    public Option() {
        fr = new JFrame("Option");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        his = new JLabel("View your history.", (int) Component.CENTER_ALIGNMENT);
        exit = new JButton("Done");
        phone = new JLabel("Search by phone number.", (int) Component.CENTER_ALIGNMENT);
        fr.setSize(300, 300);
        fr.setLocation(500, 400);
        p2.add(exit);
        p2.setBackground(new Color(210, 224, 251));
        p1.setBackground(Color.WHITE);
        p1.setLayout(new GridLayout(2, 1));
        p1.add(his);
        p1.add(phone);
        his.setIcon(new ImageIcon("C:\\Users\\marttpq\\Desktop\\HandyTrack\\Project\\src\\Picture\\his.png"));
        phone.setIcon(new ImageIcon("C:\\Users\\marttpq\\Desktop\\HandyTrack\\Project\\src\\Picture\\phone.png"));
        fr.add(p1, BorderLayout.CENTER);
        fr.add(p2, BorderLayout.SOUTH);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public static void main(String[] args) {
        new Option();
    }
}
