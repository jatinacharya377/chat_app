package com.chat.myapplication.repository;

import com.chat.myapplication.api.ChatAPI;
import com.chat.myapplication.api.ChatService;
import com.chat.myapplication.di.DaggerApiComponent;
import com.chat.myapplication.model.profile.ProfileResults;

import javax.inject.Inject;

import retrofit2.Call;

public class ProfileRepo {

    @Inject
    ChatService chatService;
    private ChatAPI api;

    public Call<ProfileResults> getProfile() {

        return api.getProfile();
    }

    public void init(String authToken) {

        DaggerApiComponent.create().inject(this);
        api = chatService.createChatAPI(authToken);
    }
}
