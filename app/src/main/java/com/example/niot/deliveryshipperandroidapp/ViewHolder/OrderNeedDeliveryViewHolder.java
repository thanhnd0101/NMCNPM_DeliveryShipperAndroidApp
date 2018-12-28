package com.example.niot.deliveryshipperandroidapp.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.niot.deliveryshipperandroidapp.Interface.RecylerViewClickListener;
import com.example.niot.deliveryshipperandroidapp.R;

public class OrderNeedDeliveryViewHolder extends RecyclerView.ViewHolder{
    private TextView order_item_text_view_ma_hoa_don;
    private TextView order_item_text_view_ngay_dat;
    private TextView order_item_text_view_dia_chi_quan;
    private TextView order_item_text_view_dia_chi_khach;
    private TextView order_item_text_view_gia_van_chuyen;

    private RecylerViewClickListener mlistener;

    public OrderNeedDeliveryViewHolder(@NonNull View itemView, final RecylerViewClickListener listener) {
        super(itemView);
        this.mlistener = listener;
        getMyView(itemView);
        setViewChildClick(itemView);
    }

    private void setViewChildClick(final View itemView) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mlistener != null){
                    int posisiton = getAdapterPosition();
                    if(posisiton != RecyclerView.NO_POSITION){
                        mlistener.onItemClick(itemView,posisiton);
                    }
                }
            }
        });
    }

    private void getMyView(View itemView) {
        order_item_text_view_ma_hoa_don = itemView.findViewById(R.id.order_item_text_view_ma_hoa_don);
        order_item_text_view_ngay_dat = itemView.findViewById(R.id.order_item_text_view_ngay_dat);
        order_item_text_view_dia_chi_quan = itemView.findViewById(R.id.order_item_text_view_dia_chi_quan);
        order_item_text_view_dia_chi_khach = itemView.findViewById(R.id.order_item_text_view_dia_chi_khach);
        order_item_text_view_gia_van_chuyen = itemView.findViewById(R.id.order_item_text_view_gia_van_chuyen);
    }
    public void setOrderNeedDeliveryViewHolder(String mahoadon,String ngaydat,String diachiquan,String diachikhach,
    String giavanchuyen){
        mahoadon = "Đơn #" + mahoadon;
        order_item_text_view_ma_hoa_don.setText(mahoadon);
        order_item_text_view_ngay_dat.setText(ngaydat);
        order_item_text_view_dia_chi_quan.setText(diachiquan);
        order_item_text_view_dia_chi_khach.setText(diachikhach);
        order_item_text_view_gia_van_chuyen.setText(giavanchuyen);
    }
}
