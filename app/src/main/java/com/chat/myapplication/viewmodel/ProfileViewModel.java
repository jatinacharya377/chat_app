package com.chat.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chat.myapplication.di.DaggerApiComponent;
import com.chat.myapplication.model.profile.ProfileResults;
import com.chat.myapplication.repository.ProfileRepo;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {

    @Inject
    ProfileRepo profileRepo;

    public LiveData<Response<ProfileResults>> getProfile() {

        MutableLiveData<Response<ProfileResults>> profileResults = new MutableLiveData<>();
        profileRepo.getProfile().enqueue(new Callback<ProfileResults>() {
            @Override
            public void onResponse(@NotNull Call<ProfileResults> call, @NotNull Response<ProfileResults> response) {

                profileResults.setValue(response);
            }

            @Override
            public void onFailure(@NotNull Call<ProfileResults> call, @NotNull Throwable t) {

                Log.e("get_profile_error", t.getMessage());
            }
        });

        return profileResults;
    }

    public void init(String authToken) {

        DaggerApiComponent.create().inject(this);
        profileRepo.init(authToken);
    }
}
