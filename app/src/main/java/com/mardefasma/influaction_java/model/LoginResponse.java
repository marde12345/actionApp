package com.mardefasma.influaction_java.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("user")
    private User user;

    @SerializedName("access_token")
    private String access_token;

    public LoginResponse(User user, String access_token) {
        this.user = user;
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "LoginResponse{" +
                "user=" + user +
                ", access_token='" + access_token + '\'' +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }
}
