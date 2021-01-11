package com.base.AtoZMaintenanceApp.CommonFiles;

import android.content.Context;

public interface onDialogClickCallback {


    void  onLeftClick(Context context, String text, Integer FunctionNum);

    void onRightClick(Context context, String text, Integer FunctionNum);

    void onOkClick(Context context, String text, Integer FunctionNum);

}
