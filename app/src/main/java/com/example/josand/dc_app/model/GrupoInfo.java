package com.example.josand.dc_app.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by josan on 18/02/2018.
 */

public class GrupoInfo {
    @SerializedName("members")
    @Expose
    private List<String> members = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("profile")
    @Expose
    private String profile;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("leader")
    @Expose
    private String leader;
    @SerializedName("first_appearance")
    @Expose
    private String firstAppearance;

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
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

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public void setFirstAppearance(String firstAppearance) {
        this.firstAppearance = firstAppearance;
    }
}
