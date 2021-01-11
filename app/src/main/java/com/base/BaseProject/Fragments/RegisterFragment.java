package com.base.BaseProject.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentActivity;

import com.base.BaseProject.CommonFiles.AppPreferences;
import com.base.BaseProject.CommonFiles.Utility;
import com.base.BaseProject.CustomViewsFiles.genericPopUp.GenericDialogClickListener;
import com.base.BaseProject.MVP.IPresenter;
import com.base.BaseProject.MVP.ViewModel;
import com.base.BaseProject.R;

public class RegisterFragment extends BaseFragment implements IPresenter, GenericDialogClickListener {

    private static final String TAG = "RegisterFragment";
    private FragmentActivity mActivity;
    private View mView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        return mView;
    }
}
