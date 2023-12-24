package br.com.luiz.smktsystem.view.component;

import javax.swing.*;
import java.awt.*;

public class FooterComponent extends JPanel {

    public FooterComponent() {
        setPreferredSize(new Dimension(getWidth(), 50)); // Define a altura do painel de rodapé
        setBackground(Color.GREEN);

        // Adicione componentes ao rodapé, se necessário
        JLabel label = new JLabel("Footer Component");
        label.setForeground(Color.WHITE);
        add(label);
    }
}
