package com.example.niot.deliveryshipperandroidapp.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Restaurant extends Account implements Serializable {
    @SerializedName("dchi")
    @Expose
    private String address;
    @SerializedName("anh")
    @Expose
    private String image_path;

    public Restaurant(String address, String image_path) {
        this.address = address;
        this.image_path = image_path;
    }

    public String getAddress() {
        return address;
    }

    public String getImage_path() {
        return image_path;
    }

    @Override
    public String toString() {
        return super.toString() + "\nAddress: " + address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
