package com.chat.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class LoginResults {

    @SerializedName("token")
    String authToken;
    @SerializedName("otp")
    String otp;
    @SerializedName("number")
    String phoneNumber;

    public String getAuthToken() {

        return authToken;
    }
}
