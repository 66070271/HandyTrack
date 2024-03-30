package net.handytrack.Login;

import java.awt.*;
import javax.swing.*;

public class ImageUtils {
    public static ImageIcon getScaledImageIcon(ImageIcon icon, int width, int height) {
        Image scaledImage = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
