package com.example;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel{
//
//    private JTextArea logText;
//
//    public LogPanel(){
//        add(new Label("Log here"));
//        logText = new JTextArea(3,100);
//
//        JScrollPane scrollPane = new JScrollPane(logText);
//        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
//
//        add(scrollPane);
//    }
//
//    public void addLog(String s){
//        logText.append(s);
//    }


    private JTextArea logArea;

    public LogPanel() {

        // Создаём текстовую область для лога
        logArea = new JTextArea(5, 100);
        logArea.setEditable(false);               // Запретить редактирование пользователем
        logArea.setLineWrap(true);                 // Перенос строк
        logArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);


        // Для примера: сразу выводим тестовые логи
        log("Программа запущена.");
        log("Загружена конфигурация.");
        log("Соединение с базой установлено.");
    }

    // Метод для добавления записей в лог
    public void log(String message) {
        logArea.append(message + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength()); // Автоматическая прокрутка вниз
    }


}
