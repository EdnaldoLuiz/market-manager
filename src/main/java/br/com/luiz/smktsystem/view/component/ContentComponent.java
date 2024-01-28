package br.com.luiz.smktsystem.view.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;


import br.com.luiz.smktsystem.service.EmployeerService;
import br.com.luiz.smktsystem.service.ProductService;
import br.com.luiz.smktsystem.service.dao.EmployeerDAO;
import br.com.luiz.smktsystem.service.dao.ProductDAO;
import br.com.luiz.smktsystem.utils.hibernate.JpaUtil;
import br.com.luiz.smktsystem.utils.products.ResizeIcon;
import br.com.luiz.smktsystem.view.panel.EmployeesPanel;
import br.com.luiz.smktsystem.view.panel.ProductsPanel;

public class ContentComponent extends JPanel {

    private JScrollPane scrollPane;
    private JPanel wideArea;
    private JPanel currentPanel;

    public ContentComponent() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));

        scrollPane = new JScrollPane();
        add(scrollPane, BorderLayout.CENTER);

        ImageIcon addIcon = ResizeIcon.createResizedIcon("/icons/add-product.png", 50, 50);
        JButton addButton = new JButton(addIcon);
        addButton.setPreferredSize(new Dimension(40, 40));
        addButton.setBackground(Color.RED);
        addButton.setBorderPainted(false);
        addButton.setFocusPainted(false);

        wideArea = new JPanel(new BorderLayout());
        wideArea.add(addButton, BorderLayout.SOUTH);

        setDefaultView(createProductsPanel());
    }

    private void setDefaultView(JPanel panel) {
        currentPanel = panel;
        scrollPane.setViewportView(currentPanel);
        revalidate();
        repaint();
    }

    private void updateViewForPanel(JPanel panel) {
        currentPanel = panel;
        scrollPane.setViewportView(currentPanel);
        revalidate();
        repaint();
    }

    public void handleSidebarOption(String option) {
        switch (option) {
            case "Mercadorias":
                updateViewForPanel(createProductsPanel());
                break;
            case "Funcionarios":
                updateViewForPanel(createEmployeesPanel());
                break;
        }
    }

    private JPanel createProductsPanel() {
        return new ProductsPanel(new ProductService(new ProductDAO(JpaUtil.getEntityManager())));
    }

    private JPanel createEmployeesPanel() {
        return new EmployeesPanel(new EmployeerService(new EmployeerDAO(JpaUtil.getEntityManager())));
    }
}
