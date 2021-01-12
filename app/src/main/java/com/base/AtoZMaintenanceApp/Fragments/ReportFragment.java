package com.base.AtoZMaintenanceApp.Fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.AtoZMaintenanceApp.Adapter.ImagesAdapter;
import com.base.AtoZMaintenanceApp.CommonFiles.Utility;
import com.base.AtoZMaintenanceApp.R;

import java.util.ArrayList;
import java.util.Arrays;

import pl.aprilapps.easyphotopicker.ChooserType;
import pl.aprilapps.easyphotopicker.DefaultCallback;
import pl.aprilapps.easyphotopicker.EasyImage;
import pl.aprilapps.easyphotopicker.MediaFile;
import pl.aprilapps.easyphotopicker.MediaSource;

public class ReportFragment extends BaseFragment implements View.OnClickListener {


    private View mView;
    private Button btnRegister;
    private EditText editMobileNumber, editEmail, editName;
    private ConstraintLayout layoutAlreadyAccount;
    private TextView txt_privacy_policy;

    //    private ImageView CancelImg ;
    String strName = "", strEmail = "", strMobileNumber = "";

    FragmentActivity mActivity;

    private static final String PHOTOS_KEY = "easy_image_photos_list";
    private static final int CHOOSER_PERMISSIONS_REQUEST_CODE = 7459;
    private static final int CAMERA_REQUEST_CODE = 7500;
    private static final int CAMERA_VIDEO_REQUEST_CODE = 7501;
    private static final int GALLERY_REQUEST_CODE = 7502;
    private static final int DOCUMENTS_REQUEST_CODE = 7503;

    protected RecyclerView recyclerView;

    protected View galleryButton;

    private ImagesAdapter imagesAdapter;

    private ArrayList<MediaFile> photos = new ArrayList<>();

    private EasyImage easyImage;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.report, container, false);

        recyclerView = mView.findViewById(R.id.recycler_view);


        if (savedInstanceState != null) {
            photos = savedInstanceState.getParcelableArrayList(PHOTOS_KEY);
        }

        imagesAdapter = new ImagesAdapter(mActivity, photos);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imagesAdapter);

        easyImage = new EasyImage.Builder(mActivity)
                .setChooserTitle("Pick media")
                .setCopyImagesToPublicGalleryFolder(false)
//                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .setFolderName("EasyImage sample")
                .allowMultiple(true)
                .build();

        checkGalleryAppAvailability();

        mView.findViewById(R.id.chooser_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] necessaryPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (arePermissionsGranted(necessaryPermissions)) {
                    easyImage.openChooser(mActivity);
                } else {
                    requestPermissionsCompat(necessaryPermissions, CHOOSER_PERMISSIONS_REQUEST_CODE);
                }
            }
        });

        return mView;
    }


    private void checkGalleryAppAvailability() {
        if (!easyImage.canDeviceHandleGallery()) {
            //Device has no app that handles gallery intent
            galleryButton.setVisibility(View.GONE);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CHOOSER_PERMISSIONS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openChooser(mActivity);
        } else if (requestCode == CAMERA_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForImage(mActivity);
        } else if (requestCode == CAMERA_VIDEO_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForVideo(mActivity);
        } else if (requestCode == GALLERY_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openGallery(mActivity);
        } else if (requestCode == DOCUMENTS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openDocuments(mActivity);
        }
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, mActivity, new DefaultCallback() {
            @Override
            public void onMediaFilesPicked(MediaFile[] imageFiles, MediaSource source) {
                for (MediaFile imageFile : imageFiles) {
                    Log.d("EasyImage", "Image file returned: " + imageFile.getFile().toString());
                }
                onPhotosReturned(imageFiles);
            }

            @Override
            public void onImagePickerError(@NonNull Throwable error, @NonNull MediaSource source) {
                //Some error handling
                error.printStackTrace();
            }

            @Override
            public void onCanceled(@NonNull MediaSource source) {
                //Not necessary to remove any files manually anymore
            }
        });
    }

    private void onPhotosReturned(@NonNull MediaFile[] returnedPhotos) {
        photos.addAll(Arrays.asList(returnedPhotos));
        imagesAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(photos.size() - 1);
    }

    private boolean arePermissionsGranted(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(mActivity, permission) != PackageManager.PERMISSION_GRANTED)
                Log.d("KL", "arePermissionsGranted: ");
         /*   startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.parse("package:" + BuildConfig.APPLICATION_ID)));
*/
            return false;

        }
        return true;
    }

    private void requestPermissionsCompat(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(mActivity, permissions, requestCode);
    }
}
