package goit.devProjectTeam2.link;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LinkUtils {

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

        for (int i = 0; i < 7; i++) {
            Collections.shuffle(characters);
            result.append(characters.get(0));
        }

        return result.toString();
    }

    public static Timestamp calculateExpireDate() {
        long currentTime = System.currentTimeMillis();
        long plus72Hours = currentTime + (72 * 60 * 60 * 1000);
        return new Timestamp(plus72Hours);
    }

}
