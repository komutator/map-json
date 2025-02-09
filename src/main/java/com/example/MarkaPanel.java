package com.example;
import java.awt.*;
import javax.swing.*;

public class MarkaPanel extends JPanel {
    public MarkaPanel() {
        setLayout(new GridLayout(2, 2)); // Две строки, два столбца

        add(new JLabel("Марка/Модель:"));
        add(new JTextField(10));

        add(new JLabel("VIN"));
        add(new JTextField("FWAFXO9873VV0984"));
    }
}


