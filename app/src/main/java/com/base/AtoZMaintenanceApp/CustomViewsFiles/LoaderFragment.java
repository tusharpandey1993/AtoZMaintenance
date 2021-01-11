package com.base.AtoZMaintenanceApp.CustomViewsFiles;

import androidx.fragment.app.FragmentTransaction;
import android.graphics.drawable.ColorDrawable;
import com.airbnb.lottie.LottieAnimationView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.graphics.Color;
import android.view.ViewGroup;
import android.view.Window;
import android.app.Dialog;
import com.base.AtoZMaintenanceApp.R;
import android.os.Bundle;
import android.view.View;
import android.util.Log;

public class LoaderFragment extends DialogFragment {

    private View mView;
    private LottieAnimationView animationView;
    private MutableLiveData<String> mutableLiveData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_loader, container, false);
        setCancelable(false);
        animationView = mView.findViewById(R.id.gifConnectingOnOpponent);
        animationView.setAnimation("loader_three.json");
        if (!animationView.isAnimating()) {
            animationView.playAnimation();
            animationView.loop(true);

        }
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set click listener
    }

    @NonNull
    @Override
    // To remove the titlebar in dialogfragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(this, tag);
            ft.commitAllowingStateLoss();
        } catch (IllegalStateException e) {
            Log.d("ABSDIALOGFRAG", "Exception", e);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

    }

}
