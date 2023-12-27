package br.com.luiz.smktsystem.view.modal;

import javax.swing.*;
import java.awt.*;

public class ResetPasswordModal {

    public static void showSuccessDialog(Component parentComponent) {
        ImageIcon successIcon = createResizedIcon("src/main/resources/icons/success.png", 100, 100);
        JLabel iconLabel = new JLabel(successIcon);
        JTextArea textArea = new JTextArea("Um código para redefinição de senha será enviado. Verifique sua caixa de e-mail.");
        customizeDialog(textArea);
        showCustomDialog(parentComponent, iconLabel, textArea, Color.GREEN);
    }

    public static void showErrorDialog(Component parentComponent) {
        ImageIcon errorIcon = createResizedIcon("src/main/resources/icons/error.png", 100, 100);
        JLabel iconLabel = new JLabel(errorIcon);
        JTextArea textArea = new JTextArea("O e-mail informado ainda não foi cadastrado.");
        customizeDialog(textArea);
        showCustomDialog(parentComponent, iconLabel, textArea, Color.RED);
    }

    private static void customizeDialog(JTextArea textArea) {
        Font textFont = new Font("Arial", Font.PLAIN, 18);
        textArea.setFont(textFont);
        textArea.setMargin(new Insets(20, 20, 20, 20));
        textArea.setOpaque(false);
        textArea.setBackground(new Color(0, 0, 0, 0));
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
    }

    private static void showCustomDialog(Component parentComponent, JLabel iconLabel, JTextArea textArea, Color buttonColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(iconLabel, BorderLayout.CENTER);
        panel.add(textArea, BorderLayout.SOUTH);
        panel.setPreferredSize(new Dimension(400, 200));

        JButton okButton = new JButton("OK");
        okButton.setBackground(buttonColor);
        okButton.setForeground(Color.WHITE);
        okButton.setFont(new Font("Arial", Font.BOLD, 16));
        okButton.setPreferredSize(new Dimension(80, 30));

        okButton.addActionListener(e -> {
            Container parentContainer = SwingUtilities.getWindowAncestor(panel);
            if (parentContainer instanceof Window) {
                ((Window) parentContainer).dispose();
            }
        });

        Object[] options = {okButton};

        JOptionPane.showOptionDialog(
                parentComponent,
                panel,
                null,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    private static ImageIcon createResizedIcon(String path, int width, int height) {
        ImageIcon originalIcon = new ImageIcon(path);
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
