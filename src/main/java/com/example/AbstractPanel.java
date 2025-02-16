package com.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AbstractPanel extends JPanel{

    private String borderName;

    public void setBorderName(String borderName) {
        this.borderName = borderName;
        setBorder(BorderFactory.createTitledBorder(borderName));
    }

    public AbstractPanel() {
//        setBorder(BorderFactory.createLineBorder(Color.black));
        borderName = "Box";
        setBorder(BorderFactory.createTitledBorder(borderName));
    }
}
