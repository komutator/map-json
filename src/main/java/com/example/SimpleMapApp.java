package com.example;
import java.awt.*;

import com.example.ConfigManager.ConfigManager;
import com.example.db.DatabaseManager;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import java.util.ArrayList;
import java.util.List;
import net.miginfocom.swing.MigLayout;

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
// todo: Подключить тесты
public class SimpleMapApp {

    public static void main(String[] args) {
        LogPanel log = new LogPanel();

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
        // todo: добавить MeggageBox об ошибке доступа в базу
        DatabaseManager databaseManager = new DatabaseManager("localhost", 3306, "eradb", "erauser","erapassword");


        try {
            databaseManager.connect();
            List<String[]>  result = databaseManager.executeQuery("SELECT * FROM msd");
            log.log("INFO","Выбрано " + result.size() + " значений");
            log.log("INFO","Выбрано " + result.get(0)[0] + " значение");

//            while (rs.next()) {
//                System.out.println("Id: " + rs.getString("l_id") + " Json: " + rs.getString("imei"));
//            }
            log.log("INFO","Подключение к базе установлено");


        } catch (SQLException e) {
            log.log("ERROR", e.getMessage());
            e.printStackTrace();
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








        StatusPanel statusPanel = new StatusPanel();
        statusPanel.setBorderName("Статус");
        statusPanel.setPreferredSize(new Dimension(290,80));

        BigPanel bigPanel = new BigPanel();

        MarkaPanel markaPanel = new MarkaPanel();
        MndPanel mndPanel = new MndPanel();
//        markaPanel.setBorderName("Марка");
//        markaPanel.setPreferredSize(new Dimension(290,80));

        LabelPanel labelPanel = new LabelPanel();
        labelPanel.setPreferredSize(new Dimension(200,30));

        ButtonsPanel buttonsPanel = new ButtonsPanel();

//        JLabel callLabel = new JLabel("Карточка вызова");
//        labelPanel.add(callLabel);

        WestPanel westPanel = new WestPanel();

//        westPanel.add(statusPanel);
//        westPanel.add(markaPanel);
//        westPanel.add(buttonsPanel);

//        MapViewer mapViewer = new MapViewer();
//        mapViewer.setMarker(latitude, longitude);
//        mapViewer.setDisplayCenter(latitude, longitude);

        MapPanel mapPanel = new MapPanel();
        mapPanel.map.setPosition(latitude, longitude);

        DatabaseListPanel databaseListPanel = new DatabaseListPanel(databaseManager);

        // Устанавливаем слушателя выбора ActionListener на изменение выбранного DatabasePanel
        databaseListPanel.setDatabaseListListener(selectedItem -> {
            System.out.println("🔔 Вы выбрали: " + selectedItem);
            log.log("INFO","Выбрано измерение " + selectedItem);


            Integer element_num = databaseListPanel.getCurrentSelectedIndex();
            log.log("INFO", "Выбран " + element_num + " элемент списка");
            MsdObject m = databaseListPanel.msdObject.get(element_num);

            double lat = HexToPos.HexToLat(m.pos_lat);
            double lon = HexToPos.HexToLon(m.pos_long);
            log.log("INFO", "Координаты:" + lat + " " + lon);
// todo: эту конструкцию заменить на mapPanel.CenterMarker
            mapPanel.map.setPosition(lat, lon);


            // todo: Запускать в отдельном потоке
            // Подгрузка городов ближайших к точке испытания
            // можно заранее их прогружаать в таблицу по координатам
            // можно вытягивать из локальной БД https://osmnames.org/download/
            // Можно в отдельном потоке онлайн запрашивать
            // Можно комбинировать

            //            try {
//                String city = ReverseGeocoder.getNearestCity(lat, lon);
//                log.log("INFO", city);
//                System.out.println("Ближайший город: " + city);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }



            bigPanel.panelItems.get(0).textField.setText(String.valueOf("НЕ ОПРЕДЕЛЕН 00"));
            bigPanel.panelItems.get(9).textField.setText(String.valueOf(m.tm));
//
//            bigPanel.fields.get(0).value = String.valueOf(m.pos_lat);
//            bigPanel.UpdateFields();



            // Здесь можно делать что угодно с выбранной строкой

//            String s = TextParser.convertToJSON();
//            mapViewer.setDisplayCenter(databaseListPanel.getLongitude());

        });


        westPanel.add(databaseListPanel);
        westPanel.add(buttonsPanel);




        // Создаем окно для отображения карты
        JFrame frame = new JFrame("Эра-Глонасс");
        // помещение иконки на frame
        ImageIcon icon = new ImageIcon("./resource/icon.png");
        frame.setIconImage(icon.getImage());




        JPanel centerPanel = new JPanel(new MigLayout());

        JPanel myLeftPanel = new JPanel(new MigLayout());
        myLeftPanel.setBackground(Color.YELLOW);
        myLeftPanel.setMaximumSize(new Dimension(500,900));
//        JPanel myRightPanel = new JPanel(new MigLayout());
        JPanel myRightPanel = new JPanel(new MigLayout("fill", "", ""));

        myRightPanel.setBackground(Color.PINK);
        Dimension d = new Dimension(800,500);
        myRightPanel.setPreferredSize(d);

        //myRightPanel.setMaximumSize(new Dimension(500,700));

        JButton testButton = new JButton("TEST BUTTON");
        // Установка ActionListener на кнопку TEST BUTTON
        testButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Кнопка нажата!");

                Integer element_num = databaseListPanel.getCurrentSelectedIndex();
                log.log("INFO", "Кнопка TEST BUTTON нажата");
                log.log("INFO", "Выбран " + element_num + " элемент списка");
//                log.log("INFO", "Выбраны " + databaseListPanel.getCurrentSelectedValues()[databaseListPanel.getCurrentSelectedIndex()] + " строки");


                MsdObject m = databaseListPanel.msdObject.get(element_num);
                log.log("INFO", "Выбрана широта " + m.pos_lat + " и долгота " + m.pos_long);

//                bigPanel.fields.get(0).value = String.valueOf(m.pos_lat);
//                bigPanel.UpdateFields();

                bigPanel.panelItems.get(0).textField.setText(String.valueOf(m.pos_lat));

//                bigPanel.invalidate();
//                bigPanel.repaint();


            }
        });



        myLeftPanel.add(testButton, "wrap");

//        myLeftPanel.add(markaPanel, "wrap");
//        myLeftPanel.add(statusPanel, "wrap");
        myLeftPanel.add(bigPanel, "wrap");
        myLeftPanel.add(mndPanel);

        myRightPanel.add(mapPanel, "grow, push");

        centerPanel.add(BorderLayout.WEST, myLeftPanel);
        centerPanel.add(BorderLayout.EAST, myRightPanel);

//        centerPanel.add(BorderLayout.WEST,markaPanel);
//        centerPanel.add(BorderLayout.WEST, statusPanel);

//        centerPanel.add(BorderLayout.CENTER,mapViewer);



        SouthPanel southPanel = new SouthPanel();
        southPanel.add(log);
        log.log("INFO","Программа запущена");


        Timer timer = new Timer(1000, new ActionListener() {

            int seconds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                seconds++;
                labelPanel.setText("Время: " + seconds + " сек");
//                log.log("Из таймера " + seconds);
            }
        });

        timer.start();



        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.getContentPane().add(BorderLayout.NORTH, labelPanel);
        frame.getContentPane().add(BorderLayout.WEST, westPanel);
        frame.getContentPane().add(BorderLayout.CENTER, centerPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, southPanel);
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
