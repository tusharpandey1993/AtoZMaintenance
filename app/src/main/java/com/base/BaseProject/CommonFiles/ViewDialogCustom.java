package com.base.BaseProject.CommonFiles;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import androidx.fragment.app.DialogFragment;

import com.base.BaseProject.R;

public class ViewDialogCustom extends DialogFragment {


    //    Cancel and Action 2 BUTTON
    public static void customDialog2Btn(final Activity activity, String msg, String btnLeftMsg, String btnRightMsg, final Integer FunctionNum, final onDialogClickCallback onDialogClickCallback) {

        final Dialog dialog = new Dialog(activity, R.style.DialogTheme);
        int layout_parms;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layout_parms = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layout_parms = WindowManager.LayoutParams.TYPE_PHONE;
        }
        new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                layout_parms,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_two_btn);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(R.color.dialog_bg)));
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        dialog.show();
    }

}
