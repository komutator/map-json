package com.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class OpenSkyLiveTracker {
    // Координаты центра города (Москва)
    private static final double CITY_LAT = 55.7558;
    private static final double CITY_LON = 37.6173;
    private static final double RADIUS_KM = 150.0; // Радиус поиска в километрах

    private static final String API_URL = "https://opensky-network.org/api/states/all";
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) {
        while (true) {
            try {
                JsonNode root = fetchData();
                if (root == null || !root.has("states")) {
                    System.out.println("⚠ Ошибка: API вернул пустой ответ.");
                    Thread.sleep(30000); // Ждем 30 секунд перед повторным запросом
                    continue;
                }

                System.out.println("\n🔹 Live Tracking for Moscow:");
                for (JsonNode state : root.get("states")) {
                    String icao24 = state.get(0).asText();
                    String callsign = state.get(1).asText().trim();
                    double lon = state.get(5).asDouble();
                    double lat = state.get(6).asDouble();
                    double altitude = state.get(13).asDouble();
                    double velocity = state.get(9).asDouble();

                    if (isWithinRadius(lat, lon, CITY_LAT, CITY_LON, RADIUS_KM)) {
                        System.out.printf("✈ Позывной: %s | ICAO: %s | Высота: %.0f м | Скорость: %.0f м/с%n",
                                callsign.isEmpty() ? "N/A" : callsign, icao24, altitude, velocity);
                    }
                }

                Thread.sleep(10000); // Обновляем данные каждые 10 секунд

            } catch (Exception e) {
                System.err.println("⚠ Ошибка при получении данных: " + e.getMessage());
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    /**
     * Отправляет GET-запрос в OpenSky API и возвращает JSON-ответ.
     */
    private static JsonNode fetchData() throws IOException {
        Request request = new Request.Builder().url(API_URL).build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                System.err.println("⚠ Ошибка API: " + response.message());
                return null;
            }
            return mapper.readTree(response.body().string());
        }
    }

    /**
     * Проверяет, находится ли самолет в пределах заданного радиуса от города.
     */
    private static boolean isWithinRadius(double lat1, double lon1, double lat2, double lon2, double radiusKm) {
        final int R = 6371; // Радиус Земли в километрах
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c <= radiusKm;
    }
}
