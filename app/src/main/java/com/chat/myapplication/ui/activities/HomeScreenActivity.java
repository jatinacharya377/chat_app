package com.chat.myapplication.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chat.myapplication.R;
import com.chat.myapplication.model.profile.GeneralInformations;
import com.chat.myapplication.model.profile.LikesProfiles;
import com.chat.myapplication.model.profile.Photos;
import com.chat.myapplication.model.profile.ProfileResults;
import com.chat.myapplication.model.profile.Profiles;
import com.chat.myapplication.repository.sharedpreferences.SignInPreferences;
import com.chat.myapplication.ui.adapters.ProfilesAdapter;
import com.chat.myapplication.viewmodel.ProfileViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeScreenActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageView userProfileImageView;
    private List<LikesProfiles> profilesList = new ArrayList<>();
    private NestedScrollView nestedScrollView;
    private ProfilesAdapter profilesAdapter;
    private ProfileViewModel profileViewModel;
    private ProgressBar loadingProgressBar;
    private TextView firstNameTextView;

    private void initializeViews() {

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getOrCreateBadge(R.id.notes).setNumber(9);
        bottomNavigationView.getOrCreateBadge(R.id.matches).setNumber(50);
        firstNameTextView = findViewById(R.id.firstNameTextView);
        loadingProgressBar = findViewById(R.id.loadingProgressBar);
        nestedScrollView = findViewById(R.id.nestedScrollView);
        profilesAdapter = new ProfilesAdapter(this, profilesList);
        SignInPreferences signInPreferences = new SignInPreferences(this);
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        profileViewModel.init(signInPreferences.getAuthToken());
        RecyclerView profileRecyclerView = findViewById(R.id.profileRecyclerView);
        profileRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        profileRecyclerView.setHasFixedSize(true);
        profileRecyclerView.setAdapter(profilesAdapter);
        userProfileImageView = findViewById(R.id.userProfileImageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        initializeViews();
    }

    @Override
    protected void onResume() {
        super.onResume();

        profileViewModel.getProfile().observe(this, profileResultsResponse -> {

            if (profileResultsResponse.isSuccessful()) {

                bottomNavigationView.setVisibility(View.VISIBLE);
                loadingProgressBar.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);
                ProfileResults profileResults = profileResultsResponse.body();

                if (profileResults != null && profileResults.getInvites() != null && profileResults.getLikes() != null) {

                    profilesList = profileResults.getLikes().getProfilesList();
                    List<Profiles> profiles = profileResults.getInvites().getProfiles();

                    if (profilesList.size() > 0) {

                        profilesAdapter.updateTheAdapter(profileResults.getLikes().isProfileVisible(), profilesList);

                    }

                    if (profiles != null) {

                        List<Photos> photosList = profiles.get(0).getPhotosList();
                        GeneralInformations generalInformations = profiles.get(0).getGeneralInformations();

                        if (generalInformations != null) {

                            String name_age = generalInformations.getFirstName() + ", " + generalInformations.getAge();
                            firstNameTextView.setText(name_age);

                        }

                        if (photosList.size() > 0) {

                            for (Photos photos : photosList) {

                                if (photos.isSelected() && photos.getStatus().equals("avatar")) {

                                    Glide.with(this).load(photos.getPhotoUrl()).into(userProfileImageView);

                                }

                            }

                        }

                    }

                }

            }

        });
    }
}