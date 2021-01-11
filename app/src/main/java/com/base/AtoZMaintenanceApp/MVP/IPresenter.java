package com.base.AtoZMaintenanceApp.MVP;

import com.base.AtoZMaintenanceApp.api.Response.getLoginRes;

public interface IPresenter {


    default void showProgressDialog(){}
    default void hideProgressDialog(){}
    default void onError(String error){}
    default void onError(String error,int code){}
    default void onError(Object error){}
    default void onError(Object error,int Code){}
    default void onResponse(getLoginRes loginResponse){}


}
