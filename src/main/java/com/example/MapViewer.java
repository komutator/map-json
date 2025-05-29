package com.example;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.awt.*;

public class MapViewer extends JMapViewer {

    public MapViewer() {
        int zoom = 10;  // Уровень масштабирования
        setZoom(zoom);

    }


    public void setDisplayCenter(double latitude, double longitude){
        Coordinate coordinate = new Coordinate(latitude, longitude);
        setDisplayPosition(coordinate, zoom);
    }


    // Добавляем точку на карту
    public void setMarker(double latitude, double longitude) {
        MapMarkerDot marker = new MapMarkerDot(latitude, longitude);
        marker.setBackColor(java.awt.Color.RED);  // Цвет маркера
        addMapMarker(marker);
    }

    public void setPosition(double latitude, double longitude){
        // Удалить все маркеры
        getMapMarkerList().clear();
        // Обновить отображение
        setDisplayCenter(latitude, longitude);
        setMarker(latitude, longitude);
        repaint();

    }


}
