package com.example.user.reportplayer;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by MooKartiN on 21/5/2560.
 */

public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance(){
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private ApiService service;

    private Context mContext;
    private HttpManager(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http//localhost/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(ApiService.class);
    }

    public ApiService getService(){
        return service;
    }
}
