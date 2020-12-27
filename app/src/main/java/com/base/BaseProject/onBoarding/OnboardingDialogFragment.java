package com.base.BaseProject.onBoarding;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;


import com.base.BaseProject.CommonFiles.AppPreferences;
import com.base.BaseProject.R;

import java.util.ArrayList;

public class OnboardingDialogFragment extends DialogFragment {

    private View mView;
    private TextView tv_next;
    private TextView tv_skip;
    private ViewPager view_pager_onboarding;
    private Activity mActivity;
    private static final String TAG = "OnboardingDialogFragmen";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.activity_onboarding, container, false);

        initView(mView);
        bindEvents();
        setData();

        return mView;
    }

    private void setData() {
        ArrayList<OnBoarding> onBoardingArrayList = new ArrayList<>();

        onBoardingArrayList.add(new OnBoarding(R.drawable.ic_age, "TRIG GROUP", "T Trusted R Resourceful I Integrated G Guardforce \nTRIG is one of India's leading professionally managed Watch & Ward Security Solutions Provider with a holistic range of specialized services to offer to its clients. With its 360 DEGREE  SECURITY SYSTEMS APPROACH, TRIG Group not only specializes in offering Man Power Security Solutions to Corporates and Residential Spaces, but have also established a niche in offering state-of-the-art Electronic Security Solutions to meet the growing need of Digital Surveillance Systems.\n" +
                "Since its inception in 1980, TRIG Group has defined its priority in offering error-free and vigilant security surveillance to exceed customer satisfaction. As on this day, TRIG Group has enrolled over 15,000 security professionals, 3,000 chauffeurs and a dedicated team of Trained Managers and Officers to seamlessly manage its 26 branches across India."));

        onBoardingArrayList.add(new OnBoarding(R.drawable.founder, "Mr. Swaran Salaria\n" +
                "(FOUNDER)", "Having started this company with a single service contract of 10 Security Personnel, it gives me immense pride that my team and I have come a long way and achieved several milestones. Today, we at TRIG Group pride over our uncompromising quality and exceptional attention to detail to ensure 100% Customer Satisfaction.\n" +
                "TRIG Group relentlessly works towards offering comprehensive and cost-effective security solutions to a variety of clients from various industries throughout India.\n" +
                "Now, TRIG Group has grown across various horizons of Security Industry and specializes in offering cutting edge watch and ward & digital surveillance systems, be it for corporates and residential spaces or for large public gatherings across India."));

        onBoardingArrayList.add(new OnBoarding(R.drawable.director_new, "Mr. Shammee Salaria\n" +
                "(Managing Director)", "Being from the New Generation and with High Ambitions of Growing our Group into various areas, Shammee brings on the table innovative ideas and competitive working environment which is extremely important to nurture the VALUES and PRINCIPLES on which our company was established. Having learnt the hard way by experiencing the Operations Activities of the company, today, he is delighted to have developed an Excellent Team of Energetic Professionals who support each other in achieving our Company’s VISION, & to implement ISO Standard’s."));

        OnBoardingPagerAdapter adapter = new OnBoardingPagerAdapter(mActivity, onBoardingArrayList);
        view_pager_onboarding.setAdapter(adapter);
    }

    private void bindEvents() {
        tv_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppPreferences.setIsShowedShowcaseAppStore(mActivity, true);
               Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                        .navigate(R.id.action_splashFragment_to_loginFragment);
                dismiss();
            }
        });
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (view_pager_onboarding.getCurrentItem() < 2) {
                    view_pager_onboarding.setCurrentItem(view_pager_onboarding.getCurrentItem() + 1);
                } else {
                    AppPreferences.setIsShowedShowcaseAppStore(mActivity, true);
                    Navigation.findNavController(requireActivity(), R.id.navHostFragment)
                            .navigate(R.id.action_splashFragment_to_loginFragment);
                    dismiss();
                }
            }
        });
        view_pager_onboarding.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i == 3) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                        tv_next.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.chip_view_shape_like));
                    }
                    tv_next.setText(getString(R.string.lets_started));
                    tv_next.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
                } else {
                    if (tv_next.getText() == getString(R.string.lets_started)) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            tv_next.setBackground(ContextCompat.getDrawable(mActivity, R.drawable.chipview_shape_like_stroke));
                        }
                        tv_next.setText(getString(R.string.next));
                        tv_next.setTextColor(ContextCompat.getColor(mActivity, R.color.bg_chipview_likes));
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView(View view) {
        tv_next = view.findViewById(R.id.tv_next);
        tv_skip = view.findViewById(R.id.tv_skip);
        view_pager_onboarding = view.findViewById(R.id.view_pager_onboarding);
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
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }



}