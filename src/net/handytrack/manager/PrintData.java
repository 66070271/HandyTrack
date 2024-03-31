/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package net.handytrack.manager;
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author FookNaRak
 */
public class PrintData {
    private JFrame fr;
    private JPanel p1,p2,p3,p4,p5;
    private JLabel txtID,txtSenderName,txtTel,txtReciverName,txtRoad,txtDistrict,txtProvince,txtZipCode,txtType,txtSex,txtCost;
    private JButton b1;
    
    public PrintData() {
        fr = new JFrame("Print");
        p1 = new JPanel(); p2 = new JPanel(); p3 = new JPanel(); p4 = new JPanel(); p5 = new JPanel();
        txtID = new JLabel("123"); txtSenderName = new JLabel("Fook"); txtTel = new JLabel("098"); txtReciverName = new JLabel("Mon"); txtRoad = new JLabel("Road"); txtDistrict = new JLabel("khet"); txtProvince = new JLabel("Bangkok"); txtZipCode = new JLabel("12345"); txtType = new JLabel("Type"); txtSex = new JLabel("Male"); txtCost = new JLabel("100");
        b1 = new JButton("print");
        
        //set JFrame
        fr.setLayout(new GridLayout(2,2));
        fr.setBackground(new Color(210,224,251));
        
        p2.setLayout(new GridLayout(3,1));
        p2.add(txtID); p2.add(txtSenderName); p2.add(txtTel);
        p4.setLayout(new GridLayout(5,1));
        p4.add(txtReciverName); p4.add(txtRoad); p4.add(txtDistrict); p4.add(txtZipCode);
        fr.add(p2); fr.add(p3); fr.add(p5); fr.add(p4);
        
        fr.setSize(600, 400);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    public static void main(String[] args) {
        new PrintData();
    }
}
