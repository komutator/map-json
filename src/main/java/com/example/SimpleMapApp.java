package com.example;
import java.awt.*;

import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleMapApp {

    public static void main(String[] args) {
        // Читаем координаты из файла test.json
        String jsonString = readFileAsString("test.json");
        if (jsonString == null) {
            System.err.println("Ошибка чтения JSON");
            return;
        }

        // Парсинг JSON
        JSONObject json = new JSONObject(jsonString);
        double latitude = json.getDouble("latitude");
        double longitude = json.getDouble("longitude");

        // Создаем карту
        JMapViewer mapViewer = new JMapViewer();
        // mapViewer.setDisplayPosition(latitude,longitude,10);


        // Устанавливаем уровень масштабирования
        mapViewer.setZoom(10);
        int zoom = 10;  // Уровень масштабирования
        mapViewer.setZoom(zoom);
        
        Coordinate coordinate = new Coordinate(latitude, longitude);
        mapViewer.setDisplayPosition(coordinate, zoom);
        

//        mapViewer.setDisplayPositionByLatLon(latitude, longitude, 10);  // Устанавливаем масштаб 10 (городской уровень)




        // Добавляем точку на карту
        MapMarkerDot marker = new MapMarkerDot(latitude, longitude);
        marker.setBackColor(java.awt.Color.RED);  // Цвет маркера
        mapViewer.addMapMarker(marker);




        // Создаем окно для отображения карты
        JFrame frame = new JFrame("Эра-Глонасс");
        // помещение иконки на frame

        StatusPanel statusPanel = new StatusPanel();
        MarkaPanel markaPanel = new MarkaPanel();


        ImageIcon icon = new ImageIcon("./resource/icon.png");
        frame.setIconImage(icon.getImage());

        JPanel labelPanel = new JPanel();
        JLabel callLabel = new JLabel("Карточка вызова");
        labelPanel.add(callLabel);




        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.getContentPane().add(BorderLayout.NORTH, labelPanel);
        frame.getContentPane().add(BorderLayout.WEST, statusPanel);
        frame.getContentPane().add(BorderLayout.CENTER, markaPanel);
        frame.getContentPane().add(BorderLayout.EAST, mapViewer);
//        frame.setLayout(new GridLayout(2, 2));
//        frame.add(mapViewer);


//        frame.add(statusPanel);
        frame.setVisible(true);
    }

    // Метод для чтения содержимого файла в строку
    private static String readFileAsString(String filePath) {
        try {
            return new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
