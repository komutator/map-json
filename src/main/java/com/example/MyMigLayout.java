package com.example;
import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class MyMigLayout {


    public class Main {
        public static void main(String[] args) {
            JFrame frame = new JFrame("Пример MigLayout");
            frame.setLayout(new MigLayout());

            frame.add(new JLabel("Имя:"));
            frame.add(new JTextField(20), "wrap");

            frame.add(new JLabel("Пароль:"));
            frame.add(new JPasswordField(20), "wrap");

            frame.add(new JButton("Войти"), "span, center");

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        }
    }

}
