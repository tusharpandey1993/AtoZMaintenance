package com.base.BaseProject.CustomViewsFiles.genericPopUp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.base.BaseProject.R;


@SuppressLint("ValidFragment")
public class GenericDialogPopup extends DialogFragment implements View.OnClickListener {

    private final String TAG = GenericDialogPopup.class.getSimpleName();

    public View mView;

    private String heading;
    private String description;
    private Spanned spannedText;
    private String howItWorkHeader;
    private String howItWorkDescription;
    private String howItWorkDescription2;
    private String howItWorkDescription3;
    private String note;
    private String note2;
    private String negativeButtonText;
    private String positiveButtonText;
    private int negativeButtonTextColor;
    private int positiveButtonTextColor;

    private boolean isShowCloseButton;

    private Drawable drawableIcon;

    private GenericDialogClickListener genericDialogClickListener;

    private AppCompatTextView tv_how_it_works;
    private AppCompatTextView tv_how_it_works_desc;
    private AppCompatTextView tv_how_it_works_desc2;
    private AppCompatTextView tv_how_it_works_desc3;
    private AppCompatTextView tv_cancel;
    private AppCompatTextView tv_confirm;
    private AppCompatTextView tv_heading;
    private AppCompatTextView tv_description;
    private AppCompatTextView tv_description_spanned;
    private AppCompatTextView tv_note;
    private AppCompatTextView tv_note2;
    private AppCompatImageView iv_cancel_dialog;
    private AppCompatImageView iv_icon;
    private int functionNumber;
    private boolean isDialogShowing = false;


