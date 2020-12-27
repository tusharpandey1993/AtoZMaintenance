package com.base.BaseProject.api;

import com.google.gson.JsonArray;
import com.base.BaseProject.api.Request.LoginRequest;
import com.base.BaseProject.api.Response.getLoginRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {


    @POST("login/login")
    Call<getLoginRes> callLogin(@Body LoginRequest loginRequest);


   }