package com.chat.myapplication.model.profile;

import com.google.gson.annotations.SerializedName;

public class ProfileResults {

    @SerializedName("invites")
    Invites invites;
    @SerializedName("likes")
    Likes likes;

    public Invites getInvites() {

        return invites;
    }

    public Likes getLikes() {

        return likes;
    }
}
