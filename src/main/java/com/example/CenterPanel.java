package com.example;

import java.awt.*;

public class CenterPanel extends AbstractPanel{

    public CenterPanel(){
        add(new MapViewer());
        setBorderName("Карта");
        setPreferredSize(new Dimension(400,400));
    }


}
