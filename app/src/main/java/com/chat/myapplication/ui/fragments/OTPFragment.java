package com.chat.myapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.chat.myapplication.R;

public class OTPFragment  extends Fragment {

    private boolean isEmpty = false;
    private CardView continueCardView;
    private CountDownTimer countDownTimer;
    private EditText otpEditText;
    private OTPListener otpListener;
    private final String phoneNo;
    private TextView phoneNumberTextView, timerTextView;

    public OTPFragment(String phoneNo) {

        this.phoneNo = phoneNo;
    }

    private String getPhoneNo() {

        return phoneNumberTextView.getText().toString();
    }

    private String getSecondsLeft() {

        return timerTextView.getText().toString();
    }

    private void checkOTP() {

        isEmpty = otpEditText.getText().toString().isEmpty();
    }

    private void initializeViews(View view) {

        continueCardView = view.findViewById(R.id.continueCardView);
        otpEditText = view.findViewById(R.id.otpEditText);
        phoneNumberTextView = view.findViewById(R.id.phoneNumberTextView);
        timerTextView = view.findViewById(R.id.timerTextView);
    }

    public interface OTPListener {

        void goToHomeScreen(boolean isEmpty, String phoneNo, String secondsLeft);
    }

    public String getOTP() {

        return otpEditText.getText().toString();
    }

    public void cancelTheTimer() {

        countDownTimer.cancel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_otp, container, false);
        initializeViews(view);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {

            otpListener = (OTPListener) context;

        } catch (Exception e) {

            throw new ClassCastException(context.toString() + " must implement OTPListener!");

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        otpListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

                int secondsLeftInInt = (int) (millisUntilFinished / 1000);
                String secondsLeft;

                if (secondsLeftInInt >= 0 && secondsLeftInInt < 10) {

                    secondsLeft = "00 : 0" + secondsLeftInInt;

                } else {

                    secondsLeft = "00 : " + secondsLeftInInt;

                }

                timerTextView.setText(secondsLeft);
            }

            public void onFinish() {

                timerTextView.setText(requireContext().getResources().getText(R.string.timer_end));
            }
        };
        countDownTimer.start();
        phoneNumberTextView.setText(phoneNo);
        continueCardView.setOnClickListener(v -> {

            checkOTP();
            otpListener.goToHomeScreen(isEmpty, getPhoneNo(), getSecondsLeft());
        });
    }
}
