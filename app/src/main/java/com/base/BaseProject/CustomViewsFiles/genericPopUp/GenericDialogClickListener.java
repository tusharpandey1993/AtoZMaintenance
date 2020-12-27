package com.base.BaseProject.CustomViewsFiles.genericPopUp;

import android.view.View;

public interface GenericDialogClickListener {

    /**
     *
     * right side button i.e Confirm
     * @param view Clicked view
     *
     */
    default void  onPositiveButtonClick(View view, int FucntionNumber){}
    /**
     *
     * right side button i.e Confirm with data
     * @param view Clicked view
     *
     */


    /**
     *
     * left side button i.e Cancel
     * @param view clicked view
     *
     */
    default void  onNegativeButtonClick(View view, int FucntionNumber){}

    /**
     *
     * top right corner close clicked
     * @param view clicked view
     *
     */
    default void  onDialogCloseButtonClick(View view, int FucntionNumber){}

}

