package goit.devProjectTeam2.entity;

import org.hibernate.validator.constraints.URL;

public class LinkValidatorEntity {
    @URL
    private String longLink;
    public void setLongLink(String longLink) {
        this.longLink = longLink;
    }

}