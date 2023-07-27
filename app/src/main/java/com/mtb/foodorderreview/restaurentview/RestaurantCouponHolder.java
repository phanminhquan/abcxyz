package com.mtb.foodorderreview.restaurentview;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;
import com.mtb.foodorderreview.utils.IClickListener;

public class RestaurantCouponHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private RestaurantCoupon item;
    private final TextView restaurant_coupon_detail1;
    private final Button restaurant_coupon_claim_btn1;
    private final RelativeLayout restaurant_coupon_layout1;
    private IClickListener<RestaurantCoupon> clickListener;
    private IClickListener<RestaurantCoupon> btnClickListener;


    public RestaurantCouponHolder(@NonNull View itemView) {
        super(itemView);

        restaurant_coupon_detail1 = itemView.findViewById(R.id.restaurant_coupon_detail1);
        restaurant_coupon_claim_btn1 = itemView.findViewById(R.id.restaurant_coupon_claim_btn1);
        restaurant_coupon_layout1 = itemView.findViewById(R.id.restaurant_coupon_layout1);

        itemView.setOnClickListener(this);

        restaurant_coupon_claim_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnClickListener != null)
                    btnClickListener.onClick(v, RestaurantCouponHolder.this.getAdapterPosition(), false, item);
            }
        });
    }

    public void setClickListener(IClickListener<RestaurantCoupon> clickListener) {
        this.clickListener = clickListener;
    }

    public void setBtnClickListener(IClickListener<RestaurantCoupon> btnClickListener) {
        this.btnClickListener = btnClickListener;
    }

    public RelativeLayout getRestaurant_coupon_layout1() {
        return restaurant_coupon_layout1;
    }

    public TextView getRestaurant_coupon_detail1() {
        return restaurant_coupon_detail1;
    }

    public Button getRestaurant_coupon_claim_btn1() {
        return restaurant_coupon_claim_btn1;
    }

    public RestaurantCoupon getItem() {
        return item;
    }

    public void setItem(RestaurantCoupon item) {
        this.item = item;
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null)
            clickListener.onClick(v, getAdapterPosition(), false, item);
    }

    @Override
    public boolean onLongClick(View v) {
        if (clickListener != null)
            clickListener.onClick(v, getAdapterPosition(), true, item);

        return false;
    }
}
