package com.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainTimer{

    private Timer timer;

    public MainTimer(LabelPanel panel){

        timer = new Timer(1000, new ActionListener() {

            int seconds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                panel.setText("Время: " + seconds + " сек");
//                log.log("Из таймера " + seconds);
            }
        });

    }

    public void start(){
        timer.start();
    }
}
