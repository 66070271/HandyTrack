package net.handytrack.HANDYTRACKMAIN;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;
import net.handytrack.database.DBquery;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class BarChart1 extends JPanel {

    private Map<String, Integer> frequencyMap;
    private int barWidth = 50;
    private  Color[] colors = {Color.decode("#4CAF50"), Color.decode("#2196F3"), Color.decode("#FFC107"),
            Color.decode("#E91E63"), Color.decode("#9C27B0")};
    private final Font labelFont = new Font("Arial", Font.BOLD, 12);

    public BarChart1() {
        fetchDataFromDatabase();
    }

    private void fetchDataFromDatabase() {
        frequencyMap = new HashMap<>();

        try {
            DBquery dbQuery = DBquery.getInstance();
            ResultSet rs = dbQuery.getSelect("SELECT Status FROM product");

            while (rs.next()) {
                String status = rs.getString("Status");
                frequencyMap.put(status, frequencyMap.getOrDefault(status, 0) + 1);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (frequencyMap.isEmpty()) {
            return; // No data to display
        }

        int maxValue = frequencyMap.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        int maxHeight = getHeight() - 50; // Leave space for labels

        int x = 30; // Start x-coordinate
        int colorIndex = 0;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            String status = entry.getKey();
            int frequency = entry.getValue();

            int barHeight = (int) ((double) frequency / maxValue * maxHeight);

            g.setColor(colors[colorIndex]); // Set color
            g.fillRect(x, getHeight() - barHeight - 25, barWidth, barHeight); // Draw bar

            g.setColor(Color.BLACK);
            g.drawString(status, x + (barWidth / 2) - (g.getFontMetrics().stringWidth(status) / 2), getHeight() - 10); // Add description under the bar

            x += barWidth + 15; // Add some space between bars
            colorIndex = (colorIndex + 1) % colors.length; // Move to the next color
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
        int width = frequencyMap.size() * (barWidth + 28); // Add space between bars
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