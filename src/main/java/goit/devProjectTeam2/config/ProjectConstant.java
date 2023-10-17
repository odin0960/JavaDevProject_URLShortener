package goit.devProjectTeam2.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public class ProjectConstant { //вже не потрібен???
    private ProjectConstant() {
    }
    public static final int TOKEN_LENGTH = 6;
    public static final long VALIDITY_PERIOD_HOURS = 72;

    public static long CACHE_PERIOD_MINUTES = 15;
    public static long CACHE_SIZE = 10000;

}
