package com.mardefasma.influaction_java.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Influencer {
    @SerializedName("user")
    User user;
    @SerializedName("platforms")
    List<Platform> platformList;

    public Influencer(User user, List<Platform> platformList) {
        this.user = user;
        this.platformList = platformList;
    }

    @Override
    public String toString() {
        return "Influencer{" +
                "user=" + user +
                ", platformList=" + platformList +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Platform> getPlatformList() {
        return platformList;
    }

    public void setPlatformList(List<Platform> platformList) {
        this.platformList = platformList;
    }
}
