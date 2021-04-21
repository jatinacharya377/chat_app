package com.chat.myapplication.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Invites {

    @SerializedName("profiles")
    List<Profiles> profiles;

    public List<Profiles> getProfiles() {

        return profiles;
    }
}
