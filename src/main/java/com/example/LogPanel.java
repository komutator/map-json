package com.example;

import javax.swing.*;
import java.awt.*;

import javax.swing.text.*;

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


    private JTextPane logArea;
    private StyledDocument doc;
    private static final int MAX_LINES = 100; // Максимальное количество строк в логе

    public LogPanel() {

        // Создаём текстовую область для лога
//        logArea = new JTextPane(5, 100);
//        logArea.setEditable(false);               // Запретить редактирование пользователем
//        logArea.setLineWrap(true);                 // Перенос строк
//        logArea.setWrapStyleWord(true);
        setLayout(new BorderLayout());

        logArea = new JTextPane();
        logArea.setEditable(false);
        doc = logArea.getStyledDocument();


        int numberOfRows = 5;
        int rowHeight = logArea.getFontMetrics(logArea.getFont()).getHeight();
        int width = 900;
        int height = numberOfRows * rowHeight;

        Dimension fixedSize = new Dimension(width, height);

//        logArea.setPreferredSize(fixedSize);
//        logArea.setMinimumSize(fixedSize);
//        logArea.setMaximumSize(fixedSize);


        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);


        // Также фиксируем размер скролл-панели
        scrollPane.setPreferredSize(fixedSize);
        scrollPane.setMinimumSize(fixedSize);
        scrollPane.setMaximumSize(fixedSize);
        add(scrollPane);

        // Для примера: сразу выводим тестовые логи
//        log("Программа запущена.");
//        log("Загружена конфигурация.");
//        log("Соединение с базой установлено.");
    }



    public void log(String level, String message) {
        SimpleAttributeSet attr = new SimpleAttributeSet();

        switch (level.toUpperCase()) {
            case "INFO":
                StyleConstants.setForeground(attr, Color.BLUE);
                break;
            case "WARNING":
                StyleConstants.setForeground(attr, Color.ORANGE);
                break;
            case "ERROR":
                StyleConstants.setForeground(attr, Color.RED);
                StyleConstants.setBold(attr, true);
                break;
            default:
                StyleConstants.setForeground(attr, Color.BLACK);
                break;
        }

        try {
            limitLines();
            doc.insertString(doc.getLength(), "[" + level + "] " + message + "\n", attr);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        logArea.setCaretPosition(doc.getLength()); // Автопрокрутка
    }


    private void limitLines() throws BadLocationException {
        Element root = doc.getDefaultRootElement();
        int lineCount = root.getElementCount();

        if (lineCount > MAX_LINES) {
            Element firstLine = root.getElement(0);
            doc.remove(0, firstLine.getEndOffset());
        }
    }


}
