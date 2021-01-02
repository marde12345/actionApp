package com.mardefasma.influaction_java.api.api_res;

import com.google.gson.annotations.SerializedName;
import com.mardefasma.influaction_java.api.model.Influencer;

import java.util.List;

public class GetInf {
    @SerializedName("data")
    private List<Influencer> influencerList;

    public GetInf(List<Influencer> influencerList) {
        this.influencerList = influencerList;
    }

    public List<Influencer> getInfluencerList() {
        return influencerList;
    }

    public void setInfluencerList(List<Influencer> influencerList) {
        this.influencerList = influencerList;
    }

    @Override
    public String toString() {
        return "GetInf{" +
                "influencerList=" + influencerList +
                '}';
    }
}
