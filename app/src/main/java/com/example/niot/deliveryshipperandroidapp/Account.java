package com.example.niot.deliveryshipperandroidapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Account implements Serializable {
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ten")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("sdt")
    @Expose
    private String phone;
    @SerializedName("pass")
    @Expose
    private String password;
    @SerializedName("is_login")
    @Expose
    private boolean isLogin;

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsLogin() {
        return isLogin;
    }

    @Override
    public String toString() {
        String ans = "ID = " + String.valueOf(id) + "\nName = " + name + "\nEmail = "
                + email + "\nPhone = " + phone + "\nisLogin: " + isLogin;
        return ans;
    }
}
