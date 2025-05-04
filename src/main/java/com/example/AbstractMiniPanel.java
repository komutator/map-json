package com.example;

import javax.swing.*;
import java.awt.*;

public class AbstractMiniPanel extends AbstractPanel {
    public AbstractMiniPanel() {
        setLayout(new GridBagLayout());

//        addField(this, gbc, 0, "Название:");
//        addField(this, gbc, 1, "Описание:");
//        addField(this, gbc, 2, "Автор:");
    }

    public static void addField(JPanel panel, GridBagConstraints gbc, int yPos, String labelText) {
        gbc.gridx = 0; // колонка 0
        gbc.gridy = yPos; // строка yPos
        gbc.anchor = GridBagConstraints.EAST; // выравнивание метки вправо
        JLabel label = new JLabel(labelText);
        panel.add(label, gbc);

        gbc.gridx = 1; // колонка 1
        gbc.anchor = GridBagConstraints.WEST; // поле ввода — выравниваем влево
        gbc.fill = GridBagConstraints.HORIZONTAL; // растягивать по горизонтали
        gbc.weightx = 1.0; // поле ввода получает дополнительное пространство
        JTextField textField = new JTextField(20);
        panel.add(textField, gbc);
    }

}
