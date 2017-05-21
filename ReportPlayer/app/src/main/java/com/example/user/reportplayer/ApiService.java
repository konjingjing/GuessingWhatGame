package com.example.user.reportplayer;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by MooKartiN on 21/5/2560.
 */

public interface ApiService {
    @POST("connectUserDB.php")
    Call<UserItemCollectionDao> loadUserList();
}
