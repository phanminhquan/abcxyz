package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.DonHang;
import com.mtb.foodorderreview.utils.Utils;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DonHangService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();

    DonHangService apiService = new Retrofit.Builder()
            .baseUrl(Utils.ip)
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(DonHangService.class);

    @GET("/donhang")
    Call<List<DonHang>> getListDH();

    @POST("/donhang")
    Call<DonHang> postDH(@Body DonHang donHang);

    @PATCH("/donhang/{id}")
    Call<DonHang> updateST(@Path("id") int id, @Body Map<String, Object> map);

    @GET("/donhang/user/{id}")
    Call<List<DonHang>> getListDonhangByIDUser(@Path("id") int id);

}
