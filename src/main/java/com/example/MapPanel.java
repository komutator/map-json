package com.example;

import java.awt.*;

public class MapPanel extends AbstractPanel{
    public MapViewer map;
    public MapPanel(){
        setBorderName("Карта");
        setLayout(new BorderLayout());
        map = new MapViewer();
        add(map);
    }



}
