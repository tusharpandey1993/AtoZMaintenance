package com.base.AtoZMaintenanceApp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.base.AtoZMaintenanceApp.CommonFiles.Constants;
import com.base.AtoZMaintenanceApp.CommonFiles.AppPreferences;
import com.base.AtoZMaintenanceApp.CommonFiles.Utility;
import com.base.AtoZMaintenanceApp.R;
import com.base.AtoZMaintenanceApp.onBoarding.OnboardingDialogFragment;

public class SplashFragment extends Fragment {

    private static final String TAG = "SplashFragment";
    private View mView;
    private FragmentActivity mActivity;
    public OnboardingDialogFragment onboardingDialogFragment;

    private final int ANIMATE_DURATION = 1000;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.splash_activty, container, false);

        if(Constants.getInstance().ShowSuccessFlag) {
            Log.d(TAG, "onCreateView1: " + Constants.getInstance().ShowSuccessFlag);
            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                    .navigate(R.id.action_splashFragment_to_successFragment);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(AppPreferences.getIsShowedShowcaseAppStore(mActivity)) {
                        if (AppPreferences.isLoggedIn(mActivity)) {
                            Log.d(TAG, "run: true ");
                            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                                    .navigate(R.id.action_splashFragment_to_dashboardFragment);
                        } else {
                            Log.d(TAG, "run: false");
                            Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                                    .navigate(R.id.action_splashFragment_to_loginFragment);
                        }
                    }else {
                        showOnBoarding();
                    }

                }
            },ANIMATE_DURATION);
        }


        return mView;

    }

    public void showOnBoarding() {
        try {
            if (Utility.getInstance().isAppOnForeground(mActivity, mActivity.getPackageName())) {
                onboardingDialogFragment = new OnboardingDialogFragment();
                onboardingDialogFragment.show(mActivity.getSupportFragmentManager(), TAG);
                onboardingDialogFragment.setCancelable(false);
                onboardingDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.You_Dialog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
