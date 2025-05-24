package com.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AbstractMiniPanel extends AbstractPanel {

    public class PanelItem{
        String labelText;
        String valueText;
        JLabel label;
        JTextField textField;

        public PanelItem(){
            labelText = "";
            valueText = "";
            label = new JLabel("");
            textField = new JTextField("");
        }


    }

    public ArrayList<PanelItem> panelItems;

    public AbstractMiniPanel() {
        setLayout(new GridBagLayout());
        panelItems = new ArrayList<>();
    }

    public static void addValue(JPanel panel, GridBagConstraints gbc, int yPos, String editText) {
        gbc.gridy = yPos;

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // только ширина
        gbc.weightx = 1.0;
        gbc.weighty = 0; // важно!



        JTextField textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 24)); // фиксируем высоту
        textField.setEditable(false);
        textField.setText(editText);
        panel.add(textField, gbc);

    }


    public void addField(JPanel panel, GridBagConstraints gbc, int yPos, String labelText, String editText) {
//        gbc.gridx = 0; // колонка 0
//        gbc.gridy = yPos; // строка yPos
//        gbc.anchor = GridBagConstraints.EAST; // выравнивание метки вправо
//        JLabel label = new JLabel(labelText);
//        panel.add(label, gbc);
//
//        gbc.gridx = 1; // колонка 1
//        gbc.anchor = GridBagConstraints.WEST; // поле ввода — выравниваем влево
//        gbc.fill = GridBagConstraints.HORIZONTAL; // растягивать по горизонтали
//        gbc.weightx = 1.0; // поле ввода получает дополнительное пространство
//        JTextField textField = new JTextField(20);
//        panel.add(textField, gbc);


        gbc.gridx = 0;
        gbc.gridy = yPos;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;


        PanelItem p = new PanelItem();
        p.labelText = labelText;
        p.label.setText(labelText);
        p.textField.setText(editText);

        panel.add(p.label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL; // только ширина
        gbc.weightx = 1.0;
        gbc.weighty = 0; // важно!


        p.textField.setPreferredSize(new Dimension(200, 24)); // фиксируем высоту
        p.textField.setEditable(false);
        p.textField.setText(editText);
        panel.add(p.textField, gbc);

        panelItems.add(p);


    }

}
