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
        int zoom = 10;  // Уровень масштабирования
        mapViewer.setZoom(zoom);
        
        Coordinate coordinate = new Coordinate(latitude, longitude);
        mapViewer.setDisplayPosition(coordinate, zoom);

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
        labelPanel.setPreferredSize(new Dimension(200,30));

        JLabel callLabel = new JLabel("Карточка вызова");
        labelPanel.add(callLabel);

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(300,300));
        westPanel.add(statusPanel);
        westPanel.add(markaPanel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.getContentPane().add(BorderLayout.NORTH, labelPanel);

        statusPanel.setPreferredSize(new Dimension(200,50));
        frame.getContentPane().add(BorderLayout.WEST, westPanel);

        markaPanel.setPreferredSize(new Dimension(200,50));

        frame.getContentPane().add(BorderLayout.CENTER, mapViewer);
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
