package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class summarize extends JPanel implements ActionListener {
    private JPanel Bigpart;
    private JButton jum;
    private JLabel jum1;
    public summarize() {
        Bigpart = new JPanel();
        jum1 = new JLabel("Summary all items to PieChart", SwingConstants.LEFT);
        jum1.setFont(new Font("Arial", Font.PLAIN, 28));
        jum = new JButton("Create");

        Bigpart.setLayout(new GridLayout(1, 1));
        Bigpart.add(jum);
        Bigpart.add(jum1);

        // เพิ่ม ActionListener ให้กับ piechartButton
        jum.addActionListener(this);

        this.setBackground(new Color(210, 224, 251));
        Bigpart.setBackground(new Color(210,224,251));
        this.add(Bigpart);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(jum)) {
                JFrame frame = new JFrame("Show Items Frequency");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.getContentPane().add(new BarChart()); // สร้าง MDI ของ BarChart
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
