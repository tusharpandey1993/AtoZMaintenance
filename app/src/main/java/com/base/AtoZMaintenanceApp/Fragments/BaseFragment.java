package com.base.AtoZMaintenanceApp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.base.AtoZMaintenanceApp.CommonFiles.NoInternetDialog;
import com.base.AtoZMaintenanceApp.CommonFiles.Utility;
import com.base.AtoZMaintenanceApp.CustomViewsFiles.LoaderFragment;
import com.base.AtoZMaintenanceApp.CustomViewsFiles.genericPopUp.GenericDialogBuilder;
import com.base.AtoZMaintenanceApp.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.base.AtoZMaintenanceApp.CustomViewsFiles.genericPopUp.GenericDialogPopup;
import com.base.AtoZMaintenanceApp.R;


public class BaseFragment  extends Fragment implements GenericDialogClickListener {

    protected final String TAG = getClass().getSimpleName();
    protected View mContent;
    private FragmentActivity mActivity;
    private ProgressDialog mProgressDialog;
    private LoaderFragment loaderFragment;
    public long mLastClickTime = 0;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

        } catch (Exception e) {
            Log.e(TAG, "  error " + e.getMessage());
        }
    }



    @Override
    public void onStop() {
        try {
            // getActivity().unregisterReceiver(mReceiver);
        } catch (Exception e) {
        }
        super.onStop();
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContent = view;
    }

    @Override
    public void onPause() {
        super.onPause();
        hideLoader();
    }


    protected void showProgressBar(String message) {
        if (isAdded()) {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(mActivity);
                mProgressDialog.setCancelable(false);
            }
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /**
         * We release the broadcast receiver service here to avoid memory leak and crashes
         */
        try {

        } catch (Exception e) {
            Log.e(TAG, " unregisterReceiver error " + e.getMessage() );
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    public void showLoader() {
        try {
            if (Utility.getInstance().isAppOnForeground(mActivity, mActivity.getPackageName())) {
                loaderFragment = new LoaderFragment();
                loaderFragment.show(mActivity.getSupportFragmentManager(), TAG);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public boolean isLoading() {
        try {
            if (Utility.getInstance().isAppOnForeground(mActivity, mActivity.getPackageName()) && loaderFragment != null) {
                return loaderFragment.getDialog().isShowing();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void hideLoader() {
        try {
                if (Utility.getInstance().isAppOnForeground(mActivity, mActivity.getPackageName())) {
                    if (loaderFragment != null) {
                        loaderFragment.dismiss();
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Override
    public void onResume() {
        super.onResume();
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        /*inflater.inflate(R.menu.menu_region, menu);
        final MenuItem menuItem = menu.findItem(R.id.menu_id_help);
        View actionView = menuItem.getActionView();

        actionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                    return;
                }
                mLastClickTime = SystemClock.elapsedRealtime();
                onOptionsItemSelected(menuItem);
            }
        });*/
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        try {
            switch (item.getItemId()) {

            }
        } catch (Exception e) {
            Log.e(TAG, "onOptionsItemSelected: error "+ e.getMessage());
        }
        return true;
    }

    public void ShowGenericDialog(int FunctionNum,String ButtonText,String DialogMessage, String dialogHeading){
        GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                .setShowCloseButton(false)
                .setHeading(dialogHeading.isEmpty()?mActivity.getResources().getString(R.string.DialogHeading): dialogHeading)
                .setDescription(DialogMessage)
                .setPositiveButtonText(ButtonText)
                .setGenericDialogClickListener(this)
                .setFucntionNumber(FunctionNum)
                .build();
        showDynamicDialog(genericDialogBuilder);
    }


    public void showDynamicDialog(GenericDialogBuilder genericDialogBuilder) {
        GenericDialogPopup genericDialogPopup = new GenericDialogPopup(genericDialogBuilder);
        genericDialogPopup.show(mActivity.getSupportFragmentManager(), TAG);
    }

    public void showNoInternet() {
        try {
            if (Utility.getInstance().isAppOnForeground(mActivity, mActivity.getPackageName())) {
                NoInternetDialog onboardingDialogFragment = new NoInternetDialog();
                onboardingDialogFragment.show(mActivity.getSupportFragmentManager(), TAG);
                onboardingDialogFragment.setCancelable(true);
                onboardingDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.You_Dialog);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
