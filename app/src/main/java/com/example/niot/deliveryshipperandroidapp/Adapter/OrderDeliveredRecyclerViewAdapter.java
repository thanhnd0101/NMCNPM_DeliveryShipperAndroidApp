package com.example.niot.deliveryshipperandroidapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niot.deliveryshipperandroidapp.Interface.RecylerViewClickListener;
import com.example.niot.deliveryshipperandroidapp.R;
import com.example.niot.deliveryshipperandroidapp.Response.BillsResponse;
import com.example.niot.deliveryshipperandroidapp.ViewHolder.OrderNeedDeliveryViewHolder;

import java.util.List;

public class OrderDeliveredRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<BillsResponse> ds_hoa_don;
    private RecylerViewClickListener mListener;

    public OrderDeliveredRecyclerViewAdapter(Context context, List<BillsResponse> ds_hoa_don, RecylerViewClickListener mListener) {
        this.context = context;
        this.ds_hoa_don = ds_hoa_don;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_item_layout,viewGroup,false);
        return new OrderNeedDeliveryViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        OrderNeedDeliveryViewHolder orderNeedDeliveryViewHolder = (OrderNeedDeliveryViewHolder)viewHolder;
        configureOrderNeedDeliveryViewHolder(orderNeedDeliveryViewHolder, position);
    }

    private void configureOrderNeedDeliveryViewHolder(OrderNeedDeliveryViewHolder orderNeedDeliveryViewHolder, int position) {
        BillsResponse billsResponse = ds_hoa_don.get(position);
        if(billsResponse.getQuanan().getAddress() == null){
            billsResponse.getQuanan().setAddress("Hoang Van Thu");
        }
        orderNeedDeliveryViewHolder.setOrderNeedDeliveryViewHolder(
                String.valueOf(billsResponse.getHoadon().getIdHoaDon()),
                billsResponse.getHoadon().getTGKhachHangDat(),
                billsResponse.getQuanan().getAddress(),
                billsResponse.getHoadon().getDiaChiaGiao(),
                String.valueOf(billsResponse.getHoadon().getGiaHoaDon()),
                String.valueOf(billsResponse.getHoadon().getGiaVanCHuyen()));
    }

    @Override
    public int getItemCount() {
        return ds_hoa_don.size();
    }
}
