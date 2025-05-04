package com.example;
import java.awt.*;
import javax.swing.*;

public class MarkaPanel extends AbstractMiniPanel {
    public MarkaPanel() {

//        setPreferredSize(new Dimension(250,100));
        setBorderName("Марка");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами


        addField(this, gbc, 0, "Название:");
        addField(this, gbc, 1, "Описание:");
        addField(this, gbc, 2, "Автор:");

//
//
//        addField(mainPanel, gbc, 0, "Название:");
//        addField(mainPanel, gbc, 1, "Описание:");
//        addField(mainPanel, gbc, 2, "Автор:");
//
//
//        add(new JLabel("Марка/Модель:"));
//        add(new JTextField(10));
//
//        add(new JLabel("VIN"));
//        add(new JTextField("FWAFXO9873VV0984"));
//        setBorder(BorderFactory.createLineBorder(Color.black));

    }
}


