package br.com.luiz.smktsystem.utils.javax.icon;

import java.awt.Image;

import javax.swing.ImageIcon;


public class ResizeIcon {

    public static ImageIcon createResizedIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
