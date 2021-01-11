package com.base.AtoZMaintenanceApp.MVP;

import android.content.Context;
import android.util.Log;

import com.base.AtoZMaintenanceApp.CommonFiles.Utility;
import com.base.AtoZMaintenanceApp.api.Request.LoginRequest;
import com.base.AtoZMaintenanceApp.api.Response.getLoginRes;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModel {

    private final Context mContext;
    private final String TAG = "ViewModel";
    private final IPresenter iPresenter;

    public ViewModel(Context context, IPresenter iPresenter) {
        this.mContext = context;
        this.iPresenter = iPresenter;
    }

    // Login
    public void callLoginApi(LoginRequest request) {
        Utility.getInstance().createServiceForTrigApp(mContext)
                .callLogin(request)
                .enqueue(new Callback<getLoginRes>() {
                    @Override
                    public void onResponse(Call<getLoginRes> call,
                                           Response<getLoginRes> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null) {
                                iPresenter.onResponse(response.body());
                                Log.e(TAG, "onResponse:login " + Utility.getInstance().getG().toJson(response.body()));
                            }
                        } else {
                            if (response.errorBody() != null) {
                                iPresenter.onError(response.errorBody());
                                Log.e(TAG, "onerror:login " + Utility.getInstance().getG().toJson(response.errorBody()));
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<getLoginRes> call, Throwable t) {
                        iPresenter.onError(t.getCause());
                        Log.e(TAG, "onFailure: callLoginApi" + t.getCause());
                    }
                });
    }


}
