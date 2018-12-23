package com.example.niot.deliveryshipperandroidapp.Tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niot.deliveryshipperandroidapp.R;
import com.example.niot.deliveryshipperandroidapp.Response.BillsResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint("ValidFragment")
public class OrderNeedDelivery extends Fragment {
    private List<BillsResponse> ds_hoa_don;

    private Context context;
    private RecyclerView recyclerView;
    private View view;
    Map<String, String> queryOptions;

    public OrderNeedDelivery(Context context) {
        this.context = context;
    }
    public static OrderNeedDelivery newOrderNeedDelivery(Context context){
        OrderNeedDelivery orderNeedDelivery = new OrderNeedDelivery(context);
        Bundle bundle = new Bundle();
        orderNeedDelivery.setArguments(bundle);
        orderNeedDelivery.setRetainInstance(true);
        return  orderNeedDelivery;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.order_recycler_view_need_delivery,container,false);
        getMyView();

        queryOptions = new HashMap<>();
        queryOptions.put("trangthai","1");

        initOrderNeedDeliveryView();
        return  view;
    }

    private void initOrderNeedDeliveryView() {

    }

    private void getMyView() {
        recyclerView = view.findViewById(R.id.recycler_view_need_delivery);

    }
}
