package com.base.BaseProject.onBoarding;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.viewpager.widget.PagerAdapter;


import com.base.BaseProject.CustomViewsFiles.CircleImageView;
import com.base.BaseProject.R;

import java.util.ArrayList;

public class OnBoardingPagerAdapter extends PagerAdapter {
    private Context mContext;
    private ArrayList<OnBoarding> dataArrayList;

    public OnBoardingPagerAdapter(Context context, ArrayList<OnBoarding> dataArrayList) {
        mContext = context;
        if (dataArrayList == null) {
            dataArrayList = new ArrayList<>();
        }
        this.dataArrayList = dataArrayList;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.list_item_onboarding, collection, false);

        try {
            CircleImageView iv_onboarding = layout.findViewById(R.id.iv_onboarding);
            AppCompatTextView tv_onboarding_title = layout.findViewById(R.id.tv_onboarding_title);
            AppCompatTextView tv_onboarding_desc = layout.findViewById(R.id.tv_onboarding_desc);

            OnBoarding onBoarding = dataArrayList.get(position);

            iv_onboarding.setImageResource(onBoarding.getImageRes());
            tv_onboarding_title.setText(onBoarding.getTitle());
            tv_onboarding_desc.setText(onBoarding.getDescription());
        } catch (Exception e) {
            e.printStackTrace();
        }
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return dataArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

}
