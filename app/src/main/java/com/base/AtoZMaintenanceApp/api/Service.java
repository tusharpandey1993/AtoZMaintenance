package com.base.AtoZMaintenanceApp.api;

import com.base.AtoZMaintenanceApp.api.Request.LoginRequest;
import com.base.AtoZMaintenanceApp.api.Response.getLoginRes;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Service {


    @POST("login/login")
    Call<getLoginRes> callLogin(@Body LoginRequest loginRequest);


   }