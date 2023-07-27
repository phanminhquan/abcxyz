package com.mtb.foodorderreview.homeview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mtb.foodorderreview.R;
import com.mtb.foodorderreview.something.Order;
import com.mtb.foodorderreview.utils.Utils;

import java.util.List;

public class OrderListViewAdapter extends BaseAdapter {
    private final List<Order> list;
    private final LayoutInflater inflater;

    public OrderListViewAdapter(Context context, List<Order> list) {
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Order getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.adapter_oder, null);
        Order item = list.get(position);

        ImageView adapter_order_image;
        TextView adapter_order_state_text,
                adapter_order_date_text,
                adapter_order_name_text,
                adapter_order_price_text,
                adapter_order_quantity_text;
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        adapter_order_image = convertView.findViewById(R.id.adapter_order_image);
        adapter_order_state_text = convertView.findViewById(R.id.adapter_order_state_text);
        adapter_order_date_text = convertView.findViewById(R.id.adapter_order_date_text);
        adapter_order_name_text = convertView.findViewById(R.id.adapter_order_name_text);
        adapter_order_price_text = convertView.findViewById(R.id.adapter_order_price_text);
        adapter_order_quantity_text = convertView.findViewById(R.id.adapter_order_quantity_text);
        // -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
        Utils.UI.setSrc(item.getRestaurant().getImage(), adapter_order_image);
        adapter_order_state_text.setText(item.getStateStr());
        adapter_order_date_text.setText(Utils.dateStr(item.getCreatedAt()));
        adapter_order_name_text.setText(item.getRestaurant().getName());
        adapter_order_price_text.setText(String.valueOf(item.getFinalPrice()));
        adapter_order_quantity_text.setText(String.valueOf(item.getCartFood().size()));

        return convertView;
    }
}