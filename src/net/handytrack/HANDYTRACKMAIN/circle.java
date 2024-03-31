package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import java.awt.*;

public class circle extends JPanel {

    private ImageIcon icon;

    public circle() {
        this.setBackground(new Color(160, 233, 255));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;
        if (icon != null) {
            g.drawImage(icon.getImage(), x, y, diameter, diameter, null);
        } else {
            g.setColor(new Color(128, 128, 128));
            g.fillOval(x, y, diameter, diameter);
        }
    }

    @Override

    public Dimension getPreferredSize() {
        return new Dimension(150, 150); // กำหนดขนาดของ JLabel วงกลม
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
        repaint(); // เรียกวิธี paintComponent เพื่อวาดภาพใหม่
    }
};
