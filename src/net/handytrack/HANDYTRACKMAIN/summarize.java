//package net.handytrack.HANDYTRACKMAIN;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class summarize extends JPanel implements ActionListener {
//    private JPanel Bigpart;
//    private JButton piechartButton;
//    private JLabel forpiechart;
//    private PieChart pieChart;
//
//    public summarize() {
//        Bigpart = new JPanel();
//        forpiechart = new JLabel("Summary all items to PieChart", SwingConstants.LEFT);
//        forpiechart.setFont(new Font("Arial", Font.PLAIN, 28));
//        piechartButton = new JButton("Create");
//        pieChart = new PieChart(new double[]{25, 30, 45}, new String[]{"A", "B", "C"},
//                new Color[]{Color.RED, Color.GREEN, Color.BLUE});
//
//        Bigpart.setLayout(new GridLayout(1, 1));
//        Bigpart.add(forpiechart);
//        Bigpart.add(piechartButton);
//
//        // เพิ่ม ActionListener ให้กับ piechartButton
//        piechartButton.addActionListener(this);
//
//        this.setBackground(new Color(228, 147, 179));
//        this.add(Bigpart);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        try {
//            if (e.getSource().equals(piechartButton)) {
//                // แสดง pieChart
//                JFrame frame = new JFrame("แผนภูมิ Pie Chart");
//                frame.setSize(400, 300);
//                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                frame.getContentPane().add(pieChart);
//                frame.setVisible(true);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//    }
//}
