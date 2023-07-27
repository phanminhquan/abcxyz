package com.mtb.foodorderreview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mtb.foodorderreview.callapi.CallNhaHang;
import com.mtb.foodorderreview.global.RestaurantGlobal;
import com.mtb.foodorderreview.global.RestaurantListGlobal;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.homeview.RestaurantListViewAdapter;
import com.mtb.foodorderreview.utils.Utils;

import java.util.List;

public class RestaurantListActivity extends AppCompatActivity {
    CallNhaHang callNhaHang = new CallNhaHang();
    ListView restaurant_list_listview;
    TextView restaurant_list_header_text;
    LinearLayout restaurant_list_back_btn;

    RestaurantListGlobal restaurantListGlobal = RestaurantListGlobal.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);

        initialization();

        Utils.UI.backBtn(this, restaurant_list_back_btn);
        setRestaurantListV();
    }

    private void initialization() {
        restaurant_list_listview = findViewById(R.id.restaurant_list_listview);
        restaurant_list_header_text = findViewById(R.id.restaurant_list_header_text);
        restaurant_list_back_btn = findViewById(R.id.restaurant_list_back_btn);

        Intent intent = getIntent();
        String title = intent.getStringExtra("TITLE");

        if (title != null) {
            String originalTitle = restaurant_list_header_text.getText().toString();
            String newTitle = originalTitle + " - " + title;
            restaurant_list_header_text.setText(newTitle);
        }
    }

    private void setRestaurantListV() {
        List<Restaurant> l = RestaurantListGlobal.getInstance().getList();
        RestaurantListViewAdapter adapter = new RestaurantListViewAdapter(this, l);
//        NhaHangService.apiService.getListNH().enqueue(new Callback<List<NhaHang>>() {
//            @Override
//            public void onResponse(Call<List<NhaHang>> call, Response<List<NhaHang>> response) {
//                List<NhaHang> nh = response.body();
//                for (NhaHang n : nh) {
//                    l.add(new Restaurant(n.getId(), n.getTen(), Utils.URL_SAMPLE_IMAGE, n.getDiaChi()));
//                }
//                adapter.notifyDataSetChanged();
//
//            }
//
//            @Override
//            public void onFailure(Call<List<com.mtb.foodorderreview.model.NhaHang>> call, Throwable t) {
//
//            }
//        });


        restaurant_list_listview.setAdapter(adapter);

        restaurant_list_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Restaurant f = (Restaurant) restaurant_list_listview.getItemAtPosition(position);
                RestaurantGlobal.getInstance().setRestaurant(f);

                Intent intent = new Intent(RestaurantListActivity.this, RestaurantActivity.class);
                startActivity(intent);
            }
        });
    }


}