package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.db.DatabaseManager;

public class DatabaseListPanel extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<String> jList;
    private JButton upButton, downButton;
    private DatabaseListListener listener; // Сообщает наверх о перемещениях по списку!
    private DatabaseManager databaseManager;
    private double longitude;
    private double latitude;
    // todo: сделать чтобы модель считывалась в список:
    private List<String[]> databaseValues;
    public ArrayList<MsdObject> msdObject;


    public DatabaseListPanel(DatabaseManager db) {
        databaseManager = db;
        msdObject = new ArrayList<>();
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350,700));
        listModel = new DefaultListModel<>();
        databaseValues = new ArrayList<>();
        jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);

        JPanel buttonPanel = new JPanel();
        upButton = new JButton("Вверх");
        downButton = new JButton("Вниз");



        buttonPanel.add(upButton);
        buttonPanel.add(downButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadDataFromDatabase();
        setupButtonListeners();
        setupSelectionListener(); // Новый код!
    }

    //todo: Задействовать DatabaseManager
    private void loadDataFromDatabase() {
//      SELECT `l_id`, `case_id`, `imsi`, `imei`, `msisdn`, `channel`, `msd_from`, `msd_to`, `is_valid`, `version`, `has_egts`, `pos_lat`, `pos_long`, `hex_raw`, `msd_len`, `msd_decoded`, `json`, `tm`, `ecs`, `err_text`, `err_len`, `rssi`
//      SELECT  0           1        2       3        4         5          6           7         8           9          10          11         12         13         14           15           16     17    18      19          20         21

        try {

            List<String[]> result = new ArrayList<>();
            result = databaseManager.executeQuery("SELECT * FROM msd");

            for(String[] s : result){
                listModel.addElement(s[0] + "  " + s[17] + " " + s[5] );
                databaseValues.add(s);

                MsdObject msd = new MsdObject();
                msd.l_id = Integer.parseInt(s[0]);
                msd.case_id = Integer.parseInt(s[1]);
                msd.imsi = s[2];
                msd.imei = s[3];
                msd.msisdn = s[4];
                msd.channel = s[5];
                msd.msd_from = s[6];
                msd.msd_to = s[7];
                msd.is_valid = Integer.parseInt(s[8]);
                msd.version = Integer.parseInt(s[9]);
                msd.has_egts = Integer.parseInt(s[10]);
                msd.pos_lat = Long.parseLong(s[11]);
                msd.pos_long = Long.parseLong(s[12]);
                msd.hex_raw = s[13];
                msd.msd_len = Integer.parseInt(s[14]);
                msd.msd_decoded = s[15];
                msd.json = s[16];

                String dateTimeString = s[17];
                Timestamp timestamp = Timestamp.valueOf(dateTimeString);
                msd.tm = timestamp;
                msd.ecs = s[18];
                msd.err_text = s[19];
                msd.err_len = Integer.parseInt(s[20]);
                msd.rssi = s[21];

                msdObject.add(msd);



            }
            System.out.println("Добавлено " + databaseValues.size() + " элементов в databaseValues");
        }

        catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ошибка загрузки данных из базы", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setupButtonListeners() {
        upButton.addActionListener(e -> moveSelection(-1));
        downButton.addActionListener(e -> moveSelection(1));
    }

    private void moveSelection(int delta) {
        int index = jList.getSelectedIndex();
        int newIndex = index + delta;
        if (newIndex >= 0 && newIndex < listModel.size()) {
            jList.setSelectedIndex(newIndex);
            jList.ensureIndexIsVisible(newIndex);
            System.out.println("Выбран " + newIndex + "элемент списка");
        }
    }

    private void setupSelectionListener() {
        jList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && listener != null) {
                String selected = jList.getSelectedValue();
                listener.onItemSelected(selected); // Сообщаем слушателю!
            }
        });
    }

    // Метод для установки слушателя
    public void setDatabaseListListener(DatabaseListListener listener) {
        this.listener = listener;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getCurrentSelectedIndex() {
        return jList.getSelectedIndex();
    }

    public String[] getCurrentSelectedValues(){
        int sel;
        sel = jList.getSelectedIndex();
        String[] zz = databaseValues.get(sel);
        return zz;
    }


}
