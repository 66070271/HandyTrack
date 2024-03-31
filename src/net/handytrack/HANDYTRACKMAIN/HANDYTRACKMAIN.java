package net.handytrack.HANDYTRACKMAIN;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.*;
import net.handytrack.database.DBmanipulation;
import net.handytrack.database.DBquery;

import java.io.*;
import java.sql.*;

public class HANDYTRACKMAIN implements MouseListener {

    private JFrame fr;
    private JPanel part1, part2, ppro, padd, psum, ptrac, pman, pbpro, pbadd, pbsum, pbtrac, pbman;
    private JMenuBar jm;
    private JLabel ladd, lsum, ltrac, lpro, lman;
    private JMenu mm;
    private profile p;
    private add a;
    private summarize s;
    private track t;
    private manage m;

    private LoginGUI g;
    private User user;
    private int keyuser;

    public HANDYTRACKMAIN() {

        fr = new JFrame("HANDYTRACKMAIN");
        part1 = new JPanel();
        part2 = new JPanel();
        ppro = new JPanel();
        psum = new JPanel();
        padd = new JPanel();
        pman = new JPanel();
        ptrac = new JPanel();
        pbpro = new JPanel();
        pbadd = new JPanel();
        pbsum = new JPanel();
        pbtrac = new JPanel();
        pbman = new JPanel();
        jm = new JMenuBar();
        mm = new JMenu("File");
        ladd = new JLabel("Add", JLabel.CENTER);
        ltrac = new JLabel("Track", JLabel.CENTER);
        lpro = new JLabel("Profile", JLabel.CENTER);
        lsum = new JLabel("Summarize", JLabel.CENTER);
        lman = new JLabel("Manage", JLabel.CENTER);
        p = new profile();

        a = new add();
        s = new summarize();
        t = new track();
        m = new manage();

        pbpro.add(p);
        pbadd.add(a);
        pbsum.add(s);
        pbtrac.add(t);
        pbman.add(m);

        ImageIcon ipro = new ImageIcon("C:\\Users\\pleum\\Downloads\\Man-Arm-Raises-1-Alternate--Streamline-Flex.png");
        ImageIcon iadd = new ImageIcon("C:\\Users\\pleum\\Downloads\\Add-2--Streamline-Stickies.png");
        ImageIcon isum = new ImageIcon("C:\\Users\\pleum\\Downloads\\Sigma--Streamline-Plump.png");
        ImageIcon iset = new ImageIcon("C:\\Users\\pleum\\Downloads\\Location-Info--Streamline-Stickies.png");
        ImageIcon man = new ImageIcon("C:\\Users\\pleum\\Downloads\\Hierarchy-14--Streamline-Core.png");

        fr.setLayout(new BorderLayout());

        jm.add(mm);

        lpro.setIcon(ipro);
        ladd.setIcon(iadd);
        lsum.setIcon(isum);
        ltrac.setIcon(iset);
        lman.setIcon(man);

        part1.setLayout(new GridLayout(1, 5));
        ppro.add(lpro);
        padd.add(ladd);
        psum.add(lsum);
        ptrac.add(ltrac);
        pman.add(lman);

        part1.add(ppro);
        part1.add(padd);
        part1.add(psum);
        part1.add(ptrac);
        part1.add(pman);

        ppro.setBorder(new LineBorder(Color.BLACK, 1));
        padd.setBorder(new LineBorder(Color.BLACK, 1));
        psum.setBorder(new LineBorder(Color.BLACK, 1));
        ptrac.setBorder(new LineBorder(Color.BLACK, 1));
        pman.setBorder(new LineBorder(Color.BLACK, 1));

        //add Listener
        ladd.addMouseListener(this);
        ltrac.addMouseListener(this);
        lpro.addMouseListener(this);
        lsum.addMouseListener(this);
        lman.addMouseListener(this);

        part2.setBackground(new Color(210, 224, 251));
//        part2.setLayout(new OverlayLayout(part2));
        part2.add(p);
        part2.add(a);
        part2.add(s);
        part2.add(t);
        part2.add(m);

//         ตั้งค่าให้แสดง JPanel ที่มีส่วนของ Profile เมื่อโปรแกรมเริ่มต้นการทำงาน
        p.setVisible(true);
        a.setVisible(false);
        s.setVisible(false);
        t.setVisible(false);
        m.setVisible(false);

        //set JFrame
        fr.setLocation(500, 150);
        fr.add(part1, BorderLayout.NORTH);
        fr.add(part2, BorderLayout.CENTER);
        fr.setJMenuBar(jm);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(800, 800);
        fr.setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (e.getSource().equals(lpro)) {
                p.setVisible(true);
                a.setVisible(false);
                s.setVisible(false);
                t.setVisible(false);
                m.setVisible(false);
            }
            if (e.getSource().equals(ladd)) {
                a.setVisible(true);
                p.setVisible(false);
                s.setVisible(false);
                t.setVisible(false);
                m.setVisible(false);
            }
            if (e.getSource().equals(lsum)) {
                p.setVisible(false);
                a.setVisible(false);
                s.setVisible(true);
                t.setVisible(false);
                m.setVisible(false);
            }
            if (e.getSource().equals(ltrac)) {
                p.setVisible(false);
                a.setVisible(false);
                s.setVisible(false);
                t.setVisible(true);
                m.setVisible(false);
            }
            if (e.getSource().equals(lman)) {
                p.setVisible(false);
                a.setVisible(false);
                s.setVisible(false);
                t.setVisible(false);
                m.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    public void fetchUser(){
        String sql = String.format("SELECT * FROM login WHERE iduser = '%d'",this.keyuser );
        ResultSet rs = DBquery.getInstance().getSelect(sql);
        try{
            if(rs.next()){
                this.user = new User(rs.getString("name"),rs.getString("surename"),rs.getString("email"),rs.getString("tel"));
                p.getLname().setText("Hi "+user.getName()+".");
                p.getLname().setFont(new Font("Aerial", Font.PLAIN, 18));
                p.getLname().setVerticalTextPosition((int)Component.CENTER_ALIGNMENT);
                p.getLtel().setText(user.getTel());
                p.setKeyuser(this.keyuser);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }


    public void setname(String s,String a){
        p.setName(s);
        p.setName(a);
    }

    public JFrame getFrame(){
        return this.fr;
    }
    public void setKeyuser(int key){
        this.keyuser = key;
    }
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new HANDYTRACKMAIN();
    }
}
