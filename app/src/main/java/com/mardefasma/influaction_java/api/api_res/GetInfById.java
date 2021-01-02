package com.mardefasma.influaction_java.api.api_res;

import com.google.gson.annotations.SerializedName;
import com.mardefasma.influaction_java.api.model.Influencer;

public class GetInfById {
    @SerializedName("data")
    private Influencer influencer;

    public GetInfById(Influencer influencer) {
        this.influencer = influencer;
    }

    @Override
    public String toString() {
        return "GetInfById{" +
                "influencer=" + influencer +
                '}';
    }

    public Influencer getInfluencer() {
        return influencer;
    }

    public void setInfluencer(Influencer influencer) {
        this.influencer = influencer;
    }
}
