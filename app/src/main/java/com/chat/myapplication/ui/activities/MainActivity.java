package com.chat.myapplication.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.chat.myapplication.R;
import com.chat.myapplication.model.LoginResults;
import com.chat.myapplication.repository.sharedpreferences.SignInPreferences;
import com.chat.myapplication.ui.fragments.OTPFragment;
import com.chat.myapplication.ui.fragments.PhoneNoFragment;
import com.chat.myapplication.viewmodel.AuthViewModel;

public class MainActivity extends AppCompatActivity implements OTPFragment.OTPListener, PhoneNoFragment.PhoneNoListener {

    private SignInPreferences signInPreferences;
    private AuthViewModel authViewModel;
    private FrameLayout fragmentContainer;
    private OTPFragment otpFragment;
    private PhoneNoFragment phoneNoFragment;

    private boolean checkInternetConnection() {

        boolean isConnected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {

            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.isConnected()) {

                if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {

                    isConnected = true;

                } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

                    isConnected = true;

                }

            }

        }

        return isConnected;
    }

    private void initializeViews() {

        signInPreferences = new SignInPreferences(this);
        authViewModel = ViewModelProviders.of(this).get(AuthViewModel.class);
        authViewModel.init();
        fragmentContainer = findViewById(R.id.fragmentContainer);
        phoneNoFragment = new PhoneNoFragment();
    }

    private void loadFragment() {

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit)
                .replace(R.id.fragmentContainer, phoneNoFragment)
                .commit();
    }

    private void loadFragmentWithSlideLeftCustomAnimation() {

        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit)
                .replace(R.id.fragmentContainer, otpFragment)
                .commit();
    }

    private void showToast(String message) {

        Toast.makeText(
                this,
                message,
                Toast.LENGTH_SHORT
        ).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (fragmentContainer != null) {

            if (signInPreferences.isLoggedIn()) {

                startActivity(new Intent(this, HomeScreenActivity.class));
                finish();

            } else {

                loadFragment();

            }

        }

    }

    @Override
    public void goToHomeScreen(boolean isEmpty, String phoneNo, String secondsLeft) {

        if (isEmpty) {

            showToast("Please, enter OTP to verify your phone number!");

        } else {

            if (!secondsLeft.equals("00:00")) {

                String otp = otpFragment.getOTP();
                authViewModel.VerifyOTP(otp, phoneNo).observe(this, loginResultsResponse -> {

                    if (loginResultsResponse.isSuccessful()) {

                        LoginResults loginResults = loginResultsResponse.body();

                        if (loginResults != null) {

                            signInPreferences.setPreferences(true, loginResults.getAuthToken());
                            otpFragment.cancelTheTimer();
                            startActivity(new Intent(this, HomeScreenActivity.class));
                            finish();

                        }

                    } else {

                        showToast("Something went wrong. Please, try again later!");

                    }

                });

            } else {

                showToast("You did not submit the OTP in time!");

            }

        }

    }

    @Override
    public void sendOTP(boolean isEmpty, boolean isLongOrShort, String phoneNo) {

        if (isEmpty) {

            showToast("Please, enter your phone number!");

        } else {

            if (isLongOrShort) {

                showToast("Please, enter a valid phone number!");

            } else {

                if (checkInternetConnection()) {

                    authViewModel.loginViaPhoneNo(phoneNo).observe(this, loginResultsResponse -> {

                        if (loginResultsResponse.isSuccessful()) {

                            otpFragment = new OTPFragment(phoneNo);
                            loadFragmentWithSlideLeftCustomAnimation();

                        } else {

                            showToast("Something went wrong. Please, try again later!");

                        }

                    });

                } else {

                    showToast("Please, make sure you are connected to internet!");

                }

            }

        }

    }
}