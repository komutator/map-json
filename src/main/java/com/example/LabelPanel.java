package com.example;
import java.awt.*;
import javax.swing.*;

public class LabelPanel extends JPanel {

    private JLabel textLabel;

    public void setText(String s) {
        textLabel.setText(s);
    }
    public LabelPanel() {
//        setLayout(new GridLayout(3, 2)); // Две строки, два столбца

        textLabel = new JLabel("Карточка вызова");
        add(textLabel);
        setBorder(BorderFactory.createLineBorder(Color.black));

    }
}


