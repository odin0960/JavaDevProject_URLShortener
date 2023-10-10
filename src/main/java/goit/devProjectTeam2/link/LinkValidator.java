package goit.devProjectTeam2.link;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkValidator {
    public static void checkURL() {
        String urlToCheck = "https://example.com";

        try {
            // Створюємо об'єкт URL на основі рядка URL
            URL url = new URL(urlToCheck);

            // Відкриваємо з'єднання з сервером
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Встановлюємо метод запиту на GET
            connection.setRequestMethod("GET");

            // Отримуємо код статусу відповіді
            int statusCode = connection.getResponseCode();

            // Перевіряємо, чи код статусу рівний 200
            if (statusCode == 200) {
                System.out.println("Посилання " + urlToCheck + " повертає код статусу 200 (OK).");
            } else {
                System.out.println("Посилання " + urlToCheck + " повертає код статусу " + statusCode);
            }

            // Закриваємо з'єднання
            connection.disconnect();
        } catch (IOException e) {
            System.err.println("Виникла помилка: " + e.getMessage());
        }
    }
}