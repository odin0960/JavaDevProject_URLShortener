package goit.devProjectTeam2.link;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@ConfigurationProperties(prefix = "app")
public class LinkUtils {

    @Value("${app.validity_period}")
    private static long validityPeriod;

    @Value("${app.token_length}")
    private static long tokenLength;

    private LinkUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String generate() {
        List<Character> characters = new ArrayList<>();

        for (char i = 'a'; i <= 'z'; i++) {
            characters.add(i);
        }

        for (char i = 'A'; i <= 'Z'; i++) {
            characters.add(i);
        }

        for (char i = '0'; i <= '9'; i++) {
            characters.add(i);
        }

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tokenLength; i++) {
            Collections.shuffle(characters);
            result.append(characters.get(0));
        }

        return result.toString();
    }

    public static Timestamp calculateExpireDate() {
        long currentTime = System.currentTimeMillis();
        long plusHours = currentTime + (validityPeriod * 60 * 60 * 1000);
        return new Timestamp(plusHours);
    }

}
