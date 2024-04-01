/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * @author marttpq
 */
package net.handytrack.tracker;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RealTrack implements ActionListener, FocusListener, KeyListener {

    private static final ImageIcon CheckPic = new ImageIcon("resources/Picture/Check.png");
    private JFrame fr;
    private JPanel p1, p2, p3, p4, p5, p6, p7, p8, pp1, pp2, pp3, pp4, pp5, pp6, pp7, pp8, p9, p10, plogo;
    private JButton submit, history, option;
    private JLabel title, l1, NameS, recPic, soPic, tranPic, delPic, finishPic, NameR, Address, Cost, Type, Status, logo;
    private JTextField jtf;

    public RealTrack() {
        fr = new JFrame("Tracker");
        p1 = new JPanel();
        p2 = new JPanel();
        p3 = new JPanel();
        p4 = new JPanel();
        p5 = new JPanel();
        p6 = new JPanel();
        p7 = new JPanel();
        p8 = new JPanel();
        pp1 = new JPanel();
        pp2 = new JPanel();
        pp3 = new JPanel();
        pp4 = new JPanel();
        pp5 = new JPanel();
        pp6 = new JPanel();
        pp7 = new JPanel();
        pp8 = new JPanel();
        p9 = new JPanel();
        p10 = new JPanel();
        plogo = new JPanel();
        logo = new JLabel();
        finishPic = new JLabel();
        option = new JButton("Search");
        p3.add(option);
        logo.setIcon(new ImageIcon("resources/Picture/image_1.png"));
        plogo.add(logo);
        plogo.setVisible(false);

        recPic = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        soPic = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        tranPic = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        delPic = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        finishPic = new JLabel("", (int) Component.CENTER_ALIGNMENT);

        pp1.setLayout(new BorderLayout());
        pp2.setLayout(new BorderLayout());
        pp3.setLayout(new BorderLayout());
        pp4.setLayout(new BorderLayout());
        pp5.setLayout(new BorderLayout());
        pp6.setLayout(new BorderLayout());
        pp7.setLayout(new BorderLayout());
        pp8.setLayout(new BorderLayout());

        NameS = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        NameS.setFont(new Font("Aerial", Font.PLAIN, 18));
        NameR = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        NameR.setFont(new Font("Aerial", Font.PLAIN, 18));
        Address = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        Address.setFont(new Font("Aerial", Font.PLAIN, 18));
        Cost = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        Cost.setFont(new Font("Aerial", Font.PLAIN, 18));
        Type = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        Type.setFont(new Font("Aerial", Font.PLAIN, 18));
        Status = new JLabel("", (int) Component.CENTER_ALIGNMENT);
        Status.setFont(new Font("Aerial", Font.PLAIN, 18));

        submit = new JButton(new ImageIcon("resources/Picture/Mirror.png"));
        history = new JButton();
        jtf = new JTextField("Enter your tracking number.", 70);
        title = new JLabel("Handy Track", new ImageIcon("resources/Picture/Delivery.png"), (int) Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Aerial", Font.PLAIN, 24));
        l1 = new JLabel();
        jtf.setPreferredSize(new Dimension(60, 60));
        p1.setBackground(new Color(210, 224, 251));


        jtf.setCaretPosition(0);

        p1.setPreferredSize(new Dimension(470, 180));
        p1.setLayout(new GridLayout(2, 1));
        p1.add(title);
        p4.setBackground(new Color(210, 224, 251));

        p4.add(jtf);
        p4.add(submit);
        p1.add(p4);

        p2.setBackground(Color.WHITE);
        p3.setPreferredSize(new Dimension(470, 40));
        p3.setBackground(new Color(210, 224, 251));
        p3.setLayout(new FlowLayout(FlowLayout.RIGHT));
        p5.setBackground(new Color(210, 224, 251));

        p2.setLayout(new GridLayout(1, 3));
        p7.setLayout(new GridLayout(5, 1));
        p7.add(recPic);
        p7.add(soPic);
        p7.add(tranPic);
        p7.add(delPic);
        p7.add(finishPic);

        p2.add(p7);
        p9.setLayout(new GridLayout(5, 1));
        p8.setLayout(new GridLayout(2, 1));

        p9.add(NameS);
        p9.add(NameR);
        p9.add(Type);
        p9.add(Cost);

        p10.add(Address);

        p8.add(p9);
        p8.add(p10);

        p2.add(p8);

        fr.add(p1, BorderLayout.NORTH);
        fr.add(p2, BorderLayout.CENTER);
        fr.add(p3, BorderLayout.SOUTH);

        Address.setIcon(new ImageIcon("resources/Picture/image_1.png"));

        jtf.addActionListener(this);
        submit.addActionListener(this);
        jtf.addFocusListener(this);
        jtf.addKeyListener(this);
        option.addActionListener(this);

        fr.setResizable(false);
        fr.setSize(1000, 700);
        fr.setLocation(460, 200);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        new RealTrack();
    }

    public void actionPerformed(ActionEvent ev) {
        if (ev.getSource().equals(submit)) {
            ParcelInfo pc = new ParcelInfo(jtf.getText());
            TrackInfo ti = new TrackInfo(jtf.getText());

            ImageIcon nore = new ImageIcon("resources/noPic/Recieved.png");
            ImageIcon notransit = new ImageIcon("resources/noPic/Transit.png");
            ImageIcon nost = new ImageIcon("resources/noPic/Sort.png");
            ImageIcon nodeli = new ImageIcon("resources/noPic/Deli.png");
            ImageIcon nofinish = new ImageIcon("resources/noPic/Finish.png");


            ImageIcon re = new ImageIcon("resources/Picture/Recieved.png");
            ImageIcon transit = new ImageIcon("resources/Picture/Transit.png");
            ImageIcon st = new ImageIcon("resources/Picture/Sort.png");
            ImageIcon deli = new ImageIcon("resources/Picture/Deli.png");
            ImageIcon finish = new ImageIcon("resources/Picture/Finish.png");

            if (jtf.getText().equals("") || jtf.getText().equals("Enter your tracking number.")) {
                JOptionPane.showMessageDialog(null, "Please insert your tracking number.", "ALERT", JOptionPane.ERROR_MESSAGE);
            } else if (pc.getNameS() == null) {
                JOptionPane.showMessageDialog(null, "Sorry, your package couldn't be found.", "ALERT", JOptionPane.ERROR_MESSAGE);
                Cost.setText("Sorry, your package couldn't be found.");
                NameS.setText("");
                NameR.setText("");
                Type.setText("");
                Address.setText("");
                Type.setIcon(new ImageIcon("resources/Picture/Caution.png"));
                recPic.setIcon(nore);
                soPic.setIcon(nost);
                tranPic.setIcon(notransit);
                delPic.setIcon(nodeli);
                finishPic.setIcon(nofinish);
                Address.setIcon(new ImageIcon("resources/noPic/LOGO.png"));
                recPic.setText(String.format("Waiting in progress..."));
                soPic.setText(String.format("Waiting in progress..."));
                tranPic.setText(String.format("Waiting in progress..."));
                delPic.setText(String.format("Waiting in progress..."));
                finishPic.setText(String.format("Waiting in progress..."));

            } else {
                JOptionPane.showMessageDialog(null, "Your parcel status will show on display.", "COMPLETE", JOptionPane.INFORMATION_MESSAGE);
                setdefImage();
                NameS.setText("Hi Mr. " + pc.getNameS());
                NameR.setText("To Mr." + pc.getNameR());
                Type.setText("Type : " + pc.getType());

                Cost.setText("Cost : " + String.valueOf(pc.getCost()));
                Status.setText(pc.getStatus());
                Type.setIcon(new ImageIcon(""));
                if (ti.getRecieved() != null && ti.getSort() == null) {
                    recPic.setIcon(CheckPic);
                    recPic.setText(String.format("<html>'%s'<br>Your Parcel is Recieved.</html>", ti.getRecieved()));
                    soPic.setText(String.format("Waiting in progress..."));
                    tranPic.setText(String.format("Waiting in progress..."));
                    delPic.setText(String.format("Waiting in progress..."));
                    finishPic.setText(String.format("Waiting in progress..."));
                } else if (ti.getSort() != null && ti.getTransit() == null) {
                    setdefImage();
                    soPic.setIcon(CheckPic);
                    recPic.setIcon(CheckPic);
                    recPic.setText(String.format("<html>'%s'<br>Your Parcel is Recieved.</html>", ti.getRecieved()));
                    soPic.setText(String.format("<html>'%s'<br>Your Parcel is Sorting.</html>", ti.getSort()));
                    tranPic.setText(String.format("Waiting in progress..."));
                    delPic.setText(String.format("Waiting in progress..."));
                    finishPic.setText(String.format("Waiting in progress..."));
                } else if (ti.getTransit() != null && ti.getDelivery() == null) {
                    setdefImage();
                    soPic.setIcon(CheckPic);
                    recPic.setIcon(CheckPic);
                    tranPic.setIcon(CheckPic);
                    recPic.setText(String.format("<html>'%s'<br>Your Parcel is Recieved.</html>", ti.getRecieved()));
                    soPic.setText(String.format("<html>'%s'<br>Your Parcel is Sorted.</html>", ti.getSort()));
                    tranPic.setText(String.format("<html>'%s'<br>Your Parcel is Transiting.</html>", ti.getTransit()));
                    delPic.setText(String.format("Waiting in progress..."));
                    finishPic.setText(String.format("Waiting in progress..."));
                } else if (ti.getDelivery() != null && ti.getFinish() == null) {
                    setdefImage();
                    soPic.setIcon(CheckPic);
                    recPic.setIcon(CheckPic);
                    tranPic.setIcon(CheckPic);
                    delPic.setIcon(CheckPic);
                    recPic.setText(String.format("<html>'%s'<br>Your Parcel is Recieved.</html>", ti.getRecieved()));
                    soPic.setText(String.format("<html>'%s'<br>Your Parcel is Sorted.</html>", ti.getSort()));
                    tranPic.setText(String.format("<html>'%s'<br>Your Parcel is Transited.</html>", ti.getTransit()));
                    delPic.setText(String.format("<html>'%s'<br>Your Parcel is been arrange<br>for delivery by driver.</html>", ti.getDelivery()));
                    finishPic.setText(String.format("Waiting for delivery to you."));
                } else if (ti.getFinish() != null) {
                    setdefImage();
                    soPic.setIcon(CheckPic);
                    recPic.setIcon(CheckPic);
                    tranPic.setIcon(CheckPic);
                    delPic.setIcon(CheckPic);
                    finishPic.setIcon(CheckPic);
                    recPic.setText(String.format("<html>'%s'<br>Your Parcel is Recieved.</html>", ti.getRecieved()));
                    soPic.setText(String.format("<html>'%s'<br>Your Parcel is Sorted.</html>", ti.getSort()));
                    tranPic.setText(String.format("<html>'%s'<br>Your Parcel is Transited.</html>", ti.getTransit()));
                    delPic.setText(String.format("<html>'%s'<br>Your Parcel is been arrange<br>for delivery by driver.</html>", ti.getDelivery()));
                    finishPic.setText(String.format("<html>'%s'<br>Successful delivery.</html>", ti.getDelivery()));
                }


//                recPic.setText(String.format("<html>'%s'<br>Your Parcel is Recieved.</html>",ti.getRecieved()));
//                soPic.setText(String.format("<html>'%s'<br>Your Parcel is Sorting.</html>",ti.getSort()));
//                tranPic.setText(String.format("<html>'%s'<br>Your Parcel is Transiting.</html>",ti.getTransit()));

            }
        } else if (ev.getSource().equals(option)) {
            new Option();
        }
    }

    public void focusGained(FocusEvent fe) {
    }

    public void focusLost(FocusEvent fe) {
        if (fe.getSource().equals(jtf)) {
            if (jtf.getText().equals("")) {
                jtf.setText("Enter your tracking number.");
                jtf.setForeground(new Color(153, 153, 153));
            }
        }
    }

    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
            submit.doClick();
        } else if (ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
            fr.dispose();
        }

    }

    public void keyPressed(KeyEvent ke) {

    }

    public void keyTyped(KeyEvent ke) {
        if (ke.getSource().equals(jtf)) {
            if (jtf.getText().equals("Enter your tracking number.")) {
                jtf.setText("");
                jtf.setForeground(Color.BLACK);
            }
        }
    }

    public void setdefImage() {
        ImageIcon re = new ImageIcon("resources/Picture/Recieved.png");
        ImageIcon transit = new ImageIcon("resources/Picture/Transit.png");
        ImageIcon st = new ImageIcon("resources/Picture/Sort.png");
        ImageIcon deli = new ImageIcon("resources/Picture/Deli.png");
        ImageIcon finish = new ImageIcon("resources/Picture/Finish.png");
        recPic.setIcon(re);
        soPic.setIcon(st);
        tranPic.setIcon(transit);
        delPic.setIcon(deli);
        finishPic.setIcon(finish);
        Address.setIcon(new ImageIcon("resources/Picture/image_1.png"));
        //
    }
}
