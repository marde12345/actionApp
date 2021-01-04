package com.mardefasma.influaction_java.api.model;

import com.google.gson.annotations.SerializedName;
import com.mardefasma.influaction_java.R;

public class User {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("role")
    private String role;
    @SerializedName("photo_profile")
    private String photo_profile;
    @SerializedName("location")
    private String location;
    @SerializedName("wa")
    private String wa;
    @SerializedName("deskripsi")
    private String deskripsi;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", photo_profile='" + photo_profile + '\'' +
                ", location='" + location + '\'' +
                ", wa='" + wa + '\'' +
                ", deskripsi='" + deskripsi + '\'' +
                '}';
    }

    public User(Integer id, String name, String email, String role, String photo_profile, String location, String wa, String deskripsi) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.photo_profile = photo_profile;
        this.location = location;
        this.wa = wa;
        this.deskripsi = deskripsi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getWa() {
        return wa;
    }

    public void setWa(String wa) {
        this.wa = wa;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhoto_profile() {
        return photo_profile;
    }

    public void setPhoto_profile(String photo_profile) {
        if (photo_profile.contains("http")){
            this.photo_profile = photo_profile;
        } else{
            this.photo_profile = R.string.base_url + "/images/" + photo_profile;
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
