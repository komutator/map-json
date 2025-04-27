package com.example;
import java.awt.*;

import com.example.ConfigManager.ConfigManager;
import com.example.db.DatabaseManager;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SimpleMapApp {

    public static void main(String[] args) {

        // Загружаем конфигурацию программы

        ConfigManager config = new ConfigManager("config.ini");
        config.loadOrCreateDefault();

        // Чтение значения
        String myid = config.get("myid");
        System.out.println("MyID: " + myid);
        String connectionString = config.get("connectionString");
        System.out.println("MyID: " + myid);

        // Изменение значения
//        config.set("myid", "5678");
//        config.save();
//        System.out.println("Новое значение myid сохранено.");



//  Подключаемся к БД
        try {
            Connection conn = DatabaseManager.getConnection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM jsondata");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                System.out.println("Id: " + rs.getString("id") + " Json: " + rs.getString("json"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DatabaseManager.closeConnection();
        }


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





        // Создаем окно для отображения карты
        JFrame frame = new JFrame("Эра-Глонасс");
        // помещение иконки на frame
        ImageIcon icon = new ImageIcon("./resource/icon.png");
        frame.setIconImage(icon.getImage());



        StatusPanel statusPanel = new StatusPanel();
        statusPanel.setBorderName("Статус");
        statusPanel.setPreferredSize(new Dimension(290,80));
        MarkaPanel markaPanel = new MarkaPanel();
        markaPanel.setBorderName("Марка");
        markaPanel.setPreferredSize(new Dimension(290,80));
        LabelPanel labelPanel = new LabelPanel();
        labelPanel.setPreferredSize(new Dimension(200,30));

        ButtonsPanel buttonsPanel = new ButtonsPanel();

//        JLabel callLabel = new JLabel("Карточка вызова");
//        labelPanel.add(callLabel);

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(350,300));
        westPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        westPanel.add(statusPanel);
        westPanel.add(markaPanel);
        westPanel.add(buttonsPanel);

        MapViewer mapViewer = new MapViewer();
        mapViewer.setMarker(latitude, longitude);
        mapViewer.setDisplayCenter(latitude, longitude);


        DatabaseListPanel databaseListPanel = new DatabaseListPanel();

        // Устанавливаем слушателя выбора
        databaseListPanel.setDatabaseListListener(selectedItem -> {
            System.out.println("🔔 Вы выбрали: " + selectedItem);
            // Здесь можно делать что угодно с выбранной строкой
        });


        westPanel.add(databaseListPanel);



        Timer timer = new Timer(1000, new ActionListener() {

            int seconds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                labelPanel.setText("Время: " + seconds + " сек");
            }
        });

        timer.start();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.getContentPane().add(BorderLayout.NORTH, labelPanel);
        frame.getContentPane().add(BorderLayout.WEST, westPanel);
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
