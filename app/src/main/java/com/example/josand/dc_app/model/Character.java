package com.example.josand.dc_app.model;

/**
 * Created by josan on 9/02/2018.
 */

public class Character {
    private String name;
    private String quote;
    private String realName;
    private String alias;
    private String base;
    private String powers;
    private String occupation;
    private String firstAppareance;
    private String urlImage;
    private String urlProfile;

    public Character(String name, String urlImage) {
        this.name = name;
        this.urlImage = urlImage;
    }

    public Character(String name, String alias, String urlImage) {
        this.name = name;
        this.alias = alias;
        this.urlImage = urlImage;
    }

    public Character(String name, String quote, String realName, String alias, String base,
                     String powers, String occupation, String firstAppareance, String urlProfile) {
        this.name = name;
        this.quote = quote;
        this.realName = realName;
        this.alias = alias;
        this.base = base;
        this.powers = powers;
        this.occupation = occupation;
        this.firstAppareance = firstAppareance;
        this.urlProfile = urlProfile;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getQuote() {
        return quote;
    }

    public String getRealName() {
        return realName;
    }

    public String getBase() {
        return base;
    }

    public String getPowers() {
        return powers;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getFirstAppareance() {
        return firstAppareance;
    }

    public String getUrlProfile() {
        return urlProfile;
    }
}
