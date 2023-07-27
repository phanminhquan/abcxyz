package com.mtb.foodorderreview.restaurentview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mtb.foodorderreview.R;

import java.util.List;

public class RestaurantUserRateListViewAdapter extends BaseAdapter {
    private final List<RestaurantUserRate> list;
    private final LayoutInflater inflater;

    public RestaurantUserRateListViewAdapter(Context context, List<RestaurantUserRate> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public RestaurantUserRate getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_restaurant_rating, null);
        RestaurantUserRate item = list.get(position);

        TextView adapter_restaurant_rating_name_text,
                adapter_restaurant_rating_star_count_text,
                adapter_restaurant_rating_description_text;

        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        adapter_restaurant_rating_name_text = convertView.findViewById(R.id.adapter_restaurant_rating_name_text);
        adapter_restaurant_rating_star_count_text = convertView.findViewById(R.id.adapter_restaurant_rating_star_count_text);
        adapter_restaurant_rating_description_text = convertView.findViewById(R.id.adapter_restaurant_rating_description_text);
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        adapter_restaurant_rating_name_text.setText(item.getName());
        adapter_restaurant_rating_star_count_text.setText(String.valueOf(item.getStarCount()));
        adapter_restaurant_rating_description_text.setText(item.getUserNote());

        return convertView;
    }
}