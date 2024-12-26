package com.rosuliman.projectta_ideplot.database;

import com.rosuliman.projectta_ideplot.database.tabel.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    // Endpoint untuk daftar pada mysql
    @POST("register")
    Call<ApiRespone> register(@Body User user);

    // Endpoint untuk login pada mysql
    @POST("login")
    Call<ApiRespone> login(@Body User user);

}

