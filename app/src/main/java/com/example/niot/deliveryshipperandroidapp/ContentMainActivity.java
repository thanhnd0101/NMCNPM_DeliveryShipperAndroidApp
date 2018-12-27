package com.example.niot.deliveryshipperandroidapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.usage.NetworkStatsManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.niot.deliveryshipperandroidapp.Adapter.PagerAdapter;
import com.example.niot.deliveryshipperandroidapp.Interface.RecylerViewClickListener;
import com.example.niot.deliveryshipperandroidapp.Model.Bill;
import com.example.niot.deliveryshipperandroidapp.Model.BillDetail;
import com.example.niot.deliveryshipperandroidapp.Model.Restaurant;
import com.example.niot.deliveryshipperandroidapp.Model.User;
import com.example.niot.deliveryshipperandroidapp.Response.BillsResponse;
import com.example.niot.deliveryshipperandroidapp.Tab.OrderDeliveringTab;
import com.example.niot.deliveryshipperandroidapp.Tab.OrderNeedDeliveryTab;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class ContentMainActivity extends AppCompatActivity implements RecylerViewClickListener{
    private final int ORDER_NEED_DELIVERY = 0;
    private int REQUEST_CODE_NEW_ORDER_ACTIVITY = 11;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;
    private int id_shipper;
    BillsResponse billsResponse;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        id_shipper = getIntent().getIntExtra("idShipper",1);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.content_main_viewpager);
        tabLayout = findViewById(R.id.content_main_tab_layout);

        pagerAdapter.addFragment(OrderNeedDeliveryTab.newOrderNeedDelivery(ContentMainActivity.this,this),"New Order");
        pagerAdapter.addFragment(OrderDeliveringTab.newOrderDeliveringTabInstance(ContentMainActivity.this,this, id_shipper),"Deliviring Order");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }


    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(ContentMainActivity.this, NewOrderActivity.class);
        billsResponse = ((OrderNeedDeliveryTab)pagerAdapter.getFragment(0)).ds_hoa_don.get(position);
        intent.putExtra("billResponse",billsResponse);
        intent.putExtra("idshipper",id_shipper);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ContentMainActivity.this.startActivityForResult(intent,REQUEST_CODE_NEW_ORDER_ACTIVITY);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_NEW_ORDER_ACTIVITY){
            if(resultCode == Activity.RESULT_OK){
                OrderDeliveringTab orderDeliveringTab = ((OrderDeliveringTab)pagerAdapter.getFragment(1));
                orderDeliveringTab.setBillDelivering(billsResponse);
                Objects.requireNonNull(viewPager.getAdapter()).notifyDataSetChanged();
            }
        }
    }
}
