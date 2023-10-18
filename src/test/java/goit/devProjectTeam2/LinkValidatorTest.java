package goit.devProjectTeam2;


import goit.devProjectTeam2.link.LinkValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LinkValidatorTest {
    @Test
    public void testLinkValidWithValidLink() {
        String validLink = "https://www.youtube.com";
        boolean isValid = LinkValidator.linkValid(validLink);
        assertTrue(isValid);
    }

    @Test
    public void testLinkValidWithInvalidLink() {
        String invalidLink = "https://www..com/nonexistent";
        boolean isValid = LinkValidator.linkValid(invalidLink);
        assertFalse(isValid);
    }
}
