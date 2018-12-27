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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.niot.deliveryshipperandroidapp.Adapter.NewOrderFoodInforRecyclerViewAdapter;
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
public class OrderDeliveringTab extends Fragment {
    private Context context;
    private RecylerViewClickListener mListener;
    private int idShipper;

    private BillsResponse billsResponse = null;
    private View view;

    private RecyclerView recyclerView;

    private TextView textViewNew_order_detail_delivering_id_hoa_don;
    private TextView textViewNew_order_detail_delivering_thoi_gian;
    private TextView textViewNew_order_detail_delivering_dia_chi_khach;
    private TextView textViewNew_order_detail_delivering_dia_chi_quan;
    private Button buttonValidateTakenOrder;
    private Button buttonValidateDeliveredOrder;
    Map<String,String> queryOptions;

    private NewOrderFoodInforRecyclerViewAdapter adapter;
    @SuppressLint("StaticFieldLeak")
    static OrderDeliveringTab orderNeedDeliveryTab;

    public OrderDeliveringTab(Context context, RecylerViewClickListener mListener, int id_shipper) {
        this.context = context;
        this.mListener = mListener;
        this.idShipper = id_shipper;
    }

    public static OrderDeliveringTab newOrderDeliveringTabInstance(Context context, RecylerViewClickListener mListener, int id_shipper){
        orderNeedDeliveryTab = new OrderDeliveringTab(context,mListener,id_shipper);
        Bundle bundle = new Bundle();
        orderNeedDeliveryTab.setArguments(bundle);
        orderNeedDeliveryTab.setRetainInstance(true);
        return orderNeedDeliveryTab;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.new_order_detail_delivering_layout,container,false);
        getMyView();

        initOrderDeliveringView();
        return view;
    }

    private void initOrderDeliveringView() {
        if (!isOnline()) {
            Toast.makeText(context, "You are not connected to Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        setRecyclerLayoutManager();
        loadData();
    }

    public void loadData() {
        queryOptions = new HashMap<>();
        queryOptions.put("shipper",String.valueOf(idShipper));
        queryOptions.put("trangthai","2");

        CvlApi api = RetrofitObject.getInstance().create(CvlApi.class);

        Call<List<BillsResponse>> call = api.billNeedDelivery(queryOptions);

        call.enqueue(new Callback<List<BillsResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<BillsResponse>> call, @NonNull Response<List<BillsResponse>> response) {
                assert response.body() != null;
                if(!response.body().isEmpty()) {
                    billsResponse = response.body().get(response.body().size()-1);
                    if (billsResponse != null) {
                        setBillDelivering(billsResponse);
                    }
                }else{
                    setEnableOrDisableView(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<BillsResponse>> call, @NonNull Throwable t) {
                Toast.makeText(context,t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getMyView() {
        recyclerView = view.findViewById(R.id.recycler_view_order_detail_delivering_food);
        textViewNew_order_detail_delivering_id_hoa_don=view.findViewById(R.id.new_order_detail_delivering_id_hoa_don);
        textViewNew_order_detail_delivering_dia_chi_khach = view.findViewById(R.id.new_order_detail_delivering_dia_chi_khach);
        textViewNew_order_detail_delivering_dia_chi_quan = view.findViewById(R.id.new_order_detail_delivering_dia_chi_quan);
        textViewNew_order_detail_delivering_thoi_gian = view.findViewById(R.id.new_order_detail_delivering_thoi_gian);
        buttonValidateDeliveredOrder = view.findViewById(R.id.buttonValidateDeliveredOrder);
        buttonValidateTakenOrder = view.findViewById(R.id.buttonValidateTakenOrder);
    }

    public void setBillDelivering(BillsResponse billDelivering){
        setEnableOrDisableView(true);
        setValueBillDelivering(billDelivering);
        NewOrderFoodInforRecyclerViewAdapter adapter = new NewOrderFoodInforRecyclerViewAdapter(context,billsResponse);
        recyclerView.setAdapter(adapter);
        //view.invalidate();
    }
    private void setValueBillDelivering(BillsResponse billDelivering){
        textViewNew_order_detail_delivering_id_hoa_don.setText(String.valueOf(billDelivering.getHoadon().getIdHoaDon()));
        textViewNew_order_detail_delivering_dia_chi_khach.setText(billDelivering.getHoadon().getDiaChiaGiao());
        textViewNew_order_detail_delivering_dia_chi_quan.setText(billDelivering.getQuanan().getAddress());
        textViewNew_order_detail_delivering_thoi_gian.setText(billDelivering.getHoadon().getTGKhachHangDat());
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
    private void setEnableOrDisableView(boolean enalble){
        textViewNew_order_detail_delivering_dia_chi_khach.setEnabled(enalble);
        textViewNew_order_detail_delivering_dia_chi_quan.setEnabled(enalble);
        textViewNew_order_detail_delivering_thoi_gian.setEnabled(enalble);
        buttonValidateDeliveredOrder.setEnabled(enalble);
        buttonValidateTakenOrder.setEnabled(enalble);
    }
}
