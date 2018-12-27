package com.example.niot.deliveryshipperandroidapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niot.deliveryshipperandroidapp.Adapter.NewOrderFoodInforRecyclerViewAdapter;
import com.example.niot.deliveryshipperandroidapp.Response.BillsResponse;
import com.example.niot.deliveryshipperandroidapp.Response.PostResponse;
import com.example.niot.deliveryshipperandroidapp.retrofit.CvlApi;
import com.example.niot.deliveryshipperandroidapp.retrofit.RetrofitObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewOrderActivity extends AppCompatActivity {
    private BillsResponse billsResponse;
    private RecyclerView recyclerView;

    private TextView textViewNew_order_detail_thoi_gian;
    private TextView textViewNew_order_detail_dia_chi_khach;
    private TextView textViewNew_order_detail_dia_chi_quan;
    private Button buttonAcceptOrder;

    private int idShipper;

    private PostResponse postResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_order_detail_layout);
        Bundle bundle = getIntent().getExtras();
        idShipper = getIntent().getIntExtra("idshipper",0);

        getView();

        buttonAcceptOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setButtonAcceptOrder();
            }
        });

        assert bundle != null;
        getBillReponse(bundle);

        initNewOrderDetail();

    }
    private void setButtonAcceptOrder() {
        Map<String,String> queryOptions;
        queryOptions = new HashMap<>();
        queryOptions.put("id_shipper",String.valueOf(idShipper));
        queryOptions.put("id_hoa_don",String.valueOf(billsResponse.getHoadon().getIdHoaDon()));
        queryOptions.put("trangthai","2");


        CvlApi api = RetrofitObject.getInstance().create(CvlApi.class);
        Call<PostResponse> call = api.validateOrder(queryOptions);
        Log.e("url\n\n\n",call.request().url().toString());

        call.enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(@NonNull Call<PostResponse> call, @NonNull Response<PostResponse> response) {
                postResponse = response.body();
                if(postResponse != null){
                    if(postResponse.getStatus() == 0){
                        validateOrder(postResponse.getStatus());
                    }else{
                        Toast.makeText(NewOrderActivity.this,"Không thể xác nhận đơn giao hàng",Toast.LENGTH_SHORT).show();
                    }
                }
                Log.e("win\n\n\n",call.request().url().toString());
            }
            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Log.e("error\n\n\n",t.getMessage());
                Toast.makeText(NewOrderActivity.this,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateOrder(int status) {
        Intent returnIntent = new Intent();
        if(status == 0) {
            setResult(Activity.RESULT_OK, returnIntent);
        }else{
            setResult(Activity.RESULT_CANCELED,returnIntent);
        }
        finish();
    }


    private void getView() {
        recyclerView = findViewById(R.id.recycler_view_order_detail_food);
        textViewNew_order_detail_dia_chi_khach = findViewById(R.id.new_order_detail_dia_chi_khach);
        textViewNew_order_detail_dia_chi_quan = findViewById(R.id.new_order_detail_dia_chi_quan);
        textViewNew_order_detail_thoi_gian = findViewById(R.id.new_order_detail_thoi_gian);
        buttonAcceptOrder = findViewById(R.id.buttonAcceptOrder);
    }

    private void initNewOrderDetail() {

        if (!isOnline()) {
            Toast.makeText(this, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        setRecyclerLayoutManager();
        setValueDetailOrder();
    }

    private void setValueDetailOrder() {
        textViewNew_order_detail_thoi_gian.setText(billsResponse.getHoadon().getTGKhachHangDat());
        textViewNew_order_detail_dia_chi_khach.setText(billsResponse.getHoadon().getDiaChiaGiao());
        textViewNew_order_detail_dia_chi_quan.setText(billsResponse.getQuanan().getAddress());
        NewOrderFoodInforRecyclerViewAdapter adapter = new NewOrderFoodInforRecyclerViewAdapter(NewOrderActivity.this,billsResponse);
        recyclerView.setAdapter(adapter);
    }

    private void getBillReponse(Bundle bundle) {
        billsResponse = (BillsResponse)bundle.get("billResponse");
    }
    private void setRecyclerLayoutManager() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
