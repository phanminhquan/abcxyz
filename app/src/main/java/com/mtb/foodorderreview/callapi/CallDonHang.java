package com.mtb.foodorderreview.callapi;

import com.mtb.foodorderreview.api.DonHangService;
import com.mtb.foodorderreview.model.DonHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallDonHang {
    public void getListDonHang(int id) {
        DonHangService.apiService.getListDonhangByIDUser(id).enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                List<DonHang> list = response.body();
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {

            }
        });
    }
}
