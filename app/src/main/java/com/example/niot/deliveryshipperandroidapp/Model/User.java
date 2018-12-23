package com.example.niot.deliveryshipperandroidapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User extends Account{
    @SerializedName("dchi")
    @Expose
    private String address;
    @SerializedName("anh")
    @Expose
    private String image_path;

    public String getAddress() {
        return address;
    }

    public String getImage_path() {
        return image_path;
    }
}