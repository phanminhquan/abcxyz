package com.mtb.foodorderreview.restaurentview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtb.foodorderreview.R;
import com.mtb.foodorderreview.utils.Utils;

import java.util.List;

public class RestaurantFoodGridAdapter extends BaseAdapter {

    private final List<RestaurantFood> list;
    private final LayoutInflater inflater;

    public RestaurantFoodGridAdapter(Context context, List<RestaurantFood> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RestaurantFood getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_restaurant_food, null);

        TextView restaurant_food_price,
                restaurant_food_des,
                restaurant_food_name;

        ImageView restaurant_food_cover;

        RestaurantFood item = list.get(position);
        restaurant_food_price = convertView.findViewById(R.id.restaurant_food_price);
        restaurant_food_des = convertView.findViewById(R.id.restaurant_food_des);
        restaurant_food_name = convertView.findViewById(R.id.restaurant_food_name);
        restaurant_food_cover = convertView.findViewById(R.id.restaurant_food_cover);


        String money = Utils.currency(item.getPrice());
        restaurant_food_price.setText(money);
        restaurant_food_des.setText(item.getDescription());
        restaurant_food_name.setText(item.getName());

        Utils.UI.setSrc(item.getImage(), restaurant_food_cover);

        return convertView;
    }
}
