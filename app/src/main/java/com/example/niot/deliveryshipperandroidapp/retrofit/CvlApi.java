package com.example.niot.deliveryshipperandroidapp.retrofit;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface CvlApi {
    @GET("/signup/nd")
    Call<List<User>> newUser(@QueryMap Map<String, String> info);
}
