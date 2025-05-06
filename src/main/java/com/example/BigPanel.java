package com.example;
import java.awt.*;
import javax.swing.*;

public class BigPanel extends AbstractMiniPanel{



    public BigPanel(){

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Отступы между элементами


        addField(this, gbc, 0, "Наличие голосового соединения:", "Да, качество не определено");
        addField(this, gbc, 1, "Тип вызова:", "Автоматический");
        addField(this, gbc, 2, "Марка/Модель", "Yohun Yohun PASSION");
        addField(this, gbc, 3, "VIN", "LDP91E952RE201384");
        addField(this, gbc, 4, "Координаты", "56\"23\' 106.125, 37\"19\'09.387");


    }



}
