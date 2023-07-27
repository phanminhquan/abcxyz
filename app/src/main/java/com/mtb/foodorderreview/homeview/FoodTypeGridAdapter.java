package com.mtb.foodorderreview.homeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtb.foodorderreview.R;

import java.util.List;

public class FoodTypeGridAdapter extends BaseAdapter {

    private final List<FoodType> list;
    private final LayoutInflater inflater;

    public FoodTypeGridAdapter(Context context, List<FoodType> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public FoodType getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_home_food_type, null);

        TextView text = convertView.findViewById(R.id.home_food_type_grid_layout_text_1);
        text.setText(list.get(position).getName());

        ImageView img = convertView.findViewById(R.id.home_food_type_grid_layout_img_1);
        img.setImageResource(list.get(position).getImg());

        return convertView;
    }
}
