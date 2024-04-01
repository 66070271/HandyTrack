/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author marttpq
 */
package net.handytrack.tracker;

import net.handytrack.customer.customer;
import net.handytrack.database.DBquery;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

public class Option implements ActionListener{
    private JFrame fr;
    private JPanel p1, p2, p3, p4;
    private JLabel his, phone;
    private JButton exit,search;
    private JTextField ttel;
    private JTextArea ja;
    private JScrollPane jp;



    public Option(){

        fr = new JFrame("Search by phone number.");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        ja = new JTextArea(60,40);
        jp = new JScrollPane(ja);
        search = new JButton();





        search.setIcon(new ImageIcon("resources/Picture/his.png"));
        his = new JLabel("View your history.", (int) Component.CENTER_ALIGNMENT);
        exit = new JButton("Done");
        phone = new JLabel("Search by phone number.", (int) Component.CENTER_ALIGNMENT);
        ttel = new JTextField(15);
        fr.setSize(500, 700);
        fr.setLocation(500, 400);
        p2.add(exit);
        p2.setBackground(new Color(210, 224, 251));
        p1.setBackground(Color.WHITE);

        p3.setBackground(new Color(210, 224, 251));

        ja.setEditable(false);

        search.addActionListener(this);
        exit.addActionListener(this);
        p3.add(ttel);
        p3.add(search);
        fr.add(p3,BorderLayout.NORTH);
        fr.add(jp, BorderLayout.CENTER);
        fr.add(p2, BorderLayout.SOUTH);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setLocation(700,200);
        fr.setResizable(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(search)){
            ResultSet rs = DBquery.getInstance().getSelect(String.format("SELECT * FROM customer WHERE tel = '%s'",ttel.getText()));
            try {
                while (rs.next()) {
                    String h = rs.getString("History");
                    ja.append(h+"\n");
                }

            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else if(e.getSource().equals(exit)){
            fr.dispose();
        }
    }
}