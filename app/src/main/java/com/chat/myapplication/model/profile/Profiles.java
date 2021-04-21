package com.chat.myapplication.model.profile;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Profiles {

    @SerializedName("general_information")
    GeneralInformations generalInformations;
    @SerializedName("photos")
    List<Photos> photosList;

    public GeneralInformations getGeneralInformations() {

        return generalInformations;
    }

    public List<Photos> getPhotosList() {

        return photosList;
    }
}
