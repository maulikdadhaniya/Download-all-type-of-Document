package com.example.downloadexample.retrofit;

import com.example.downloadexample.response.MyResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("get_homework.php")
    Call<MyResponse> addRequest(@Field("student_id") int userId);
}
