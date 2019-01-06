package com.example.niot.deliveryshipperandroidapp.retrofit;

import com.example.niot.deliveryshipperandroidapp.Model.Shipper;
import com.example.niot.deliveryshipperandroidapp.Response.BillsResponse;
import com.example.niot.deliveryshipperandroidapp.Response.PostResponse;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface CvlApi {
    @GET("/signup/shipper")
    Call<List<Shipper>> newUser(@QueryMap Map<String, String> info);

    @GET("/login/shipper")
    Call<List<Shipper>> logginShipper(@QueryMap Map<String, String> info);

    @GET("/hoa_don")
    Call<List<BillsResponse>> billNeedDelivery(@QueryMap Map<String, String> info);

    @GET("/giao_hang")
    Call<PostResponse> validateOrder(@QueryMap Map<String,String> info);
}
