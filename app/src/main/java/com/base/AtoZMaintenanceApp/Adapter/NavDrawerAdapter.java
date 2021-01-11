package com.base.AtoZMaintenanceApp.Adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.base.AtoZMaintenanceApp.R;

public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.NavHolder>  {

    View view;
    Context context;
    private String[] screenTitles;
    private Drawable[] screenIcons;
    OnClickInterface onClickListner;
    private int selectedPosition = 0;
    private int userType = 0;

    public NavDrawerAdapter(Context context, OnClickInterface mListner,int userType) {
        this.context= context;
        this.userType= userType;
        if(userType == 0){
            this.screenIcons = loadTrainerScreenIcons();
            this.screenTitles = loadTrainerScreenTitles();
        }else{
            this.screenIcons = loadScreenIcons();
            this.screenTitles = loadScreenTitles();
        }

        this.onClickListner = mListner;
    }

    @NonNull
    @Override
    public NavDrawerAdapter.NavHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nav_child_layout, viewGroup, false);
        return new NavHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull NavDrawerAdapter.NavHolder holder, final int position) {

        if(selectedPosition == position){
            holder.id_value.setTextColor(context.getResources().getColor(R.color.colorAccent));
        }else{
            holder.id_value.setTextColor(context.getResources().getColor(R.color.gray));
        }
        holder.id_value.setText(screenTitles[position]);
        holder.id_icon.setBackground(screenIcons[position]);

        holder.navChildLYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition >= 0) {
                    selectedPosition = position;
                    notifyDataSetChanged();
                }
                onClickListner.onClick(view, position);
            }
        });

    }



    @Override
    public int getItemCount() {
        return screenTitles.length;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    protected class NavHolder extends RecyclerView.ViewHolder  {

        TextView id_value;
        ImageView id_icon;
        LinearLayout navChildLYT;

        public NavHolder(View itemView) {
            super(itemView);
            id_icon = itemView.findViewById(R.id.id_icon);
            id_value = itemView.findViewById(R.id.id_value);
            navChildLYT = itemView.findViewById(R.id.navChildLYT);
        }


    }

    private String[] loadScreenTitles() {
        return context.getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    private Drawable[] loadScreenIcons() {
        TypedArray ta = context.getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(context, id);
            }
        }
        ta.recycle();
        return icons;
    }

    private String[] loadTrainerScreenTitles() {
        return context.getResources().getStringArray(R.array.ld_TrainerScreenTitles);
    }

    private Drawable[] loadTrainerScreenIcons() {
        TypedArray ta = context.getResources().obtainTypedArray(R.array.ld_TrainerScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(context, id);
            }
        }
        ta.recycle();
        return icons;
    }

}