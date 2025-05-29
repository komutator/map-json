package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class ReverseGeocoder {
    private static final String USER_AGENT = "MyReverseGeocoder/1.0";

    public static String getNearestCity(double lat, double lon) throws Exception {
        String urlStr = "https://nominatim.openstreetmap.org/reverse?format=json"
                + "&lat=" + lat
                + "&lon=" + lon
                + "&zoom=10&addressdetails=1";

        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", USER_AGENT);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = in.readLine()) != null) {
            response.append(line);
        }
        in.close();

        JSONObject json = new JSONObject(response.toString());
        JSONObject address = json.getJSONObject("address");

        // Пробуем получить город или другой населённый пункт
        return address.optString("municipality",
                address.optString("county",
                        address.optString("state",
                                address.optString("region", "Не найдено"))));
    }
}
