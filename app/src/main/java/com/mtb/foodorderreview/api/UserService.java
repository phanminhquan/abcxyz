package com.mtb.foodorderreview.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mtb.foodorderreview.model.Login;
import com.mtb.foodorderreview.model.Message;
import com.mtb.foodorderreview.model.User;
import com.mtb.foodorderreview.utils.Utils;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH-mm-ss").create();
    UserService apiService = new Retrofit.Builder()
            .baseUrl(Utils.ip)
            .addConverterFactory(GsonConverterFactory.create(gson)).build().create(UserService.class);

    @POST("/register")
    Call<Message> register(@Body User user);

    @POST("/login")
    Call<User> login(@Body Login login);

    @PATCH("user/{id}")
    Call<User> update(@Path("id") int id, @Body Map<String, Object> map);
}
