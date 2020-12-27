package com.base.BaseProject.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.CountDownTimer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.BaseProject.CommonFiles.AppPreferences;
import com.base.BaseProject.CommonFiles.Utility;
import com.base.BaseProject.CustomViewsFiles.PinEntryEditText;
import com.base.BaseProject.R;

import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 */
public class OtpFragment extends Fragment {

    final Handler handler = new Handler();
    TextView timer_otp_detect, number_message,changeNumber,resentOtp;
    //Button resentOtp;
    private String EnteredOTP;
    PinEntryEditText txtPinEntry;
    int count = 4;
    boolean isResendOTP = false;
    View mView;
    TextView toolBarText;

    FragmentActivity mActivity;
    static final int RESEND_CODE_TIMER = 90 * 1000;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }

    public OtpFragment() {
        // Required empty public constructor
    }

    private CountDownTimer countDownTimer = new CountDownTimer(RESEND_CODE_TIMER, 1000) {

        @Override
        public void onTick(long millisUntilFinished) {
//            timer_otp_detect.setText("00:" + String.valueOf((int) (millisUntilFinished / 1000)));

            timer_otp_detect.setText("" + String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
        }

        @Override
        public void onFinish() {
            count--;
            timer_otp_detect.setText("00:00");
            enableResendOtp();
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_otp, container, false);
        init(mView);

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
//                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
//                        .navigate(R.id.action_otpFragment_to_LoginFragment);
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

        countDownTimer.start();
        /*disableResendOtp();

        changeNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_otpFragment_to_LoginFragment);

            }
        });*/

        number_message.setText(mActivity.getResources().getString(R.string.text_number_messsage, AppPreferences.getMobileNumber(mActivity)));

        txtPinEntry.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {


            @Override
            public void onPinEntered(CharSequence str) {

                EnteredOTP = str.toString();

                if (!EnteredOTP.equalsIgnoreCase("")) {
                    Utility.getInstance().hideKeyboard(mActivity);
                    verifyOtp(EnteredOTP);
                } else {
                    Utility.getInstance().hideKeyboard(mActivity);
                    txtPinEntry.setText("");
                    Utility.getInstance().showSnackbar(getView(), "OTP is invalid");

                }
            }
        });
        resentOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if (count == 0) {
                    showDialogWithOk(getActivity(), getResources().getString(R.string.otpLimitExceed), new LoginFragment(), OtpFragment.this);
                } else {*/
                Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.otpSent));
                txtPinEntry.setText("");
                countDownTimer.start();
                disableResendOtp();
                //}
            }
        });


        return mView;
    }

    private void verifyOtp(String otp) {
        if(otp.equals("0000")){
            /*Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                    .navigate(R.id.action_otpFragment_to_dashboardFragment);*/

            AppPreferences.setLoginPref(mActivity, true);
        }else{
            txtPinEntry.setText("");
            Utility.getInstance().showSnackbar(getView(), "OTP is invalid");
        }

//        if (Utils.getInstance().isNetworkAvailable(mActivity)) {
////To check from which textview its coming
//            if(mobileLogin){
//                mPresenter.verifyOtp(ParentalPreferences.getCountryCode(mActivity),null,ParentalPreferences.getMobileNumber(mActivity),null, otp);
//            }else{
//                mPresenter.verifyOtp(null,null,null,ParentalPreferences.getEmail(mActivity), otp);
//            }
//            //mPresenter.verifyOtp(ParentalPreferences.getCountryCode(mActivity),null,ParentalPreferences.getMobileNumber(mActivity),null, otp);
//        } else {
//            Utils.getInstance().showSnackbar(getView(), getResources().getString(R.string.no_internet_message));
//        }
    }

    public void init(View view){
        timer_otp_detect = (TextView) view.findViewById(R.id.timer_otp_detect);
        resentOtp = (TextView) view.findViewById(R.id.resent_otp);
        //resentOtp = (Button) view.findViewById(R.id.resent_otp);
        number_message = (TextView) view.findViewById(R.id.number_message);
        changeNumber = (TextView) view.findViewById(R.id.changeNumber);

        txtPinEntry = (PinEntryEditText) view.findViewById(R.id.txt_pin_entry);

        txtPinEntry.setFocusable(true);
        toolBarText = mView.findViewById(R.id.toolBarText);
        toolBarText.setText("OTP Verification");
    }

    private void disableResendOtp() {
        //resentOtp.setBackground(mActivity.getResources().getDrawable(R.drawable.selector_login_button_grey_rounded_corner));
        resentOtp.setEnabled(false);
        resentOtp.setTextColor(mActivity.getResources().getColor(R.color.resenddefault));
    }

    private void enableResendOtp() {
        //resentOtp.setBackground(mActivity.getResources().getDrawable(R.drawable.selector_login_button_blue_rounded_corner));
        resentOtp.setEnabled(true);
        resentOtp.setTextColor(mActivity.getResources().getColor(R.color.idonthavemiko_color));
    }


}
