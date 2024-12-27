package com.rosuliman.projectta_ideplot.database;

import com.rosuliman.projectta_ideplot.database.tabel.Plot;
import com.rosuliman.projectta_ideplot.database.tabel.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    // Endpoint untuk daftar pada mysql
    @POST("register")
    Call<ApiResponse> register(@Body User user);

    // Endpoint untuk login pada mysql
    @POST("login")
    Call<ApiResponse> login(@Body User user);

    // Endpoint untuk mendapatkan data plot
    @GET("getPlot")
    Call<ApiResponseList> getPlot(@Query("userId") int userId); // Menggunakan sintaks Java
}
