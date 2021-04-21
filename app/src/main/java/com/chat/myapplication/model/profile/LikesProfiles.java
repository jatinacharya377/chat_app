package com.chat.myapplication.model.profile;

import com.google.gson.annotations.SerializedName;

public class LikesProfiles {

    @SerializedName("first_name")
    String firstName;
    @SerializedName("avatar")
    String avatarUrl;

    public String getFirstName() {

        return firstName;
    }

    public String getAvatarUrl() {

        return avatarUrl;
    }
}
