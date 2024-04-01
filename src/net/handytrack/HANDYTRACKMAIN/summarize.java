package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class summarize extends JPanel implements ActionListener {
    private JPanel part1,part2,part3,part4;
    private JButton jum,jum2;
    private JLabel jlum1, jlum2;

    public summarize() {
        part1 = new JPanel();
        part2 = new JPanel();
        part3 = new JPanel();
        part4 = new JPanel();
        jlum1 = new JLabel("Summary all items to PieChart", SwingConstants.CENTER);
        jlum1.setFont(new Font("Arial", Font.PLAIN, 28));
        jlum2 = new JLabel("Showing Status Items", SwingConstants.CENTER);
        jlum2.setFont(new Font("Arial", Font.PLAIN, 28));
        jum2 = new JButton("Create2");
        jum = new JButton("Create");

        this.setLayout(new GridLayout(4, 1));
        part1.setLayout((new GridLayout(2,1)));
        part3.setLayout(new GridLayout(2,1));
        part1.add(jum);
        part1.add(jlum1);

        part3.add(jum2);
        part3.add(jlum2);

        this.add(part1);
        this.add(part2);
        this.add(part3);
        this.add(part4);

        // เพิ่ม ActionListener ให้กับ piechartButton
        jum.addActionListener(this);
        jum2.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource().equals(jum)) {
                // ลบ JInternalFrame เก่าทิ้ง (ถ้ามี)
                Component[] components = part2.getComponents();
                for (Component component : components) {
                    if (component instanceof JInternalFrame) {
                        part2.remove(component);
                    }
                }
                // สร้าง JInternalFrame ใหม่และเพิ่มลงใน part2
                JInternalFrame internalFrame = new JInternalFrame("Show Items Frequency");
                internalFrame.getContentPane().add(new BarChart());
                internalFrame.setVisible(true);
                internalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                part2.setLayout(new BorderLayout());
                part2.add(internalFrame, BorderLayout.CENTER);
            }
            if (e.getSource().equals(jum2)){
                // ลบ JInternalFrame เก่าทิ้ง (ถ้ามี)
                Component[] components = part4.getComponents();
                for (Component component : components) {
                    if (component instanceof JInternalFrame) {
                        part4.remove(component);
                    }
                }
                // สร้าง JInternalFrame ใหม่และเพิ่มลงใน part4
                JInternalFrame internalFrame = new JInternalFrame("Show Status");
                internalFrame.getContentPane().add(new BarChart1());
                internalFrame.setVisible(true);
                internalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                part4.setLayout(new BorderLayout());
                part4.add(internalFrame, BorderLayout.CENTER);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
