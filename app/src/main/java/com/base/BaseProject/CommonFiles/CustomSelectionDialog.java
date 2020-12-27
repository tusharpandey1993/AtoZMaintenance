package com.base.BaseProject.CommonFiles;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.BaseProject.Adapter.DialogItemClickListAdapter;
import com.base.BaseProject.Adapter.OnClickInterface;
import com.base.BaseProject.R;

import java.util.ArrayList;

public class CustomSelectionDialog extends Dialog implements
        android.view.View.OnClickListener {

    public Activity mActivity;
    public Dialog d;
    public TextView titledialog;
    public RecyclerView listdialog;
    EditText editTextSearch;
    OnClickInterface mListner;
    DataPayload payload;

    public CustomSelectionDialog(Activity activity,DataPayload payload, OnClickInterface mListner) {
        super(activity);
        this.mActivity = activity;
        this.payload = payload;
        this.mListner = mListner;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.selection_popup_mainview);
            titledialog = findViewById(R.id.titledialog);
            editTextSearch = findViewById(R.id.editTextSearch);
//            editTextSearch.setHint("Select "+payload.getTitle());
            listdialog = findViewById(R.id.listdialog);
//            titledialog.setText(payload.getTitle());


        } catch (Exception e){
            Log.e("TAG", "onCreate: " + e.getMessage() );
        }
    }


    @Override
    public void onClick(View v) {

    }


}
