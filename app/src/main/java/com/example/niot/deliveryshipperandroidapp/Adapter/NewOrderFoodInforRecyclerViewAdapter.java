package com.example.niot.deliveryshipperandroidapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.niot.deliveryshipperandroidapp.Model.Bill;
import com.example.niot.deliveryshipperandroidapp.Model.BillDetail;
import com.example.niot.deliveryshipperandroidapp.R;
import com.example.niot.deliveryshipperandroidapp.Response.BillsResponse;
import com.example.niot.deliveryshipperandroidapp.ViewHolder.NewOrderFoodInfoViewHolder;

public class NewOrderFoodInforRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private BillsResponse billsResponse;

    public NewOrderFoodInforRecyclerViewAdapter(Context mContext, BillsResponse billsResponse) {
        this.mContext = mContext;
        this.billsResponse = billsResponse;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View view = inflater.inflate(R.layout.order_food_info_item_layout,viewGroup,false);
        viewHolder = new NewOrderFoodInfoViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        NewOrderFoodInfoViewHolder newOrderFoodInfoViewHolder = (NewOrderFoodInfoViewHolder)viewHolder;
        configureNewOrderFoodInfoViewHolder(newOrderFoodInfoViewHolder, i);
    }

    private void configureNewOrderFoodInfoViewHolder(NewOrderFoodInfoViewHolder newOrderFoodInfoViewHolder, int position) {
        BillDetail billDetail = billsResponse.getChi_tiet().get(position);
        newOrderFoodInfoViewHolder.setNewOrderFoodInfoViewHolder(billDetail.getTen(),
                String.valueOf(billDetail.getSoluong()),
                String.valueOf(billDetail.getGia()));
    }

    @Override
    public int getItemCount() {
        return billsResponse.getChi_tiet().size();
    }
    public void setBillsResponse(BillsResponse billsResponse){
        this.billsResponse = billsResponse;
        notifyDataSetChanged();
    }
}
