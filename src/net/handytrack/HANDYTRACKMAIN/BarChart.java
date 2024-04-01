package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBquery;


public class BarChart extends JPanel {

    private Map<String, Integer> frequencyMap;
    private int barWidth = 50;
    private  Color[] colors = {Color.decode("#4CAF50"), Color.decode("#2196F3"), Color.decode("#FFC107"),
            Color.decode("#E91E63"), Color.decode("#9C27B0")};
    private final Font labelFont = new Font("Arial", Font.BOLD, 12);

    public BarChart() {
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        frequencyMap = new HashMap<>();

        try {
            DBquery dbQuery = DBquery.getInstance();
            ResultSet rs = dbQuery.getSelect("SELECT type FROM product");

            while (rs.next()) {
                String type = rs.getString("type");
                frequencyMap.put(type, frequencyMap.getOrDefault(type, 0) + 1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
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

        int x = 50; // แกน x;
        int colorIndex = 0;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String type = entry.getKey();
            int frequency = entry.getValue();

            int barHeight = (int) ((double) frequency / maxValue * maxHeight);

            g.setColor(colors[colorIndex]); // set สี
            g.fillRect(x, getHeight() - barHeight - 25, barWidth, barHeight);

            g.setColor(Color.BLACK);
            g.drawString(type, x + (barWidth / 2) - (g.getFontMetrics().stringWidth(type) / 2), getHeight() - 10); // Add description under the bar

            x += barWidth + 15; // ระยะห่าง
            colorIndex = (colorIndex + 1) % colors.length;
        }

        // Draw Y-axis scale
        g.setFont(labelFont);
        g.setColor(Color.BLACK);
        for (int i = 0; i <= maxValue; i++) {
            int y = getHeight() - 25 - i * maxHeight / maxValue;
            g.drawString(Integer.toString(i), 5, y);
        }

        // Draw X-axis label
        g.drawString("", getWidth() / 2 - 20, getHeight() - 5);
    }

    @Override
    public Dimension getPreferredSize() {
        int width = frequencyMap.size() * (barWidth + 21); // Add space between bars
        return new Dimension(width, 300);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMaterialLighterIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        JFrame frame = new JFrame("Show Items Frequency");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new BarChart());
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);
    }
}