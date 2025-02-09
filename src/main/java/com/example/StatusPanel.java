package com.example;
import java.awt.*;
import javax.swing.*;

public class StatusPanel extends JPanel {
    public StatusPanel() {
        setLayout(new GridLayout(3, 2)); // Две строки, два столбца

        add(new JLabel("КСИ:"));
        add(new JTextField(10));

        add(new JLabel("УСИ:"));
        add(new JTextField(10));

        add(new JLabel("Наличие голосового сообщения:"));
        JTextField textField = new JTextField("ДА");
        textField.setEditable(false);
        add(textField);

    }
}


