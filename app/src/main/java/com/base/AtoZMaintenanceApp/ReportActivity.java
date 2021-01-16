package com.base.AtoZMaintenanceApp;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.AtoZMaintenanceApp.Adapter.ImagesAdapter;
import com.base.AtoZMaintenanceApp.CommonFiles.Constants;
import com.base.AtoZMaintenanceApp.imagePicker.ChooserType;
import com.base.AtoZMaintenanceApp.imagePicker.DefaultCallback;
import com.base.AtoZMaintenanceApp.imagePicker.EasyImage;
import com.base.AtoZMaintenanceApp.imagePicker.MediaFile;
import com.base.AtoZMaintenanceApp.imagePicker.MediaSource;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ReportActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener {

    private static final String TAG = "ReportActivity";
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
    TextView toolBarText;
    ImageView backIcon;
    com.wdullaer.materialdatetimepicker.date.DatePickerDialog mdatePickerDialog;
    private ImageView profileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_fragment);

        recyclerView = findViewById(R.id.recycler_view);

        init();


        if (savedInstanceState != null) {
            photos = savedInstanceState.getParcelableArrayList(PHOTOS_KEY);
        }

        imagesAdapter = new ImagesAdapter(this, photos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(imagesAdapter);



        easyImage = new EasyImage.Builder(this)
                .setChooserTitle("Pick media")
                .setCopyImagesToPublicGalleryFolder(false)
//                .setChooserType(ChooserType.CAMERA_AND_DOCUMENTS)
                .setChooserType(ChooserType.CAMERA_AND_GALLERY)
                .setFolderName("EasyImage sample")
                .allowMultiple(true)
                .build();

        checkGalleryAppAvailability();



        findViewById(R.id.chooser_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] necessaryPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (arePermissionsGranted(necessaryPermissions)) {
                    easyImage.openGallery(ReportActivity.this);
                } else {
                    requestPermissionsCompat(necessaryPermissions, GALLERY_REQUEST_CODE);
                }
            }
        });

        findViewById(R.id.feedback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePopUp();
            }
        });

    }

    private void init() {
        profileImg = findViewById(R.id.profileImg);
        toolBarText = findViewById(R.id.toolBarText);
        backIcon = findViewById(R.id.backIcon);

        profileImg.setImageResource(Constants.getInstance().imageName);
        toolBarText.setText(Constants.getInstance().itemName);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(PHOTOS_KEY, photos);
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
            easyImage.openChooser(ReportActivity.this);
        } else if (requestCode == CAMERA_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForImage(ReportActivity.this);
        } else if (requestCode == CAMERA_VIDEO_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openCameraForVideo(ReportActivity.this);
        } else if (requestCode == GALLERY_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openGallery(ReportActivity.this);
        } else if (requestCode == DOCUMENTS_REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            easyImage.openDocuments(ReportActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        easyImage.handleActivityResult(requestCode, resultCode, data, this, new DefaultCallback() {
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
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                return false;

        }
        return true;
    }

    private void requestPermissionsCompat(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(ReportActivity.this, permissions, requestCode);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public void showDatePopUp() {
        Calendar now = Calendar.getInstance();

        com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                ReportActivity.this,
                now.get(Calendar.YEAR), // Initial year selection
                now.get(Calendar.MONTH), // Initial month selection
                now.get(Calendar.DAY_OF_MONTH) // Inital day selection
        );
// If you're calling this from a support Fragment
        dpd.show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @Override
    public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog datePickerDialog, int year, int monthOfYear, int dayOfMonth) {
        mdatePickerDialog = datePickerDialog;
        Log.d(TAG, "onDateSet: " + year + monthOfYear + dayOfMonth );

    }
}
