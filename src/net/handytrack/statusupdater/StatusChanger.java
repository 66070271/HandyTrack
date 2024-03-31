package net.handytrack.statusupdater;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class StatusChanger implements ActionListener{
//    private JFrame fr;
//    private JRadioButton receives, sortings, intransits, completes;
//    private JButton done;
//    private JPanel pn;
//    private ButtonGroup group;
//    private String statusnow, curstats;
//    private JLabel rtime, stime, ttime, ctime;
//    
//    public StatusChanger() {
//        fr = new JFrame("Change Status");
//        receives = new JRadioButton("Receive");
//        sortings = new JRadioButton("Sorting");
//        intransits = new JRadioButton("In Transit");
//        completes = new JRadioButton("Complete");
//        done = new JButton("Done");
//        group = new ButtonGroup();
//        pn = new JPanel();
//        
//        rtime = new JLabel();
//        stime = new JLabel();
//        ttime = new JLabel();
//        ctime = new JLabel();
//        
//        group.add(receives);        
//        group.add(sortings);
//        group.add(intransits);
//        group.add(completes);
//        
//        pn.add(receives);        
//        pn.add(sortings);
//        pn.add(intransits);
//        pn.add(completes);
//        pn.setLayout(new GridLayout(0, 1));
//      
//        fr.add(pn, BorderLayout.CENTER);
//        fr.add(done, BorderLayout.SOUTH);
//        
//        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        fr.setSize(200, 200);
//        fr.setVisible(false);
//        
//        done.addActionListener(this);
//        receives.addActionListener(this);
//        sortings.addActionListener(this);
//        completes.addActionListener(this);
//        intransits.addActionListener(this);
//        
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//       if (receives.isSelected()) { this.statusnow = "Receive"; }
//       else if (sortings.isSelected()) { this.statusnow = "Sorting"; }
//       else if (intransits.isSelected()) { this.statusnow = "In Transit"; }
//       else if (completes.isSelected()) { this.statusnow = "Complete"; }
//       else { JOptionPane.showMessageDialog(fr, "Please choose the status!", "Error!", JOptionPane.WARNING_MESSAGE); } 
////       fr.dispose(); 
//    }
//    
//    public JFrame getFr() {
//        return fr;
//    }
//    public String getStatus() {
//        return statusnow;
//    }
//    public JButton getDone(){
//        return done;
//    }
//    
//    public void setCurStats(String s) {
//        this.curstats = s;
//    }
//    public String getCurStats() {
//        return this.curstats;
//    }
//    public JRadioButton getReceives() {
//        return receives;
//    }
//    public JRadioButton getSortings() {
//        return sortings;
//    }
//    public JRadioButton getInTransits() {
//        return intransits;
//    }
//    public JRadioButton getCompletes() {
//        return completes;
//    }
//    
//    //    public static void main(String args[]) {
////        try {
////                UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
////            }  catch(Exception ex ) {
////                System.err.println( "Failed to initialize LaF" );
////    } 
////            new StatusChanger();
////    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StatusChanger implements ActionListener {
    private JFrame fr;
    private JRadioButton receives, sortings, intransits, deliverys, completes;
    private JButton done;
    private JPanel pn, pn2, pn3;
    private ButtonGroup group;
    private String statusnow;
    private JLabel rtime, stime, ttime, dtime, ctime;

    public StatusChanger() {
        fr = new JFrame("Change Status");
        receives = new JRadioButton("Receive");
        sortings = new JRadioButton("Sorting");
        intransits = new JRadioButton("In Transit");
        deliverys = new JRadioButton("Delivery");
        completes = new JRadioButton("Complete");
        done = new JButton("Done");
        group = new ButtonGroup();
        pn = new JPanel();

        pn2 = new JPanel();
        pn3 = new JPanel();
        rtime = new JLabel("");
        stime = new JLabel("");
        ttime = new JLabel("");
        dtime = new JLabel("");
        ctime = new JLabel("");

        group.add(receives);
        group.add(sortings);
        group.add(intransits);
        group.add(deliverys);
        group.add(completes);

        pn.add(receives);
        pn.add(sortings);
        pn.add(intransits);
        pn.add(deliverys);
        pn.add(completes);
        pn.setLayout(new GridLayout(0, 1));
        pn2.setLayout(new GridLayout(5, 1));
        pn2.add(rtime);
        pn2.add(stime);
        pn2.add(ttime);
        pn2.add(dtime);
        pn2.add(ctime);

        pn3.setLayout(new GridLayout(1, 2));
        pn3.add(pn);
        pn3.add(pn2);
        fr.add(pn3, BorderLayout.CENTER);
        fr.add(done, BorderLayout.SOUTH);

        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.setSize(200, 200);
        fr.setVisible(false);

        done.addActionListener(this);
        receives.addActionListener(this);
        sortings.addActionListener(this);
        completes.addActionListener(this);
        deliverys.addActionListener(this);
        intransits.addActionListener(this);

    }

    @Override
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
//       fr.dispose(); 
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

    public JLabel getStime() {
        return stime;
    }

    public JLabel getTtime() {
        return ttime;
    }

    public JLabel getDtime() {
        return dtime;
    }

    public JLabel getCtime() {
        return ctime;
    }
}
