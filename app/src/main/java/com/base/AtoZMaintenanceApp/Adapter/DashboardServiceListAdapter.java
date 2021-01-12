package com.base.AtoZMaintenanceApp.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.base.AtoZMaintenanceApp.R;

public class DashboardServiceListAdapter extends RecyclerView.Adapter<DashboardServiceListAdapter.MyViewHolder> {


    private Context mActivity;
    private int userType;
    private OnClickInterface onClickListner;
    TypedArray ta;
    String [] ta2;

    public DashboardServiceListAdapter(Context context, OnClickInterface mListner) {
        this.mActivity= context;
        this.userType= userType;
        this.onClickListner = mListner;

        ta = mActivity.getResources().obtainTypedArray(R.array.homeIcons);
        ta2 =  mActivity.getResources().getStringArray(R.array.homeItems);
    }


    @Override
    public DashboardServiceListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_list_item, parent, false);
        DashboardServiceListAdapter.MyViewHolder vh = new DashboardServiceListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DashboardServiceListAdapter.MyViewHolder holder, final int position) {

        try {
                holder.item_icon.setBackgroundResource(ta.getResourceId(position, 0));
                holder.title.setText(ta2[position]);
        } catch (Exception e){
            Log.e("TAG", "onBindViewHolder: " + e.getMessage() );
        }

    }

    @Override
    public int getItemCount() {
          return 6;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public View view;
        public ImageView item_icon;
        private TextView title;
        public MyViewHolder(View v) {
            super(v);
            try {
                item_icon = v.findViewById(R.id.item_icon);
                title = v.findViewById(R.id.title);
            } catch (Exception e){
                Log.e("TAG", "MyViewHolder: " + e.getMessage() );
            }
        }
    }

}
