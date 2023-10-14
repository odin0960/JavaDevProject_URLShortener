package goit.devProjectTeam2;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
//@ConfigurationProperties(prefix = "app")
public class ProjectConstant {

    private ProjectConstant() {
    }

    public static final int CHARACTER_LENGTHS = 7;
    public static final long HOURS_TO_CALCULATE_EXPIRE_DATE = 72;

}
