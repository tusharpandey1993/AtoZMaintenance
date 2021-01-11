package com.base.AtoZMaintenanceApp.CustomViewsFiles;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.base.AtoZMaintenanceApp.R;

public class CustomListViewDialog  extends Dialog implements View.OnClickListener {

    private Context activity;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter adapter;


    public CustomListViewDialog(Context a, RecyclerView.Adapter adapter) {
        super(a);
        this.activity = a;
        this.adapter = adapter;
        setupLayout();
    }

    private void setupLayout() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.report_child_dialog);

        recyclerView = findViewById(R.id.report_child_recycle);
        mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        dismiss();
    }
}