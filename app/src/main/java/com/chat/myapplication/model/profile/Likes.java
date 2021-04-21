package com.chat.myapplication.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Likes {

    @SerializedName("profiles")
    List<LikesProfiles> profilesList;
    @SerializedName("can_see_profile")
    boolean isProfileVisible;
    @SerializedName("likes_received_count")
    int totalLikes;

    public List<LikesProfiles> getProfilesList() {

        return profilesList;
    }

    public boolean isProfileVisible() {

        return isProfileVisible;
    }

    public int getTotalLikes() {

        return totalLikes;
    }
}
