package com.example;

public class MapPanel extends AbstractPanel{
    public MapViewer map;
    public MapPanel(){
        setBorderName("Карта");
        map = new MapViewer();
        add(map);
    }



}
