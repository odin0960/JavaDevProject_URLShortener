package goit.devProjectTeam2.link;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkValidator {
    public static boolean linkValid(String link) {
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            connection.disconnect();
            return responseCode == 200;
        } catch (IOException e) {
            return false;
        }
    }
    /*
    public static void checkLink(LinkValidatorEntity urlToCheck) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<LinkValidatorEntity>> violations = validator.validate(urlToCheck);

        if (violations.isEmpty()) {
            System.out.println("Link is valid");
        } else {
            for (ConstraintViolation<LinkValidatorEntity> violation : violations) {
                System.err.println("Validation error: " + violation.getMessage());
            }
        }
    }
     */



}