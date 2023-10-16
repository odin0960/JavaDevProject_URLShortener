package goit.devProjectTeam2.config;

import lombok.Getter;

@Getter
public class ProjectConstant { //вже не потрібен???
    private ProjectConstant() {
    }
    public static final int CHARACTER_LENGTHS = 7;
    public static final long HOURS_TO_CALCULATE_EXPIRE_DATE = 72;
}
