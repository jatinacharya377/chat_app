package com.chat.myapplication.model.profile;

import com.google.gson.annotations.SerializedName;

public class Photos {

    @SerializedName("photo")
    String photoUrl;
    @SerializedName("selected")
    boolean selected;
    @SerializedName("status")
    String status;

    public String getPhotoUrl() {

        return photoUrl;
    }

    public boolean isSelected() {

        return selected;
    }

    public String getStatus() {

        return status;
    }
}
