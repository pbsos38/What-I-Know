package com.example.prince;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface setData {

    @GET("api/Home/GetSlider")
    Call<List<SliderBean>> GetSlider();
}
