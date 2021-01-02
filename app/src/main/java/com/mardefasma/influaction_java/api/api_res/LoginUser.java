package com.mardefasma.influaction_java.api.api_res;

import com.google.gson.annotations.SerializedName;
import com.mardefasma.influaction_java.api.model.User;

public class LoginUser {
    @SerializedName("user")
    private User user;

    @SerializedName("access_token")
    private String access_token;

    @SerializedName("message")
    private String message;

    public LoginUser(User user, String access_token, String message) {
        this.user = user;
        this.access_token = access_token;
        this.message = message;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "user=" + user +
                ", access_token='" + access_token + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
