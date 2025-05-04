package com.example;

import javax.swing.*;
import java.awt.*;

public class LogPanel extends JPanel{

    private JTextArea logText;

    public LogPanel(){
        add(new Label("Log here"));
        logText = new JTextArea(3,100);

        JScrollPane scrollPane = new JScrollPane(logText);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(scrollPane);
    }

    public void addLog(String s){
        logText.append(s);
    }

}
