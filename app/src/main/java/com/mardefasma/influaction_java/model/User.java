package com.mardefasma.influaction_java.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("i_ig")
    private String i_ig;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", i_ig='" + i_ig + '\'' +
                '}';
    }

    public User(Integer id, String name, String email, String i_ig) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.i_ig = i_ig;
    }

    public String getI_ig() {
        return i_ig;
    }

    public void setI_ig(String i_ig) {
        this.i_ig = i_ig;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
