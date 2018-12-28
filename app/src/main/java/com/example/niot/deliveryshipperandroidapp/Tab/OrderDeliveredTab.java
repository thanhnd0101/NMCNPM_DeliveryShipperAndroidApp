package com.example.niot.deliveryshipperandroidapp.Tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.niot.deliveryshipperandroidapp.Adapter.OrderNeedDeliveryRecyclerViewAdapter;
import com.example.niot.deliveryshipperandroidapp.Interface.RecylerViewClickListener;
import com.example.niot.deliveryshipperandroidapp.R;
import com.example.niot.deliveryshipperandroidapp.Response.BillsResponse;
import com.example.niot.deliveryshipperandroidapp.retrofit.CvlApi;
import com.example.niot.deliveryshipperandroidapp.retrofit.RetrofitObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("ValidFragment")
public class OrderDeliveredTab extends Fragment {
    private Context context;
    private RecylerViewClickListener mListener;
    private RecyclerView recyclerView;
    private View view;

    private List<BillsResponse> ds_hoa_don;
    private OrderNeedDeliveryRecyclerViewAdapter adapter;

    private int idShipper;

    public OrderDeliveredTab(Context context, RecylerViewClickListener mListener,int id_shipper) {
        this.context = context;
        this.mListener = mListener;
        this.idShipper = id_shipper;
    }
    public static OrderDeliveredTab newOrderDeliveredTab(Context context, RecylerViewClickListener mListener,int id_shipper){
        OrderDeliveredTab orderDeliveredTab = new OrderDeliveredTab(context,mListener, id_shipper);
        Bundle bundle = new Bundle();
        orderDeliveredTab.setArguments(bundle);
        orderDeliveredTab.setRetainInstance(true);
        return orderDeliveredTab;
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

        initOrderDeliveredView();
        return view;
    }

    private void initOrderDeliveredView() {
        if (!isOnline()) {
            Toast.makeText(context, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        setRecyclerLayoutManager();
        loadData();
    }

    private void loadData() {

        Map<String,String> queryOptions = new HashMap<>();
        queryOptions.put("trangthai","4");
        queryOptions.put("id_shipper",String.valueOf(idShipper));

        CvlApi api = RetrofitObject.getInstance().create(CvlApi.class);
        Call<List<BillsResponse>> call = api.billNeedDelivery(queryOptions);

        call.enqueue(new Callback<List<BillsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<BillsResponse>> call, @NonNull Response<List<BillsResponse>> response) {
                ds_hoa_don = response.body();
                adapter = new OrderNeedDeliveryRecyclerViewAdapter(context, ds_hoa_don, mListener);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<BillsResponse>> call, Throwable t) {
                Toast.makeText(context, "Error Fetching Bill data\n\n\n" + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void getMyView() {
        recyclerView = view.findViewById(R.id.recycler_view_need_delivery);
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
    private void setRecyclerLayoutManager() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
}
