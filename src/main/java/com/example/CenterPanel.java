package com.example;

import javax.swing.*;
import java.awt.*;

public class CenterPanel extends AbstractPanel{

    public CenterPanel(){
        setBorderName("Данные звонка");
        //setLayout(new BorderLayout());
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));


        //        setPreferredSize(new Dimension(400,400));
    }


}
