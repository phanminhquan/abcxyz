package com.mtb.foodorderreview.checkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtb.foodorderreview.R;
import com.mtb.foodorderreview.global.CartFood;
import com.mtb.foodorderreview.utils.Utils;

import java.util.List;

public class CartFoodListViewAdapter extends BaseAdapter {

    private final List<CartFood> list;
    private final LayoutInflater inflater;

    public CartFoodListViewAdapter(Context context, List<CartFood> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CartFood getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_cart_food, null);
        CartFood item = list.get(position);
        ImageView cart_food_image;
        TextView cart_food_quantity,
                cart_food_name,
                cart_food_price;
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        cart_food_image = convertView.findViewById(R.id.cart_food_image);
        cart_food_quantity = convertView.findViewById(R.id.cart_food_quantity);
        cart_food_name = convertView.findViewById(R.id.cart_food_name);
        cart_food_price = convertView.findViewById(R.id.cart_food_price);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        Utils.UI.setSrc(item.getFood().getImage(), cart_food_image);
        cart_food_quantity.setText(String.valueOf(item.getQuantity()));
        cart_food_name.setText(item.getFood().getName());
        cart_food_price.setText(Utils.currency(item.getFood().getPrice() * item.getQuantity()));

        return convertView;
    }
}
