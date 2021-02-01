package com.base.AtoZMaintenanceApp.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.airbnb.lottie.LottieAnimationView;
import com.base.AtoZMaintenanceApp.CommonFiles.Constants;
import com.base.AtoZMaintenanceApp.CommonFiles.AppPreferences;
import com.base.AtoZMaintenanceApp.R;

public class SuccessFragment extends Fragment {

    private static final String TAG = "FeedbackFragment";

    private View mView;
    private FragmentActivity mActivity;
    private TextView toolBarText, SuccessTitle;
    private final int ANIMATE_DURATION = 1500;
    private LottieAnimationView lottieAnimationView;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    public SuccessFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_success, container, false);
        init(mView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                proceed();
            }
        },ANIMATE_DURATION);

        return mView;
    }

    @SuppressLint("SetTextI18n")
    private void init(View mView) {
        lottieAnimationView = mView.findViewById(R.id.lottieAnimationView);
        SuccessTitle = mView.findViewById(R.id.SuccessTitle);

        if(AppPreferences.getSource_To_Desitnation(mActivity) == Constants.getInstance().loginScreen) {
            SuccessTitle.setText("Login Success");
        } else {
            SuccessTitle.setText("Success");
        }

        lottieAnimationView.setAnimation("done_tick.json");
        lottieAnimationView.playAnimation();
    }

    public void proceed() {
        if(AppPreferences.getSource_To_Desitnation(mActivity) == Constants.getInstance().loginScreen) {

            if (AppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().user)) {

                moveToDashboard();
            } else if (AppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().trainer)) {
                /*Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_fragment_success_to_dashboardTrainerFragment);*/
            }

        } else if(AppPreferences.getSource_To_Desitnation(mActivity) == Constants.getInstance().feedback) {

            if (AppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().user)) {
                moveToDashboard();
            } else if (AppPreferences.getUser_Type(mActivity).equalsIgnoreCase(Constants.getInstance().trainer)) {
               /* Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_fragment_success_to_dashboardTrainerFragment);*/
            }

        } else {

            moveToDashboard();
        }
    }

    private void moveToDashboard() {
        AppPreferences.setLoginPref(mActivity, true);
        Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                .navigate(R.id.action_fragment_success_to_dashboardFragment);
    }
}
