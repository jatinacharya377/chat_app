package com.chat.myapplication.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.chat.myapplication.R;

public class PhoneNoFragment extends Fragment {

    private boolean isEmpty = false, isLongOrShort = false;
    private CardView continueCardView;
    private EditText phoneNumberEditText;
    private PhoneNoListener phoneNoListener;
    private String phoneNo = "";

    private void checkPhoneNumber() {

        if (phoneNumberEditText.getText().toString().isEmpty()) {

            isEmpty = true;

        } else {

            if (phoneNumberEditText.getText().toString().length() > 10) {

                isEmpty = false;
                isLongOrShort = true;

            } else if (phoneNumberEditText.getText().toString().length() < 10) {

                isEmpty = false;
                isLongOrShort = true;

            } else {

                isEmpty = false;
                isLongOrShort = false;
                phoneNo = "+91" + phoneNumberEditText.getText().toString();

            }

        }

    }

    private void initializeViews(View view) {

        continueCardView = view.findViewById(R.id.continueCardView);
        phoneNumberEditText = view.findViewById(R.id.phoneNumberEditText);
    }

    public interface PhoneNoListener {

        void sendOTP(boolean isEmpty, boolean isLongOrShort, String phoneNo);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_phone_no, container, false);
        initializeViews(view);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {

            phoneNoListener = (PhoneNoListener) context;

        } catch (Exception e) {

            throw new ClassCastException(context.toString() + " must implement PhoneNoListener!");

        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        phoneNoListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        continueCardView.setOnClickListener(v -> {

            checkPhoneNumber();
            phoneNoListener.sendOTP(isEmpty, isLongOrShort, phoneNo);
        });
    }
}
