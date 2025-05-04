package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DatabaseListPanel extends JPanel {
    private DefaultListModel<String> listModel;
    private JList<String> jList;
    private JButton upButton, downButton;
    private DatabaseListListener listener; // Новый код!

    public DatabaseListPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(350,700));
        listModel = new DefaultListModel<>();
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

    private void loadDataFromDatabase() {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/eradb", "erauser", "erapassword");
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM msd");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String element = rs.getString("l_id");
                element = element + " " + rs.getString("imei");
                listModel.addElement(element);
            }

        } catch (SQLException e) {
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
}
