package com.example.ConfigManager;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    private final String configFilePath;
    private final Properties properties;

    public ConfigManager(String configFilePath) {
        this.configFilePath = configFilePath;
        this.properties = new Properties();
    }

    public void loadOrCreateDefault() {
        File configFile = new File(configFilePath);

        if (configFile.exists()) {
            try (FileInputStream fis = new FileInputStream(configFile)) {
                properties.load(fis);
                System.out.println("Конфигурация загружена из файла.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Файл не найден. Создание файла с настройками по умолчанию...");
            // значения по умолчанию
            properties.setProperty("connectionString", "");
            properties.setProperty("myid", "1234");
            save();
        }
    }

    public String get(String key) {
        return properties.getProperty(key);
    }

    public void set(String key, String value) {
        properties.setProperty(key, value);
    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream(configFilePath)) {
            properties.store(fos, "Configuration file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
