package com.example.niot.deliveryshipperandroidapp.Response;

import com.example.niot.deliveryshipperandroidapp.Model.Bill;
import com.example.niot.deliveryshipperandroidapp.Model.BillDetail;
import com.example.niot.deliveryshipperandroidapp.Model.Restaurant;
import com.example.niot.deliveryshipperandroidapp.Model.User;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BillsResponse implements Serializable {
    @SerializedName("hoa_don")
    private Bill hoadon;
    @SerializedName("nguoi_dung")
    private User nguoidung;
    @SerializedName("quan_an")
    private Restaurant quanan;
    @SerializedName("chi_tiet")
    private List<BillDetail> chi_tiet;

    public BillsResponse(Bill hoadon, User nguoidung, Restaurant quanan, List<BillDetail> chi_tiet) {
        this.hoadon = hoadon;
        this.nguoidung = nguoidung;
        this.quanan = quanan;
        this.chi_tiet = chi_tiet;
    }

    public Bill getHoadon() {
        return hoadon;
    }

    public User getNguoidung() {
        return nguoidung;
    }

    public Restaurant getQuanan() {
        return quanan;
    }

    public List<BillDetail> getChi_tiet() {
        return chi_tiet;
    }
}
