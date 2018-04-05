package com.example.josand.dc_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by josan on 18/02/2018.
 */

public class PersonajeInfo {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("quote")
    @Expose
    private String quote;
    @SerializedName("real_name")
    @Expose
    private String realName;
    @SerializedName("alias")
    @Expose
    private String alias;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("powers")
    @Expose
    private String powers;
    @SerializedName("occupation")
    @Expose
    private String occupation;
    @SerializedName("first_appearance")
    @Expose
    private String firstAppearance;

    /**
     * No args constructor for use in serialization
     *
     */
    public PersonajeInfo() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getPowers() {
        return powers;
    }

    public void setPowers(String powers) {
        this.powers = powers;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }

}
