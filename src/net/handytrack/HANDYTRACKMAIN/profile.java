package net.handytrack.HANDYTRACKMAIN;

import net.handytrack.database.DBmanipulation;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class profile extends JPanel implements ActionListener, Serializable {

    private circle c;
    private JLabel lname, ltel;
 //   private DBConnect db;
    private String p, name, pass, num;

    private int keyuser;

    public profile() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // ปรับ
        this.setBackground(new Color(210, 224, 251));
        lname = new JLabel("NAME : ");
        ltel = new JLabel("TEL :");
        c = new circle();
        c.setSize(new Dimension(150, 150));
        name = "";
        pass = "";
        num = "";
        this.setPreferredSize(new Dimension(200, 200));

        c.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                actionPerformed(new ActionEvent(c, ActionEvent.ACTION_PERFORMED, null));
            }
        });
        this.add(c);
        this.add(Box.createVerticalStrut(10));
        this.add(lname);
        this.add(ltel);

    }
    public JLabel getLname(){
        return lname;
    }
    public JLabel getLtel(){
        return ltel;
    }

    public void actionPerformed(ActionEvent e) {
        // ทำเมื่อกดที่วงกลม

        try {
            if (e.getSource() == c) {
                // เปิดไฟล์ภาพ
                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setCurrentDirectory(new File(System.getProperty("File Explorer")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.IMAGE", "jpg", "gif", "png");
                fileChooser.addChoosableFileFilter(filter);
                int returnValue = fileChooser.showOpenDialog(this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    ImageIcon image = new ImageIcon(selectedFile.getAbsolutePath());
                    c.setIcon(image); // กำหนดภาพใหม่ในวงกลม
                    c.repaint();
                    p = path;
                }
            }
            //การอัพเดดภาพลงบน sql
            Connection dom = DriverManager.getConnection("jdbc:sqlite:resources/DB.db");
            PreparedStatement ps = dom.prepareStatement("UPDATE login SET profile = ? WHERE iduser = ?");


//            byte[] imageBytes = lm.readAllBytes();
            ps.setBlob(1,new FileInputStream(p));
            ps.setInt(2,this.keyuser);
            ps.executeUpdate();
//                ps.executeQuery();
            JOptionPane.showMessageDialog(null, "Data Inserted");

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Can't Open File", "ข้อผิดพลาด", JOptionPane.ERROR_MESSAGE);
        }

    }
//
//    public void setName(String s) {
//        lname.setText("NAME: " + s);
//    }
//
//    public void setTel(String s) {
//        ltel.setText("TEL: " + s);
//    }
//
//    public void setNum(String s) {
//        num = s;
//    }
//
//    public void setPass(String s) {
//        pass = s;
//    }
//
//    public void setPicture(String s) {
//        p = s;
//    }

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
    public void setKeyuser(int i){
        this.keyuser = i;
    }
}
