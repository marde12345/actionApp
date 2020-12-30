package com.mardefasma.influaction_java.model;

import java.util.List;

public class Influencer {
    Integer influencerId;
    String influencerName;
    String influencerPrice;
    String influencerLocation;
    String influencerImageUrl;
    List<IconInfluencer> influencerIcons;

    public List<IconInfluencer> getInfluencerIcons() {
        return influencerIcons;
    }

    public void setInfluencerIcons(List<IconInfluencer> influencerIcons) {
        this.influencerIcons = influencerIcons;
    }

    public Influencer(Integer influencerId, String influencerName, String influencerPrice, String influencerLocation, String influencerImageUrl, List<IconInfluencer> influencerIcons) {
        this.influencerId = influencerId;
        this.influencerName = influencerName;
        this.influencerPrice = influencerPrice;
        this.influencerLocation = influencerLocation;
        this.influencerImageUrl = influencerImageUrl;
        this.influencerIcons = influencerIcons;
    }

    public Integer getInfluencerId() {
        return influencerId;
    }

    public void setInfluencerId(Integer influencerId) {
        this.influencerId = influencerId;
    }

    public String getInfluencerName() {
        return influencerName;
    }

    public void setInfluencerName(String influencerName) {
        this.influencerName = influencerName;
    }

    public String getInfluencerPrice() {
        return influencerPrice;
    }

    public void setInfluencerPrice(String influencerPrice) {
        this.influencerPrice = influencerPrice;
    }

    public String getInfluencerLocation() {
        return influencerLocation;
    }

    public void setInfluencerLocation(String influencerLocation) {
        this.influencerLocation = influencerLocation;
    }

    public String getInfluencerImageUrl() {
        return influencerImageUrl;
    }

    public void setInfluencerImageUrl(String influencerImageUrl) {
        this.influencerImageUrl = influencerImageUrl;
    }
}
