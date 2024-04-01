package net.handytrack.HANDYTRACKMAIN;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class circle extends JPanel {

    private Image image;


    public circle() {
        this.setBackground(new Color(204, 219, 253));
        image = new ImageIcon("resources/Picture/newuser.png").getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // คำนวณเส้นผ่านศูนย์กลางและพิกัดของวงกลม
        int diameter = Math.min(getWidth(), getHeight());
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        // สร้างเป็นรูปร่างวงกลม
        Shape oldClip = g.getClip();
        Ellipse2D.Double circle = new Ellipse2D.Double(x, y, diameter, diameter);
        g.setClip(circle);

        // วาดภาพ
        if (image != null) {
            int imgWidth = image.getWidth(this);
            int imgHeight = image.getHeight(this);

            // ปรับขนาดของภาพให้พอดีกับวงกลม
            double scaleFactor = Math.min((double) diameter / imgWidth, (double) diameter / imgHeight);
            int scaledWidth = (int) (imgWidth * scaleFactor);
            int scaledHeight = (int) (imgHeight * scaleFactor);
            int imgX = x + (diameter - scaledWidth) / 2;
            int imgY = y + (diameter - scaledHeight) / 2;

            // วาดภาพทับวงกลม
            g.drawImage(image, imgX, imgY, scaledWidth, scaledHeight, this);
        }
        // เรียกคืน เดิม
        g.setClip(oldClip);
    }

    @Override

    public Dimension getPreferredSize() {
        return new Dimension(150, 150); // กำหนดขนาดของ JLabel วงกลม
    }

}

