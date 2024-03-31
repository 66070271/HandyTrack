package net.handytrack.Login;

import javax.swing.*;
import java.awt.*;

public class ImageUtils {
    public static ImageIcon getScaledImageIcon(ImageIcon icon, int width, int height) {
        Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
