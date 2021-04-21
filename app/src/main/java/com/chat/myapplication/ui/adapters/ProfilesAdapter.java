package com.chat.myapplication.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chat.myapplication.R;
import com.chat.myapplication.model.profile.LikesProfiles;

import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ProfilesAdapter extends RecyclerView.Adapter<ProfilesAdapter.ProfilesHolder> {

    private boolean isProfileVisible;
    private final Context context;
    private List<LikesProfiles> profilesList;

    public ProfilesAdapter(Context context, List<LikesProfiles> profilesList) {

        this.context = context;
        this.profilesList = profilesList;
    }

    public void updateTheAdapter(boolean isProfileVisible, List<LikesProfiles> profilesList) {

        this.isProfileVisible = isProfileVisible;
        this.profilesList = profilesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProfilesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_profiles, parent, false);
        return new ProfilesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilesHolder holder, int position) {

        if (isProfileVisible) {

            Glide.with(context)
                    .load(profilesList.get(position).getAvatarUrl())
                    .into(holder.userProfileImageView);

        } else {

            Glide.with(context)
                    .load(profilesList.get(position).getAvatarUrl())
                    .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                    .into(holder.userProfileImageView);

        }

        holder.firstNameTextView.setText(profilesList.get(position).getFirstName());
    }

    @Override
    public int getItemCount() {

        return profilesList.size();
    }

    public static class ProfilesHolder extends RecyclerView.ViewHolder {

        private final ImageView userProfileImageView;
        private final TextView firstNameTextView;

        public ProfilesHolder(@NonNull View itemView) {
            super(itemView);

            firstNameTextView = itemView.findViewById(R.id.userNameText);
            userProfileImageView = itemView.findViewById(R.id.userProfileImage);
        }
    }
}
