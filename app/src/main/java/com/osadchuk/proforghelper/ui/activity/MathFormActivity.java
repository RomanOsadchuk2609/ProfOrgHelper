package com.osadchuk.proforghelper.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.osadchuk.proforghelper.R;
import com.osadchuk.proforghelper.model.MathForm;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_math_form)
public class MathFormActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;

    private static final int PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 2;

    @StringRes(R.string.math_form)
    String mathFormTitle;

    @ViewById
    ImageView imageViewBlank;

    @ViewById
    Button btnUploadImage;

    @ViewById
    Button btnDeleteImage;

    private MathForm mathForm;

    @AfterViews
    void init() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(mathFormTitle);
        }

        mathForm = MathForm.findFirst();
        if (mathForm != null) {
            imageViewBlank.setImageBitmap(BitmapFactory.decodeFile(mathForm.getUri()));
        }
    }

    @OptionsItem(android.R.id.home)
    void homeSelected() {
        finish();
    }

    @Click
    void btnUploadImage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
            || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }

            return;
        }
        uploadImage();
    }


    @Click
    void btnDeleteImage() {
        if (mathForm != null) {
            mathForm.delete();
            imageViewBlank.setImageURI(Uri.EMPTY);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = getContentResolver().query(imageUri, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                if (mathForm == null) {
                    mathForm = new MathForm();
                }
                mathForm.setUri(imgDecodableString);
                mathForm.save();

                imageViewBlank.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull final String[] permissions, @NonNull final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                uploadImage();
            }
        }
    }

    private void uploadImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

}
