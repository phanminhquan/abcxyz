package com.mtb.foodorderreview.homeview;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mtb.foodorderreview.R;
import com.mtb.foodorderreview.utils.IClickListener;

public class RestaurantHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
    private Restaurant item;
    private TextView food_shop_name1;
    private CardView food_shop_cardView1;
    private ImageView food_shop_image1;
    private IClickListener<Restaurant> clickListener;


    public RestaurantHolder(@NonNull View itemView) {
        super(itemView);

        itemView.setOnClickListener(this);

        food_shop_name1 = itemView.findViewById(R.id.food_shop_name1);
        food_shop_cardView1 = itemView.findViewById(R.id.food_shop_cardView1);
        food_shop_image1 = itemView.findViewById(R.id.food_shop_image1);
    }

    public void setClickListener(IClickListener<Restaurant> clickListener) {
        this.clickListener = clickListener;
    }

    public TextView getFood_shop_name1() {
        return food_shop_name1;
    }

    public CardView getFood_shop_cardView1() {
        return food_shop_cardView1;
    }

    public ImageView getFood_shop_image1() {
        return food_shop_image1;
    }

    public void setItem(Restaurant item) {
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
