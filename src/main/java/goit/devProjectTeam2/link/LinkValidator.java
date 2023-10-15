package goit.devProjectTeam2.link;

import goit.devProjectTeam2.entity.LinkValidatorEntity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;

public class LinkValidator {
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
}