package com.chat.myapplication.repository;

import com.chat.myapplication.api.ChatService;
import com.chat.myapplication.di.DaggerApiComponent;
import com.chat.myapplication.model.LoginResults;

import javax.inject.Inject;

import retrofit2.Call;

public class AuthRepo {

    @Inject
    ChatService chatService;

    public Call<LoginResults> loginViaPhoneNo(String phoneNo) {

        return chatService.api.loginViaPhoneNo(phoneNo);
    }

    public Call<LoginResults> verifyOTP(String otp, String phoneNo) {

        return chatService.api.verifyOTP(otp, phoneNo);
    }

    public void init() {

        DaggerApiComponent.create().inject(this);
        chatService.init();
    }
}
