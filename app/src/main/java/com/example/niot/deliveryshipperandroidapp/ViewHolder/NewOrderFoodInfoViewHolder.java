package com.example.niot.deliveryshipperandroidapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.niot.deliveryshipperandroidapp.R;

public class NewOrderFoodInfoViewHolder extends RecyclerView.ViewHolder {
    private TextView textViewFoodName;
    private TextView textViewFoodAmount;
    private TextView textViewFoodPrice;
    public NewOrderFoodInfoViewHolder(@NonNull View itemView) {
        super(itemView);
        getView(itemView);
    }

    private void getView(View itemView) {
        textViewFoodName = itemView.findViewById(R.id.new_order_detail_food_name);
        textViewFoodAmount= itemView.findViewById(R.id.new_order_detail_food_amount);
        textViewFoodPrice = itemView.findViewById(R.id.new_order_detail_food_price);
    }
    public void setNewOrderFoodInfoViewHolder(String name,String amount,String price){
        textViewFoodName.setText(name);
        textViewFoodAmount.setText(amount);
        textViewFoodPrice.setText(price);
    }
}
