package net.handytrack.statusupdater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//public class StatusChanger implements ActionListener {
//    private JFrame fr;
//    private JRadioButton receives, sortings, intransits, deliverys, completes;
//    private JButton done;
//    private JPanel pn, pn2, pn3;
//    private ButtonGroup group;
//    private String statusnow;
//    private JLabel rtime, stime, ttime, dtime, ctime;
//
//    public StatusChanger() {
//        fr = new JFrame("Change Status");
//        receives = new JRadioButton("Receive");
//        sortings = new JRadioButton("Sorting");
//        intransits = new JRadioButton("In Transit");
//        deliverys = new JRadioButton("Delivery");
//        completes = new JRadioButton("Complete");
//        done = new JButton("Done");
//        group = new ButtonGroup();
//        pn = new JPanel();
//
//        pn2 = new JPanel();
//        pn3 = new JPanel();
//        rtime = new JLabel("");
////        stime = new JLabel("");
////        ttime = new JLabel("");
////        dtime = new JLabel("");
////        ctime = new JLabel("");
//
//        pn.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));
//
//        group.add(receives);
//        group.add(sortings);
//        group.add(intransits);
//        group.add(deliverys);
//        group.add(completes);
//
//        pn.add(receives);
//        pn.add(sortings);
//        pn.add(intransits);
//        pn.add(deliverys);
//        pn.add(completes);
//        pn.setLayout(new GridLayout(0, 1));
////        pn2.setLayout(new GridLayout(1, 1));
//        pn2.add(rtime);
////        pn2.add(stime);
////        pn2.add(ttime);
////        pn2.add(dtime);
////        pn2.add(ctime);
//        pn3.setLayout(new BorderLayout());
//        pn3.add(pn, BorderLayout.WEST);
//        pn3.add(pn2, BorderLayout.CENTER);
////        pn3.setLayout(new GridLayout(1, 2));
////        pn3.add(pn);
////        pn3.add(pn2);
//        fr.add(pn3, BorderLayout.CENTER);
//        fr.add(done, BorderLayout.SOUTH);
//
//        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        fr.setSize(270, 200);
//        fr.setLocation(800, 400);
//        fr.setVisible(false);
//
//        done.addActionListener(this);
//        receives.addActionListener(this);
//        sortings.addActionListener(this);
//        completes.addActionListener(this);
//        deliverys.addActionListener(this);
//        intransits.addActionListener(this);
//
//    }
//
//    public void actionPerformed(ActionEvent e) {
//        if (receives.isSelected()) {
//            this.statusnow = "Receive";
//        } else if (sortings.isSelected()) {
//            this.statusnow = "Sorting";
//        } else if (intransits.isSelected()) {
//            this.statusnow = "In Transit";
//        } else if (deliverys.isSelected()) {
//            this.statusnow = "Delivery";
//        } else if (completes.isSelected()) {
//            this.statusnow = "Complete";
//        } else {
//            JOptionPane.showMessageDialog(fr, "Please choose the status!", "Error!", JOptionPane.WARNING_MESSAGE);
//        }
//    }
//
//    public JFrame getFr() {
//        return fr;
//    }
//
//    public String getStatus() {
//        return statusnow;
//    }
//
//    public JButton getDone() {
//        return done;
//    }
//
//    public JRadioButton getReceives() {
//        return receives;
//    }
//
//    public JRadioButton getSortings() {
//        return sortings;
//    }
//
//    public JRadioButton getInTransits() {
//        return intransits;
//    }
//
//    public JRadioButton getDeliverys() {
//        return deliverys;
//    }
//
//    public JRadioButton getCompletes() {
//        return completes;
//    }
//
//    public JLabel getRtime() {
//        return rtime;
//    }
//
////    public JLabel getStime() {
////        return stime;
////    }
////
////    public JLabel getTtime() {
////        return ttime;
////    }
////
////    public JLabel getDtime() {
////        return dtime;
////    }
////
////    public JLabel getCtime() {
////        return ctime;
////    }
//}

