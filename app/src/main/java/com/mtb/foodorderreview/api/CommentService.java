package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.DanhGia;
import com.mtb.foodorderreview.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CommentService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();

    CommentService apiService = new Retrofit.Builder()
            .baseUrl(Utils.ip)
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(CommentService.class);

    @POST("/danh_gia")
    Call<DanhGia> createComment(@Body DanhGia danhGia);

    @GET("/danh_gia/{id}")
    Call<List<DanhGia>> getListDanhGia(@Path("id") int id);
}
