package com.base.AtoZMaintenanceApp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;

import com.base.AtoZMaintenanceApp.CommonFiles.Utility;
import com.base.AtoZMaintenanceApp.R;

public class RegisterFragment extends BaseFragment implements View.OnClickListener {


    private View mView;
    private Button btnRegister;
    private EditText editMobileNumber, editEmail, editName;
    private ConstraintLayout layoutAlreadyAccount;
    private TextView txt_privacy_policy,toolBarText;
    private ImageView backIcon;

    //    private ImageView CancelImg ;
    String strName = "", strEmail = "", strMobileNumber = "";

    FragmentActivity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Utility.getInstance().setApplicationLocale("hi",mActivity);
        mView = inflater.inflate(R.layout.fragment_register, container, false);
        editMobileNumber = (EditText) mView.findViewById(R.id.edit_mobile_number);
        editEmail = (EditText) mView.findViewById(R.id.edit_email);
        editName = (EditText) mView.findViewById(R.id.edit_name);
        btnRegister = (Button) mView.findViewById(R.id.btnRegister);
        layoutAlreadyAccount = (ConstraintLayout) mView.findViewById(R.id.layoutAlreadyAccount);
        btnRegister.setOnClickListener(this);
        layoutAlreadyAccount.setOnClickListener(this);
        txt_privacy_policy = (TextView) mView.findViewById(R.id.txt_privacy_policy);
        toolBarText = (TextView) mView.findViewById(R.id.toolBarText);
        backIcon = mView.findViewById(R.id.backIcon);
        toolBarText.setText("Register");
        txt_privacy_policy.setOnClickListener(this);
        backIcon.setOnClickListener(this);


        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        Utils.getInstance().fadeOutToBottom(mBinding.parentConstraintlayout, true);

        editName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
              /*  if (s.length() != 0) {
                    checkFormFilled();
                } else {
                    enableSubmitButton(false);
                }*/
            }
        });

        editEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                /*if (s.length() != 0) {
                    checkFormFilled();
                } else {
                    enableSubmitButton(false);
                }*/
            }
        });

        editMobileNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                try {
//                    if(ccp.getSelectedCountryCodeWithPlus().equalsIgnoreCase("+0")) {
//                        ccp.setCountryForNameCode(setCountryCode());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if (s.length() != 0) {
                    checkFormFilled();
                } else {
                    enableSubmitButton(false);
                }*/
            }
        });

    }


    public void registerNow(View view) {
        Utility.getInstance().hideKeyboard(mActivity);
        if (Utility.getInstance().isNetworkAvailable(mActivity)) {
            if (isValid(view)) {
                Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                        .navigate(R.id.action_RegisterFragment_to_successFragment);
            }
        } else {
            Utility.getInstance().showSnackbar(view, getResources().getString(R.string.no_internet_message));
        }
    }

    private boolean isValid(View view) {
        strName = editName.getText().toString();
        strEmail = editEmail.getText().toString();
        strMobileNumber = editMobileNumber.getText().toString();
       // String ccpval = ccp.getSelectedCountryCodeWithPlus();
//        Log.e(TAG, "isValid: "+ccpval );

        if (TextUtils.isEmpty(strName)) {
            Utility.getInstance().showSnackbar(view, getResources().getString(R.string.error_invalid_name));
            return false;
        } else if (TextUtils.isEmpty(strEmail)) {
            Utility.getInstance().showSnackbar(view, getResources().getString(R.string.error_invalid_email_empty));
            return false;
        } else if (!Utility.getInstance().isEmailValid(strEmail)) {
            Utility.getInstance().showSnackbar(view, getResources().getString(R.string.error_invalid_email));
            return false;
        } /*else if (ccpval.equalsIgnoreCase("+0")) {
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_invalid_countryCode));
            return false;
        }*/ else if (TextUtils.isEmpty(strMobileNumber)) {
            Utility.getInstance().showSnackbar(view, getResources().getString(R.string.error_invalid_mobilenumber));
            return false;
        } else if (strMobileNumber.length() < 4) {
            Utility.getInstance().showSnackbar(view, getResources().getString(R.string.error_invalid_mobilenumber));
            return false;
        } else if (strMobileNumber.startsWith("0")) {
            Utility.getInstance().showSnackbar(getView(), getResources().getString(R.string.error_mobilenumberZero));
            return false;
        }
        return true;
    }


    public void hideKeyboard(View view) {
        Utility.getInstance().hideKeyboard(mActivity);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnRegister:
                registerNow(v);
                break;

            case R.id.layoutAlreadyAccount:
                mActivity.onBackPressed();
                break;

            case R.id.txt_privacy_policy:

                break;

                case R.id.backIcon:
                    Navigation.findNavController(requireActivity(),R.id.navHostFragment)
                            .navigate(R.id.action_RegisterFragment_to_LoginFrag);
                break;

        }

    }

   /* public void ShowGenericDialog(int FunctionNum, String ButtonText, String DialogMessage, String dialogHeading) {
        GenericDialogBuilder genericDialogBuilder = new GenericDialogBuilder.Builder()
                .setShowCloseButton(false)
                .setHeading(dialogHeading)
                .setDescription(DialogMessage)
                .setPositiveButtonText(ButtonText)
                .setGenericDialogClickListener(this)
                .setFucntionNumber(FunctionNum)
                .build();
        showDynamicDialog(genericDialogBuilder);
    }*/


    @Override
    public void onPositiveButtonClick(View view, int FucntionNumber) {
       /* if (FucntionNumber == Constants.registok) {
            mActivity.onBackPressed();
        }*/
    }


    @Override
    public void onNegativeButtonClick(View view, int FucntionNumber) {

    }

    @Override
    public void onDialogCloseButtonClick(View view, int FucntionNumber) {

    }

   /* private void enableSubmitButton(boolean enable) {
        btnRegister.setEnabled(enable);
        if (enable) {
            Utility.getInstance().setGradientBgColor(mActivity, btnRegister, R.color.white);
        } else {
            Utility.getInstance().setGradientBgColor(mActivity, btnRegister, R.color.white_alpha_20);
        }
    }*/

  /*  private void checkFormFilled() {
        String name = editName.getText().toString();
        String email = editEmail.getText().toString();
        String mobile = editMobileNumber.getText().toString();
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(mobile)) {
            enableSubmitButton(true);
        } else {
            enableSubmitButton(false);
        }
    }*/

    @Override
    public void onResume() {
        super.onResume();
//        checkFormFilled();
    }
}