public class StatusChanger implements ActionListener {
    private JFrame fr;
    private JRadioButton receives, sortings, intransits, deliverys, completes;
    private JButton done;
    private JPanel spn, yellow, pink, blue, green1, green2, midblue;
    private ButtonGroup group;
    private String statusnow;
    private JLabel rtime, track, receiver, contact, address, type, status;

    public StatusChanger() {
        fr = new JFrame("Change Status");
        receives = new JRadioButton("Receive");
        sortings = new JRadioButton("Sorting");
        intransits = new JRadioButton("In Transit");
        deliverys = new JRadioButton("Delivery");
        completes = new JRadioButton("Complete");
        done = new JButton("Done");
        group = new ButtonGroup();

        spn = new JPanel();
        yellow = new JPanel();
        pink = new JPanel();
        green1 = new JPanel();
        green2 = new JPanel();
        blue = new JPanel();
        midblue = new JPanel();
        rtime = new JLabel("asd");
        track = new JLabel("trac");
        receiver = new JLabel("receiver");
        contact = new JLabel("contact");
        address = new JLabel("addres");
        type = new JLabel("type");
        status = new JLabel("stats");

        group.add(receives);
        group.add(sortings);
        group.add(intransits);
        group.add(deliverys);
        group.add(completes);

        spn.add(receives);
        spn.add(sortings);
        spn.add(intransits);
        spn.add(deliverys);
        spn.add(completes);
//        pn.setLayout(new GridLayout(0, 1));
        spn.setLayout(new GridLayout(1, 0));

        fr.setLayout(new BorderLayout());
        fr.add(done, BorderLayout.SOUTH);
        fr.add(yellow, BorderLayout.CENTER);

        pink.setLayout(new BorderLayout());
        pink.add(track, BorderLayout.NORTH);
        pink.add(blue, BorderLayout.CENTER);

        yellow.setLayout(new BorderLayout());
        yellow.add(spn, BorderLayout.SOUTH);
        yellow.add(pink, BorderLayout.CENTER);

        blue.setLayout(new GridLayout(3, 1));
        blue.add(green1);
        blue.add(green2);
        midblue.setLayout(new GridLayout(1, 1));
        midblue.add(address);
        blue.add(midblue);

        green1.setLayout(new GridLayout(1, 2));
        green1.add(receiver);
        green1.add(contact);
        green2.setLayout(new GridLayout(1, 2));
        green2.add(type);
        green2.add(status);


        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setSize(400, 175);
        fr.setLocation(800, 400);
        fr.setVisible(false);

        done.addActionListener(this);
        receives.addActionListener(this);
        sortings.addActionListener(this);
        completes.addActionListener(this);
        deliverys.addActionListener(this);
        intransits.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (receives.isSelected()) {
            this.statusnow = "Receive";
        } else if (sortings.isSelected()) {
            this.statusnow = "Sorting";
        } else if (intransits.isSelected()) {
            this.statusnow = "In Transit";
        } else if (deliverys.isSelected()) {
            this.statusnow = "Delivery";
        } else if (completes.isSelected()) {
            this.statusnow = "Complete";
        } else {
            JOptionPane.showMessageDialog(fr, "Please choose the status!", "Error!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public JFrame getFr() {
        return fr;
    }

    public String getStatus() {
        return statusnow;
    }

    public JButton getDone() {
        return done;
    }

    public JRadioButton getReceives() {
        return receives;
    }

    public JRadioButton getSortings() {
        return sortings;
    }

    public JRadioButton getInTransits() {
        return intransits;
    }

    public JRadioButton getDeliverys() {
        return deliverys;
    }

    public JRadioButton getCompletes() {
        return completes;
    }

    public JLabel getRtime() {
        return rtime;
    }
    public JLabel getTrack() {
        return track;
    }
    public JLabel getReceiver() {
        return receiver;
    }
    public JLabel getContact() {
        return contact;
    }
    public JLabel getAddress() {
        return address;
    }
    public JLabel getTypes() {
        return type;
    }
    public JLabel getStatuss() {
        return status;
    }
}

