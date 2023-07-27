package com.mtb.foodorderreview.homeview;

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

public class RestaurantListViewAdapter extends BaseAdapter {
    private final List<Restaurant> list;
    private final LayoutInflater inflater;

    public RestaurantListViewAdapter(Context context, List<Restaurant> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Restaurant getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_restaurant_vertical, null);
        Restaurant item = list.get(position);
        ImageView adapter_restaurant_image;
        TextView adapter_restaurant_name_text;
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        adapter_restaurant_image = convertView.findViewById(R.id.adapter_restaurant_image);
        adapter_restaurant_name_text = convertView.findViewById(R.id.adapter_restaurant_name_text);
        //-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        Utils.UI.setSrc(item.getImage(), adapter_restaurant_image);
        adapter_restaurant_name_text.setText(String.valueOf(item.getName()));

        return convertView;
    }
}