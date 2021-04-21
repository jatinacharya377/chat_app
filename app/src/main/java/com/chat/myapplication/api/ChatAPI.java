package com.chat.myapplication.api;

import com.chat.myapplication.model.LoginResults;
import com.chat.myapplication.model.profile.ProfileResults;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ChatAPI {

    @FormUrlEncoded
    @POST("users/phone_number_login")
    Call<LoginResults> loginViaPhoneNo(
            @Field("number") String phoneNo
    );

    @FormUrlEncoded
    @POST("users/verify_otp")
    Call<LoginResults> verifyOTP(
            @Field("otp") String otp,
            @Field("number") String phoneNo
    );

    @GET("users/test_profile_list")
    Call<ProfileResults> getProfile();
}
