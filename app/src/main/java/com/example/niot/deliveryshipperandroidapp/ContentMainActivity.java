package com.example.niot.deliveryshipperandroidapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;


import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.niot.deliveryshipperandroidapp.Adapter.PagerAdapter;
import com.example.niot.deliveryshipperandroidapp.Interface.RecylerViewClickListener;
import com.example.niot.deliveryshipperandroidapp.Tab.OrderNeedDelivery;


public class ContentMainActivity extends AppCompatActivity {
    private final int ORDER_NEED_DELIVERY = 0;
    PagerAdapter pagerAdapter;
    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.content_main_viewpager);
        tabLayout = findViewById(R.id.content_main_tab_layout);

        pagerAdapter.addFragment(OrderNeedDelivery.newOrderNeedDelivery(this,this),"New Order");

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
