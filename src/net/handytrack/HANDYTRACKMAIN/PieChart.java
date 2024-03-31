//package net.handytrack.HANDYTRACKMAIN;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.*;
//import java.util.*;
//import java.util.List;
//import java.sql.*;
//
//public class PieChart extends JPanel {
//
//    private double[] values;
//    private String[] labels;
//    private Color[] colors;
//
//    public PieChart(double[] values, String[] labels, Color[] colors) {
//        this.values = values;
//        this.labels = labels;
//        this.colors = colors;
//    }
//    public PieChart() {
//        fetchDataFromDatabase();
//    }
//    private void fetchDataFromDatabase() {
//        // สมมติว่าคุณมีเมทอดในการเชื่อมต่อกับฐานข้อมูล SQLite และดึงข้อมูล ที่นี่คุณต้องแทนที่โค้ดนี้ด้วยการเชื่อมต่อและส่งคำสั่ง SQL จริงของคุณ
//        List<Double> valueList = new ArrayList<>();
//        List<String> labelList = new ArrayList<>();
//        List<Color> colorList = new ArrayList<>();
//        try {
//            // เชื่อมต่อกับฐานข้อมูล SQLite และดึงข้อมูล
//            Connection conn = YourDatabaseConnection.getConnection();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM PieData");
//
//            // ดึงข้อมูลจาก ResultSet
//            while (rs.next()) {
//                valueList.add(rs.getDouble("value"));
//                labelList.add(rs.getString("label"));
//                colorList.add(new Color(rs.getInt("red"), rs.getInt("green"), rs.getInt("blue")));
//            }
//        }catch (Exception ex){
//            ex.printStackTrace();
//        }
//        values = valueList.stream().mapToDouble(Double::doubleValue).toArray();
//        labels = labelList.toArray(new String[0]);
//        colors = colorList.toArray(new Color[0]);
//    }
//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        frame.setSize(400, 300);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(false);
//    }
//}
