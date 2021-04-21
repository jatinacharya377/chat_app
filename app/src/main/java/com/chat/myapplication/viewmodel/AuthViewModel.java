package com.chat.myapplication.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.chat.myapplication.di.DaggerApiComponent;
import com.chat.myapplication.model.LoginResults;
import com.chat.myapplication.repository.AuthRepo;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthViewModel extends ViewModel {

    @Inject
    AuthRepo authRepo;

    public LiveData<Response<LoginResults>> loginViaPhoneNo(String phoneNo) {

        MutableLiveData<Response<LoginResults>> loginResults = new MutableLiveData<>();
        authRepo.loginViaPhoneNo(phoneNo).enqueue(new Callback<LoginResults>() {
            @Override
            public void onResponse(@NotNull Call<LoginResults> call, @NotNull  Response<LoginResults> response) {

                loginResults.setValue(response);
            }

            @Override
            public void onFailure(@NotNull Call<LoginResults> call, @NotNull Throwable t) {

                Log.e("login_error", t.getMessage());
            }
        });

        return loginResults;
    }

    public LiveData<Response<LoginResults>> VerifyOTP(String otp, String phoneNo) {

        MutableLiveData<Response<LoginResults>> otpVerResults = new MutableLiveData<>();
        authRepo.verifyOTP(otp, phoneNo).enqueue(new Callback<LoginResults>() {
            @Override
            public void onResponse(@NotNull Call<LoginResults> call, @NotNull  Response<LoginResults> response) {

                otpVerResults.setValue(response);
            }

            @Override
            public void onFailure(@NotNull Call<LoginResults> call, @NotNull Throwable t) {

                Log.e("verification_error", t.getMessage());
            }
        });

        return otpVerResults;
    }

    public void init() {

        DaggerApiComponent.create().inject(this);
        authRepo.init();
    }
}
