package com.example;
import java.awt.*;
import javax.swing.*;

public class MarkaPanel extends AbstractMiniPanel {
    public MarkaPanel() {

//        setPreferredSize(new Dimension(250,100));
        setBorderName("Марка");
//        setBackground(Color.cyan);
//      setPreferredSize(new Dimension(250, 100)); // ширина 200, высота 24 пикселя
//        setMinimumSize(new Dimension(250, 100));
//        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами


        addField(this, gbc, 0, "Название:", "Toyota Corolla");
        addField(this, gbc, 1, "Описание:", "Бежевый цвет");
        addField(this, gbc, 2, "Автор:", "Ямогото Чотокога");

        revalidate();
        repaint();

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


