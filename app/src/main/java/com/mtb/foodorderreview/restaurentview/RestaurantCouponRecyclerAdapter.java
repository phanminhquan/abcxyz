package com.mtb.foodorderreview.restaurentview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;
import com.mtb.foodorderreview.global.CartGlobal;
import com.mtb.foodorderreview.utils.IClickListener;

import java.util.List;

public class RestaurantCouponRecyclerAdapter extends RecyclerView.Adapter<RestaurantCouponHolder> {
    private List<RestaurantCoupon> list;
    private final String CAN_USE = "Dùng";
    private final String USED = "Đã dùng";
    private final LayoutInflater inflater;
    private IClickListener<RestaurantCoupon> clickListener;
    private IClickListener<RestaurantCoupon> btnClickListener;

    private int margin = -1;

    public RestaurantCouponRecyclerAdapter(Context context, List<RestaurantCoupon> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    public void setClickListener(IClickListener<RestaurantCoupon> clickListener) {
        this.clickListener = clickListener;
    }

    public void setBtnClickListener(IClickListener<RestaurantCoupon> btnClickListener) {
        this.btnClickListener = btnClickListener;
    }

    @NonNull
    @Override
    public RestaurantCouponHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.adapter_restaurant_coupon, null);
        return new RestaurantCouponHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantCouponHolder holder, int position) {
        RestaurantCoupon item = list.get(position);

        holder.setItem(item);
        holder.getRestaurant_coupon_detail1().setText(item.getTitle());
        holder.setClickListener(clickListener);

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) holder.getRestaurant_coupon_layout1().getLayoutParams();
        if (margin == -1) margin = lp.rightMargin;

        if (position != 0) {
            lp.leftMargin = 0;
        }

        if (position < list.size() - 1) {
            lp.rightMargin = this.margin / 2;
        }
        holder.getRestaurant_coupon_layout1().setLayoutParams(lp);

        RestaurantCoupon currentCoupon = CartGlobal.getInstance().getCoupon();
        if (currentCoupon != null && currentCoupon.getId() == item.getId()) {
            holder.getRestaurant_coupon_claim_btn1().setText(this.USED);
            holder.setBtnClickListener(null);
        } else {
            holder.getRestaurant_coupon_claim_btn1().setText(this.CAN_USE);
            holder.setBtnClickListener(btnClickListener);
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
