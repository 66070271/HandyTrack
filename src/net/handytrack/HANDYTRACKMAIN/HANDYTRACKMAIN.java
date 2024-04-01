package net.handytrack.HANDYTRACKMAIN;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBquery;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    private LoginEdit g;
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

        ImageIcon ipro = new ImageIcon("resources/Picture/Profile.png");
        ImageIcon iadd = new ImageIcon("resources/Picture/Add.png");
        ImageIcon isum = new ImageIcon("resources/Picture/Summarize.png");
        ImageIcon iset = new ImageIcon("resources/Picture/Setting.png");
        ImageIcon man = new ImageIcon("resources/Picture/manipulate.png");

        fr.setLayout(new BorderLayout());

        jm.add(mm);

        lpro.setIcon(ipro);
        ladd.setIcon(iadd);
        lsum.setIcon(isum);
        ltrac.setIcon(iset);
        lman.setIcon(man);

        lpro.setFont(new Font("Aerial", Font.PLAIN, 20));
        ladd.setFont(new Font("Aerial", Font.PLAIN, 20));
        lsum.setFont(new Font("Aerial", Font.PLAIN, 20));
        ltrac.setFont(new Font("Aerial", Font.PLAIN, 20));
        lman.setFont(new Font("Aerial", Font.PLAIN, 20));
        lpro.setForeground(new Color(0,0,16));
        ladd.setForeground(new Color(0,0,16));
        lsum.setForeground(new Color(0,0,16));
        ltrac.setForeground(new Color(0,0,16));
        lman.setForeground(new Color(00,0,16));

        part1.setLayout(new GridLayout(1, 5));
        ppro.add(lpro);
        ppro.setBackground(new Color(249, 245, 246));
        padd.add(ladd);
        padd.setBackground(new Color(255, 230, 230));
        psum.add(lsum);
        psum.setBackground(new Color(225, 175, 209));
        ptrac.add(ltrac);
        ptrac.setBackground(new Color(173, 136, 198));
        pman.add(lman);
        pman.setBackground(new Color(116, 105, 182));
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

        part2.setLayout(new BorderLayout());
        part2.setBackground(new Color(210, 224, 251));
        part2.setLayout(new OverlayLayout(part2));
        part2.add(p);
//        part2.add(a);
        part2.add(s);
//        part2.add(t);
//        part2.add(m);

//         ตั้งค่าให้แสดง JPanel ที่มีส่วนของ Profile เมื่อโปรแกรมเริ่มต้นการทำงาน
        p.setVisible(true);
//        a.setVisible(false);
        s.setVisible(false);
//        t.setVisible(false);
//        m.setVisible(false);

        //set JFrame
        fr.setLocation(500, 150);
        fr.add(part1, BorderLayout.NORTH);
        fr.add(part2, BorderLayout.CENTER);
        fr.setJMenuBar(jm);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setSize(800, 800);
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new HANDYTRACKMAIN();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        try {
            if (e.getSource().equals(lpro)) {
//                resizeFrame(false);
                p.setVisible(true);
                a.setVisible(false);
                s.setVisible(false);
                t.setVisible(false);
                m.setVisible(false);
            }
            if (e.getSource().equals(ladd)) {
//                resizeFrame(false);
                a.setVisible(true);
                p.setVisible(false);
                s.setVisible(false);
                t.setVisible(false);
                m.setVisible(false);
            }
            if (e.getSource().equals(lsum)) {
//                resizeFrame(false);
                p.setVisible(false);
                a.setVisible(false);
                s.setVisible(true);
                t.setVisible(false);
                m.setVisible(false);
            }
            if (e.getSource().equals(ltrac)) {
//                resizeFrame(false);
                p.setVisible(false);
                a.setVisible(false);
                s.setVisible(false);
                t.setVisible(true);
                m.setVisible(false);
            }
            if (e.getSource().equals(lman)) {
//                resizeFrame(false);
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

    public void fetchUser() {
        String sql = String.format("SELECT * FROM login WHERE iduser = '%d'", this.keyuser);
        ResultSet rs = DBquery.getInstance().getSelect(sql);
        try {
            if (rs.next()) {
                this.user = new User(rs.getString("name"), rs.getString("surename"), rs.getString("email"), rs.getString("tel"));
                p.getLname().setText("Hi " + user.getName() + ".");
                p.getLname().setFont(new Font("Aerial", Font.PLAIN, 32));
                p.getLtel().setText("Tel : " + user.getTel());
                p.setKeyuser(this.keyuser);
                p.getEmail().setText("Email : " + user.getEmail());
                p.getAllname().setText("Name : " + user.getName() + " " + user.getSurename());
                p.getAllname().setFont(new Font("Aerial", Font.PLAIN, 24));
            }
            p.getEmail().setFont(new Font("Aerial", Font.PLAIN, 24));
            p.getLtel().setFont(new Font("Aerial", Font.PLAIN, 24));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setname(String s, String a) {
        p.setName(s);
        p.setName(a);
    }

    public JFrame getFrame() {
        return this.fr;
    }

    public void setKeyuser(int key) {
        this.keyuser = key;
    }

//    private void resizeFrame(boolean resize) {
//        if (resize) {
//            fr.pack(); // Resize frame
//        } else {
//            fr.setSize(800, 800); // Revert to original size
//        }
//    }
}
