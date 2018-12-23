package com.example.niot.deliveryshipperandroidapp.Tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
public class OrderNeedDelivery extends Fragment {
    private List<BillsResponse> ds_hoa_don;
    private OrderNeedDeliveryRecyclerViewAdapter adapter;
    private RecylerViewClickListener mListener;

    private Context context;
    private RecyclerView recyclerView;
    private View view;
    Map<String, String> queryOptions;

    public OrderNeedDelivery(Context context,RecylerViewClickListener listener) {
        this.context = context;
        this.mListener = listener;
    }
    public static OrderNeedDelivery newOrderNeedDelivery(Context context, RecylerViewClickListener listener){
        OrderNeedDelivery orderNeedDelivery = new OrderNeedDelivery(context,listener);
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

        initOrderNeedDeliveryView();
        return  view;
    }

    private void initOrderNeedDeliveryView() {
        if (!isOnline()) {
            Toast.makeText(context, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        setRecyclerLayoutManager();
        loadJSON();
    }

    private void loadJSON() {
        try{
            queryOptions = new HashMap<>();
            queryOptions.put("trangthai","1");

            CvlApi api = RetrofitObject.getInstance().create(CvlApi.class);
            Call<List<BillsResponse>> call = api.billNeedDelivery(queryOptions);
            Log.e("url \n\n\n\n\n\n", call.request().url().toString());

            call.enqueue(new Callback<List<BillsResponse>>() {
                @Override
                public void onResponse(Call<List<BillsResponse>> call, Response<List<BillsResponse>> response) {
                    ds_hoa_don = response.body();

                    if(ds_hoa_don!=null){
                        adapter = new OrderNeedDeliveryRecyclerViewAdapter(context,ds_hoa_don,mListener);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<List<BillsResponse>> call, Throwable t) {
                    Toast.makeText(context, "Error Fetching Bill data\n\n\n" + t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("Error\n\n\n",t.getMessage());
                }
            });
        }catch (Exception e){
            Log.e("error", e.getMessage());
        }
    }

    private void getMyView() {
        recyclerView = view.findViewById(R.id.recycler_view_need_delivery);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void setRecyclerLayoutManager() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
