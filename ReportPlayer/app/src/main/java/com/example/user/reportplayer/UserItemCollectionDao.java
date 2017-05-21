package com.example.user.reportplayer;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by MooKartiN on 21/5/2560.
 */

public class UserItemCollectionDao {
    public List<UserItemDao> getUser() {
        return user;
    }

    public void setUser(List<UserItemDao> user) {
        this.user = user;
    }

    @SerializedName("user")     private List<UserItemDao> user;
}
