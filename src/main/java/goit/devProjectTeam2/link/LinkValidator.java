package goit.devProjectTeam2.link;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkValidator {
    public static void checkLink() {
        String urlToCheck = "";
        try {
            URL url = new URL(urlToCheck);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int statusCode = connection.getResponseCode();
            if (statusCode == 200) {
                System.out.println("Посилання " + urlToCheck + " повертає код статусу 200 (OK).");
            } else {
                System.out.println("Посилання " + urlToCheck + " повертає код статусу " + statusCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            System.err.println("Виникла помилка: " + e.getMessage());
        }
    }
}