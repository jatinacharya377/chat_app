package com.chat.myapplication.repository.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class SignInPreferences {

    private final SharedPreferences sharedPreferences;

    public SignInPreferences(Context context) {

        sharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public boolean isLoggedIn() {

        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    public String getAuthToken() {

        return sharedPreferences.getString("authToken", "");
    }

    public void setPreferences(boolean isLoggedIn, String authToken) {

        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply();
        sharedPreferences.edit().putString("authToken", authToken).apply();
    }
}
