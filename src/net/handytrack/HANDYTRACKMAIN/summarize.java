package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class summarize extends JPanel implements ActionListener {
    private JPanel Bigpart;
    private JButton piechart;
    private JLabel forpiechart;

    public summarize() {
        Bigpart = new JPanel();
        forpiechart = new JLabel("Create for Piechart");
        piechart = new JButton("Create");

        Bigpart.setLayout(new GridLayout(1, 1));
        Bigpart.add(forpiechart);
        Bigpart.add(piechart);

        this.setBackground(new Color(228, 147, 179));
        this.add(Bigpart);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(piechart)) {

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
