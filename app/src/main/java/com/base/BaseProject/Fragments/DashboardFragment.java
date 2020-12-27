package com.base.BaseProject.Fragments;


import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.button.MaterialButton;
import com.base.BaseProject.Adapter.NavDrawerAdapter;
import com.base.BaseProject.Adapter.OnClickInterface;
import com.base.BaseProject.CommonFiles.Constants;
import com.base.BaseProject.CommonFiles.AppPreferences;
import com.base.BaseProject.CommonFiles.Utility;
import com.base.BaseProject.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.base.BaseProject.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.base.BaseProject.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.base.BaseProject.MVP.IPresenter;
import com.base.BaseProject.MVP.ViewModel;
import com.base.BaseProject.R;
import com.yarolegovich.slidingrootnav.SlideGravity;
import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;
import com.yarolegovich.slidingrootnav.callback.DragListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends BaseFragment implements GenericDialogClickListener, View.OnClickListener , OnClickInterface, DragListener, IPresenter {

    private static final String TAG = "DashboardFragment";
    FragmentActivity mActivity;

    private View mView;
    private ConstraintLayout courseContainer, assessmentContainer;
    private TextView toolBarText, feedback, userText, courseNumber, courseCompletedNumber, assessmentNumber, assementCompleted;
    private MaterialButton Category, initials;
    private RecyclerView list;
    private ViewModel viewModel;
    public static boolean fromCourses = false;
    public static boolean goToContactTrainer = false;
    private PieChart pieChartResult, pieChartCourses, pieChartAssessment;

    private ImageView closeIcon;

    private SlidingRootNav slidingRootNav;

    public DashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_dashboard, container, false);


        init(mView);

        return mView;
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        Log.e(TAG, "getLifecycle: " + super.getLifecycle().getCurrentState());
        return super.getLifecycle();
    }


    private void init(View mView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.courseContainer:

                break;
            case R.id.assessmentContainer:

                break;
            case R.id.feedback:

                break;
            case R.id.logout:

                break;

            case R.id.closeIcon:
                break;
        }
    }


    @Override
    public void onPositiveButtonClick(View view, int FucntionNumber) {

    }

    @Override
    public void onNegativeButtonClick(View view, int FucntionNumber) {

    }

    @Override
    public void onDialogCloseButtonClick(View view, int FucntionNumber) {

    }

    public final int POS_DASHBOARD = 0;
    public final int POS_COURCES = 1;
    public final int POS_ASSESSMENT = 2;
    public final int POS_PROFILE = 3;
    public final int POS_CONTACT_US = 4;
    public final int POS_CONTACT_TRAINER = 5;
    public final int POS_FEEDBACK = 6;
    public final int POS_LOGOUT = 7;

    @Override
    public void onClick(View view, int position) {
        try {

            switch (position) {
                case POS_DASHBOARD:
                    break;
                case POS_COURCES:
                    fromCourses = true;
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_topics);
                    break;
                case POS_ASSESSMENT:
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_topics);
                    break;
                case POS_PROFILE:
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_ProfileFragment);
                    break;
                case POS_CONTACT_US:
                    goToContactTrainer = true;
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_Contact);
                    break;
                case POS_CONTACT_TRAINER:
                    goToContactTrainer = false;
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_Contact);
                    break;
                case POS_FEEDBACK:
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_dashboardFrag_to_FeedbackFragment);
                    break;
                case POS_LOGOUT:
                    AppPreferences.setLoginPref(mActivity, false);
                    GenericDialogPopup genericDialogPopup = null;
                    GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                            .setShowCloseButton(false)
                            .setHeading(mActivity.getResources().getString(R.string.logoutHeading))
                            .setDescription(mActivity.getResources().getString(R.string.appLogout))
                            .setPositiveButtonText(Constants.LOGOUT)
                            .setNegativeButtonText(Constants.Cancel)
                            .setGenericDialogClickListener(DashboardFragment.this)
                            .setFucntionNumber(Constants.getInstance().logout)
                            .build();
                    Utility.getInstance().showDynamicDialog(mActivity, genericDialogBuilder, genericDialogPopup, mActivity.getSupportFragmentManager());
                    break;
            }
            if (slidingRootNav != null) {
                slidingRootNav.closeMenu();
            }
        } catch (Exception e) {
            Log.e(TAG, "onClick: exception" + e.getMessage());
        }
    }

    @Override
    public void onDrag(float progress) {
        Fragment navHostFragment = mActivity.getSupportFragmentManager().findFragmentById(R.id.navHostFragment);
        Fragment fragment = navHostFragment.getChildFragmentManager().getFragments().get(0);
        if (fragment != null && !(fragment instanceof DashboardFragment)) {
           /* slidingRootNav = new SlidingRootNavBuilder(mActivity)
                    .withMenuOpened(false)
                    .addDragListener(this)
                    .withGravity(SlideGravity.LEFT)
                    .withContentClickableWhenMenuOpened(true)
                    .withMenuLayout(R.layout.menu_left_drawer)
                    .withMenuLocked(true)
                    .inject();*/
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showProgressDialog() {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void hideProgressDialog() {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(String error) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(String error, int code) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(Object error) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

    @Override
    public void onError(Object error, int Code) {
        try {
            hideLoader();
        } catch (Exception e) {
            Log.e(TAG, "onResponseProfile: " + e.getMessage());
        }
    }

}
