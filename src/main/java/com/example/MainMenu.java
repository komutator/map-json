package com.example;

import javax.swing.*;

public class MainMenu extends JMenuBar {
    public MainMenu(JFrame frame) {
        // Главное меню "Файл"
        JMenu fileMenu = new JMenu("Файл");

        // Пункты меню
        JMenuItem openItem = new JMenuItem("Открыть");
        JMenuItem exitItem = new JMenuItem("Выход");



        // Меню "Помощь"
        JMenu helpMenu = new JMenu("Помощь");
        JMenuItem aboutItem = new JMenuItem("О программе");

        aboutItem.addActionListener(e ->
                JOptionPane.showMessageDialog(frame,
                        "ЭРА-ГЛОНАСС\n\nДанные испытаний\n\nВерсия 0.7",
                        "О программе", JOptionPane.INFORMATION_MESSAGE)
        );


        // Обработчик "Открыть"
        openItem.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Открытие файла"));

        // Обработчик "Выход"
        exitItem.addActionListener(e -> System.exit(0));

        // Сборка меню
        fileMenu.add(openItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        helpMenu.add(aboutItem);

        this.add(fileMenu);
        this.add(Box.createHorizontalGlue());
        this.add(helpMenu);


    }
}
