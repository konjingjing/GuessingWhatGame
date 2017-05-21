package com.example.user.reportplayer;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by MooKartiN on 21/5/2560.
 */

public class UserItemDao implements Parcelable {
    @SerializedName("userID")       private String userID;
    @SerializedName("profileName")  private String profileName;
    @SerializedName("rating")       private float rating;
    @SerializedName("profileImage") private String profileImage;
    @SerializedName("userPlayer2ID") private String userPlayer2ID;

    protected UserItemDao(Parcel in) {
        userID = in.readString();
        profileName = in.readString();
        rating = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userID);
        dest.writeString(profileName);
        dest.writeFloat(rating);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserItemDao> CREATOR = new Creator<UserItemDao>() {
        @Override
        public UserItemDao createFromParcel(Parcel in) {
            return new UserItemDao(in);
        }

        @Override
        public UserItemDao[] newArray(int size) {
            return new UserItemDao[size];
        }
    };

    public String getProfileImage() {
        return profileImage;
    }

    public String getUserPlayer2ID() {
        return userPlayer2ID;
    }

    public void setUserPlayer2ID(String userPlayer2ID) {
        this.userPlayer2ID = userPlayer2ID;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
