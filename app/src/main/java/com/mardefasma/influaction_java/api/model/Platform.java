package com.mardefasma.influaction_java.api.model;

import com.google.gson.annotations.SerializedName;

public class Platform {
    @SerializedName("id")
    private Integer id;
    @SerializedName("platform")
    private String platform;
    @SerializedName("url")
    private String url;
    @SerializedName("price")
    private Double price;
    @SerializedName("per_days")
    private Integer per_days;
    @SerializedName("follower")
    private Integer follower;
    @SerializedName("user_id")
    private Integer user_id;

    public Platform(Integer id, String platform, String url, Double price, Integer per_days, Integer follower, Integer user_id) {
        this.id = id;
        this.platform = platform;
        this.url = url;
        this.price = price;
        this.per_days = per_days;
        this.follower = follower;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Platform{" +
                "id=" + id +
                ", platform='" + platform + '\'' +
                ", url='" + url + '\'' +
                ", price=" + price +
                ", per_days=" + per_days +
                ", follower=" + follower +
                ", user_id=" + user_id +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getPer_days() {
        return per_days;
    }

    public void setPer_days(Integer per_days) {
        this.per_days = per_days;
    }

    public Integer getFollower() {
        return follower;
    }

    public void setFollower(Integer follower) {
        this.follower = follower;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}
