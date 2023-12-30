package br.com.luiz.smktsystem.view.shared.modal;

import javax.swing.*;

import br.com.luiz.smktsystem.utils.javax.color.CustomColor;
import br.com.luiz.smktsystem.utils.javax.icon.ResizeIcon;

import java.awt.*;

public class Modal {

    private static ImageIcon createResizedIcon(String path, int width, int height) {
        return ResizeIcon.createResizedIcon(path, width, height);
    }

    public static void showSuccessDialog(Component parentComponent) {
        ImageIcon successIcon = createResizedIcon("src/main/resources/icons/success.png", 100, 100);
        JLabel iconLabel = new JLabel(successIcon);
        String successMessage = "Um código para redefinição de senha será enviado. Verifique sua caixa de e-mail.";
        showCustomDialog(parentComponent, iconLabel, successMessage, CustomColor.SUCESS_GREEN);
    }

    public static void showErrorDialog(Component parentComponent, String errorMessage) {
        ImageIcon errorIcon = createResizedIcon("src/main/resources/icons/error.png", 100, 100);
        JLabel iconLabel = new JLabel(errorIcon);
        showCustomDialog(parentComponent, iconLabel, errorMessage, Color.RED);
    }

    private static void showCustomDialog(Component parentComponent, JLabel iconLabel, String message, Color buttonColor) {
        JTextArea textArea = new JTextArea(message);
        customizeDialog(textArea);

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
}
