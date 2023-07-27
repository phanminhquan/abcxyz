package com.mtb.foodorderreview.callapi;

import com.mtb.foodorderreview.api.NhaHangService;
import com.mtb.foodorderreview.global.RestaurantListGlobal;
import com.mtb.foodorderreview.homeview.Restaurant;
import com.mtb.foodorderreview.model.NhaHang;
import com.mtb.foodorderreview.utils.ICallback;
import com.mtb.foodorderreview.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallNhaHang {
    private ICallback callback;

    public void getlist(int id) {
        List<Restaurant> l = new ArrayList<>();
        NhaHangService.apiService.getListNHbyLoaiFood(id).enqueue(new Callback<List<com.mtb.foodorderreview.model.NhaHang>>() {
            @Override
            public void onResponse(Call<List<com.mtb.foodorderreview.model.NhaHang>> call, Response<List<com.mtb.foodorderreview.model.NhaHang>> response) {
                List<NhaHang> nh = response.body();
                for (NhaHang n : nh) {
                    l.add(new Restaurant(n.getId(), n.getTen(), Utils.URL_SAMPLE_IMAGE, n.getDiaChi()));
                }

                RestaurantListGlobal.getInstance().setList(l);

                callback.callback();
            }

            @Override
            public void onFailure(Call<List<com.mtb.foodorderreview.model.NhaHang>> call, Throwable t) {

            }
        });
    }

    public void getlist() {
        List<Restaurant> l = new ArrayList<>();

        NhaHangService.apiService.getListNH().enqueue(new Callback<List<NhaHang>>() {
            @Override
            public void onResponse(Call<List<NhaHang>> call, Response<List<NhaHang>> response) {
                List<NhaHang> nh = response.body();
                for (NhaHang n : nh) {
                    l.add(new Restaurant(n.getId(), n.getTen(), Utils.URL_SAMPLE_IMAGE, n.getDiaChi()));
                }
                RestaurantListGlobal.getInstance().setList(l);

                callback.callback();
            }

            @Override
            public void onFailure(Call<List<com.mtb.foodorderreview.model.NhaHang>> call, Throwable t) {

            }
        });
    }

    public void setCallback(ICallback callback) {
        this.callback = callback;
    }
}
