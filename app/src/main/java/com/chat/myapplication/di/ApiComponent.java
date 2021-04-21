package com.chat.myapplication.di;

import com.chat.myapplication.api.ChatService;
import com.chat.myapplication.repository.AuthRepo;
import com.chat.myapplication.repository.ProfileRepo;
import com.chat.myapplication.viewmodel.AuthViewModel;
import com.chat.myapplication.viewmodel.ProfileViewModel;

import dagger.Component;

@Component(modules = ApiModule.class)
public interface ApiComponent {

    void inject(AuthRepo authRepo);
    void inject(AuthViewModel authViewModel);
    void inject(ChatService chatService);
    void inject(ProfileRepo profileRepo);
    void inject(ProfileViewModel profileViewModel);
}
