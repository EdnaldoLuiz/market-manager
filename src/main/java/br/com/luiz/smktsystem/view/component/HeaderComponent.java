package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.utils.javax.CustomColor;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;
import br.com.luiz.smktsystem.view.panel.LoginPanel;

public class HeaderComponent extends JPanel {

    private EmployeerService employeerService;

    public HeaderComponent(EmployeerService employeerService) {
        this.employeerService = employeerService;
        setPreferredSize(new Dimension(getWidth(), 100));
        setBackground(CustomColor.MAIN_RED);

        setLayout(new BorderLayout());

        ImageIcon scaledIcon = ResizeIcon.createResizedIcon("/icons/market-manager-logo.png", 250, 80);
        JLabel logoLabel = new JLabel(scaledIcon);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));
        add(logoLabel, BorderLayout.WEST);

        ImageIcon scaledIconRight = ResizeIcon.createResizedIcon("/icons/logout.png", 45, 45);
        JLabel logoLabelRight = new JLabel(scaledIconRight);
        logoLabelRight.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 30));
        logoLabelRight.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                performLogout();
            }
        });

        add(logoLabelRight, BorderLayout.EAST);
    }

    private void performLogout() {
        SwingUtilities.getWindowAncestor(this).dispose();
        new LoginPanel(employeerService);
    }
}