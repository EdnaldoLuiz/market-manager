package br.com.luiz.smktsystem.utils.javax;

import java.awt.*;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollbar extends BasicScrollBarUI {

    @Override
    protected void configureScrollBarColors() {
        super.configureScrollBarColors();
        trackColor = CustomColor.DARK_GRAY;
    }
}