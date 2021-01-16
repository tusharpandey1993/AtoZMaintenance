package com.base.AtoZMaintenanceApp;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.base.AtoZMaintenanceApp.CommonFiles.Constants;

import static com.base.AtoZMaintenanceApp.Fragments.ProfileFragment.profileImg;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    Toolbar toolbar;
    private static final String TAG = "MainActivity";
    private String value ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
            setContentView(R.layout.activity_main);

            if(getIntent() != null && getIntent().getExtras() != null ) {
                value = getIntent().getExtras().getString(Constants.getInstance().ShowSuccess);
                Constants.getInstance().ShowSuccessFlag = true;
            } else {
                Constants.getInstance().ShowSuccessFlag = false;
            }

            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            navController = Navigation.findNavController(this, R.id.navHostFragment);

        } catch (Exception e) {
            Log.e(TAG, "onCreate: " + e.getMessage() );
        }
    }
    public static float dpFromPx(final Context context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        if(profileImg != null) {
                            profileImg.setImageBitmap(selectedImage);
                        }
                    }
                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                if(profileImg != null) {
                                    profileImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                }
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}
