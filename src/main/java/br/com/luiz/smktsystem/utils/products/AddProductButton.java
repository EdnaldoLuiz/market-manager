package br.com.luiz.smktsystem.utils.products;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AddProductButton extends JButton {

    public AddProductButton(ActionListener actionListener) {
        super("Adicionar Produto");
        addActionListener(actionListener);
    }
}

