package br.com.luiz.smktsystem.utils.products;
import java.awt.Image;
import java.net.URL;

import javax.swing.ImageIcon;

public class ResizeIcon {

    public static ImageIcon createResizedIcon(String path, int width, int height) {
        URL imageUrl = ResizeIcon.class.getResource(path);
        if (imageUrl == null) {
            imageUrl = ResizeIcon.class.getResource("/icons/forgot-password.png");
        }
        ImageIcon originalIcon = new ImageIcon(imageUrl);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