    public GenericDialogPopup(GenericDialogBuilder genericDialogBuilder) {
        String heading = genericDialogBuilder.getHeading();
        boolean isShowCloseButton = genericDialogBuilder.isShowCloseButton();
        Drawable drawableIcon = genericDialogBuilder.getDrawableIcon();
        String description = genericDialogBuilder.getDescription();
        String howItWorkHeader = genericDialogBuilder.getHowItWorkHeader();
        String howItWorkDescription = genericDialogBuilder.getHowItWorkDescription();
        String howItWorkDescription2 = genericDialogBuilder.getHowItWorkDescription2();
        String howItWorkDescription3 = genericDialogBuilder.getHowItWorkDescription3();
        Spanned spannedText = genericDialogBuilder.getSpannedText();
        String note = genericDialogBuilder.getNote();
        String note2 = genericDialogBuilder.getNote2();
        String negativeButtonText = genericDialogBuilder.getNegativeButtonText();
        String positiveButtonText = genericDialogBuilder.getPositiveButtonText();
        GenericDialogClickListener genericDialogClickListener = genericDialogBuilder.getGenericDialogClickListener();
        functionNumber = genericDialogBuilder.getFucntionNumber();

        this.heading = heading;
        this.isShowCloseButton = isShowCloseButton;
        this.drawableIcon = drawableIcon;
        this.description = description;
        this.spannedText = spannedText;
        this.howItWorkHeader = howItWorkHeader;
        this.howItWorkDescription = howItWorkDescription;
        this.howItWorkDescription2 = howItWorkDescription2;
        this.howItWorkDescription3 = howItWorkDescription3;
        this.note = note;
        this.note2 = note2;
        this.negativeButtonText = negativeButtonText;
        this.positiveButtonText = positiveButtonText;
        this.genericDialogClickListener = genericDialogClickListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        try {
            Window window = dialog.getWindow();
            if (window != null) {
                //window.setBackgroundDrawableResource(R.drawable.card_bg);
                window.setBackgroundDrawableResource(android.R.color.transparent);
                window.setGravity(Gravity.CENTER);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.layout_generic_dialog, container, false);
        setCancelable(false);
        initView(mView);
        bindEvents();
        setData();

        return mView;
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        try {

            FragmentManager fm = getFragmentManager();

           /* if (fm != null) {
                for(int entry = 0; entry < manager.getBackStackEntryCount(); entry++){
                    Log.i(TAG, "Found fragment: " + fm.getBackStackEntryAt(entry).getId());
                }
            }*/
            if (!isDialogShowing) {
                FragmentTransaction ft = manager.beginTransaction();
                    ft.add(this, tag);
                    ft.commitAllowingStateLoss();
                    isDialogShowing = true;
            }
        } catch (IllegalStateException e) {
            Log.d(TAG, "Exception", e);
        }
    }

    public void initView(View view) {

        iv_cancel_dialog = view.findViewById(R.id.iv_cancel_dialog);
        iv_icon = view.findViewById(R.id.iv_icon);
        tv_heading = view.findViewById(R.id.tv_heading);
        tv_description = view.findViewById(R.id.tv_description);
        tv_description_spanned = view.findViewById(R.id.tv_description_spanned);
        tv_how_it_works = view.findViewById(R.id.tv_how_it_works);
        tv_how_it_works_desc = view.findViewById(R.id.tv_how_it_works_desc);
        tv_how_it_works_desc2 = view.findViewById(R.id.tv_how_it_works_desc2);
        tv_how_it_works_desc3 = view.findViewById(R.id.tv_how_it_works_desc3);
        tv_note = view.findViewById(R.id.tv_note);
        tv_note2 = view.findViewById(R.id.tv_note2);

        tv_cancel = view.findViewById(R.id.tv_cancel);
        tv_confirm = view.findViewById(R.id.tv_confirm);



    }

    private void setData() {
        try {

            //set heading value, if its empty or null hide it
            if (!TextUtils.isEmpty(heading)) {
                tv_heading.setText(heading);
                tv_heading.setVisibility(View.VISIBLE);
            } else {
                tv_heading.setVisibility(View.GONE);
            }

            //set description value, if its empty or null hide it
            if (!TextUtils.isEmpty(description)) {
                tv_description.setText(description);
                tv_description.setVisibility(View.VISIBLE);
            } else {
                tv_description.setVisibility(View.GONE);
            }

            //set description value, if its empty or null hide it
            if (!TextUtils.isEmpty(spannedText)) {
                tv_description_spanned.setText(spannedText);
                tv_description_spanned.setVisibility(View.VISIBLE);
            } else {
                tv_description_spanned.setVisibility(View.GONE);
            }

            //set drawableIcon value, if its empty or null hide it
            if (drawableIcon != null) {
                iv_icon.setBackgroundDrawable(drawableIcon);
                iv_icon.setVisibility(View.VISIBLE);
            } else {
                iv_icon.setVisibility(View.GONE);
            }

            //set isShowCloseButton value, if its false or null hide it
            if (isShowCloseButton) {
                iv_cancel_dialog.setVisibility(View.VISIBLE);
            } else {
                iv_cancel_dialog.setVisibility(View.GONE);
            }

            //set drawableIcon value, if its empty or null hide it
            if (!TextUtils.isEmpty(howItWorkHeader)) {
                tv_how_it_works.setText(howItWorkHeader);
                tv_how_it_works.setVisibility(View.VISIBLE);
            } else {
                tv_how_it_works.setVisibility(View.GONE);
            }

            //set howItWorkDescription value, if its empty or null hide it
            if (!TextUtils.isEmpty(howItWorkDescription)) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    tv_how_it_works_desc.setText(Html.fromHtml(howItWorkDescription, Html.FROM_HTML_MODE_LEGACY));
                } else {
                    tv_how_it_works_desc.setText(Html.fromHtml(howItWorkDescription));
                }
                tv_how_it_works_desc.setVisibility(View.VISIBLE);
            } else {
                tv_how_it_works_desc.setVisibility(View.GONE);
            }


            //set howItWorkDescription2 value, if its empty or null hide it
            if (!TextUtils.isEmpty(howItWorkDescription2)) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    tv_how_it_works_desc2.setText(Html.fromHtml(howItWorkDescription2, Html.FROM_HTML_MODE_LEGACY));
                } else {
                    tv_how_it_works_desc2.setText(Html.fromHtml(howItWorkDescription2));
                }
                tv_how_it_works_desc2.setVisibility(View.VISIBLE);
            } else {
                tv_how_it_works_desc2.setVisibility(View.GONE);
            }

            //set howItWorkDescription2 value, if its empty or null hide it
            if (!TextUtils.isEmpty(howItWorkDescription3)) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    tv_how_it_works_desc3.setText(Html.fromHtml(howItWorkDescription3, Html.FROM_HTML_MODE_LEGACY));
                } else {
                    tv_how_it_works_desc3.setText(Html.fromHtml(howItWorkDescription3));
                }
                tv_how_it_works_desc3.setVisibility(View.VISIBLE);
            } else {
                tv_how_it_works_desc3.setVisibility(View.GONE);
            }

            //set note value, if its empty or null hide it
            if (!TextUtils.isEmpty(note)) {
                tv_note.setText(note);
                tv_note.setVisibility(View.VISIBLE);
            } else {
                tv_note.setVisibility(View.GONE);
            }

            //set note value, if its empty or null hide it
            if (!TextUtils.isEmpty(note2)) {
                tv_note2.setText(note2);
                tv_note2.setVisibility(View.VISIBLE);
            } else {
                tv_note2.setVisibility(View.GONE);
            }

            //set negative value, if its empty or null hide it
            if (!TextUtils.isEmpty(negativeButtonText)) {
                tv_cancel.setText(negativeButtonText);
                tv_cancel.setVisibility(View.VISIBLE);

                //set text color
                if (negativeButtonTextColor > 0) {
                    tv_cancel.setTextColor(negativeButtonTextColor);
                }
            } else {
                tv_cancel.setVisibility(View.GONE);
            }

            //set positive value, if its empty or null hide it
            if (!TextUtils.isEmpty(positiveButtonText)) {
                tv_confirm.setText(positiveButtonText);
                tv_confirm.setVisibility(View.VISIBLE);

                //set text color
                if (positiveButtonTextColor > 0) {
                    tv_confirm.setTextColor(positiveButtonTextColor);
                }
            } else {
                tv_confirm.setVisibility(View.GONE);
            }


            /*//set gravity of positive button to center if negativeButton is gone
            if (tv_cancel.getVisibility() == View.GONE) {
                tv_confirm.setGravity(Gravity.CENTER);
            }
*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void bindEvents() {
        iv_cancel_dialog.setOnClickListener(this);
        tv_cancel.setOnClickListener(this);
        tv_confirm.setOnClickListener(this);
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_cancel_dialog:
                if (genericDialogClickListener != null) {
                    genericDialogClickListener.onDialogCloseButtonClick(v, functionNumber);
                }
                dismiss();
                isDialogShowing = false;
                break;
            case R.id.tv_cancel:
                if (genericDialogClickListener != null) {
                    genericDialogClickListener.onNegativeButtonClick(v, functionNumber);
                }
                dismiss();
                isDialogShowing = false;
                break;
            case R.id.tv_confirm:
                if (genericDialogClickListener != null) {
                    genericDialogClickListener.onPositiveButtonClick(v, functionNumber);
                }
                dismiss();
                isDialogShowing = false;
                break;
            default:
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: DialogFragment");
        isDialogShowing = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        isDialogShowing = false;
        Log.d(TAG, "onStop: DialogFragment");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        isDialogShowing = false;
        Log.d(TAG, "onDetach: DialogFragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDialogShowing = false;
        Log.d(TAG, "onDestroyView: DialogFragment");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isDialogShowing = false;
        Log.d(TAG, "onDestroy: DialogFragment");
    }
}
