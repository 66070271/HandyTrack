package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBquery;

public class BarChart1 extends JPanel {

    private Map<String, Integer> frequencyMap;
    private int barWidth = 50;
    private int[] colors = {0x4CAF50, 0x2196F3, 0xFFC107, 0xE91E63, 0x9C27B0};
    private final Font labelFont = new Font("Arial", Font.BOLD, 12);

    public BarChart1() {
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        frequencyMap = new HashMap<>();
        DBquery dbQuery = DBquery.getInstance();
        ResultSet rs = dbQuery.getSelect("SELECT Status FROM product");
        try {


            while (rs.next()) {
                String status = rs.getString("Status");
                frequencyMap.put(status, frequencyMap.getOrDefault(status, 0) + 1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            dbQuery.disconnect();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (frequencyMap.isEmpty()) {
            return; // กรณีไม่มีข้อมูล
        }

        int maxValue = frequencyMap.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        int maxHeight = getHeight() - 50; // การสร้างระยะห้างระหว่างช่อง

        int numBars = frequencyMap.size();
        int barWidth = (getWidth() - 50) / numBars;

        int x = 30; // เริ่มต้น
        int colorIndex = 0;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String status = entry.getKey();
            int frequency = entry.getValue();

            int barHeight = (int) ((double) frequency / maxValue * maxHeight);

            g.setColor(new Color(colors[colorIndex])); // Set color
            g.fillRect(x, getHeight() - 30 - barHeight, barWidth, barHeight); // วาดแท่ง

            g.setColor(Color.BLACK);
            g.drawString(status, x + (barWidth / 2) - (g.getFontMetrics().stringWidth(status) / 2), getHeight() - 5); // Add description under the bar

            x += barWidth + 10; // ระยะห่าง
            colorIndex = (colorIndex + 1) % colors.length; // เปลี่ยนสี
        }

        // Draw Y-axis scale
        g.setFont(labelFont);
        g.setColor(Color.BLACK);
        for (int i = 0; i <= maxValue; i++) {
            int y = getHeight() - 25 - i * maxHeight / maxValue;
            g.drawString(Integer.toString(i), 5, y);
        }

        // วาดแกน X
        g.drawString("", getWidth() / 2 , getHeight() - 5);
    }

    @Override
    public Dimension getPreferredSize() {
        int width = frequencyMap.size() * (barWidth + 10);
        return new Dimension(width, 300);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        JFrame frame = new JFrame("Show Status");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BarChart1());
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}