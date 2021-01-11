package com.base.AtoZMaintenanceApp.Adapter;

import android.view.View;

public interface OnClickInterface {

    default void   onClick(View view, int position){}

    default void   onClick(View view, int position,String selectedValue,String SelectedID,String type){}

    default void   onClickQuiz(View view, int position, int id, int assismentID,String optionID, int assessmentQuestionId){}

    default void   onClickReport(View view, int viewName, int UnitId){}

    default void   onClickReportWithEmp(View view, int viewName, int UnitId, int employeeId){}

//    void  onClickChildView(View view, String viewID, int position);

//    void  onAppsStoreChildViewClicked(View view, String viewID, int position, int AppID);


}
