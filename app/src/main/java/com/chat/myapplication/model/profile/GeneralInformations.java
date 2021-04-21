package com.chat.myapplication.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneralInformations {

    @SerializedName("first_name")
    String firstName;
    @SerializedName("age")
    String age;

    public String getFirstName() {

        return firstName;
    }

    public String getAge() {

        return age;
    }
}
