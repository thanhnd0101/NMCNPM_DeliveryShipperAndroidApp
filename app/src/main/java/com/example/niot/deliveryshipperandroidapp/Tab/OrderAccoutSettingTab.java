package com.example.niot.deliveryshipperandroidapp.Tab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niot.deliveryshipperandroidapp.R;

public class OrderAccoutSettingTab extends Fragment {

    private View view;

    public OrderAccoutSettingTab(){}

    public static OrderAccoutSettingTab newOrderAccountSettingTab(){
        OrderAccoutSettingTab orderAccoutSettingTab = new OrderAccoutSettingTab();
        Bundle bundle = new Bundle();
        orderAccoutSettingTab.setArguments(bundle);
        orderAccoutSettingTab.setRetainInstance(true);
        return  orderAccoutSettingTab;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.account_setting_layout,container,false);

        return view;
    }
}
