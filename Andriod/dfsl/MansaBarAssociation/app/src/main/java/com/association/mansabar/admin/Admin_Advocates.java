package com.association.mansabar.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.association.mansabar.R;
import com.association.mansabar.services.Check_Permisions;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Admin_Advocates extends AppCompatActivity {
    Check_Permisions check_permission;
    int alreadyDeclined = 0;
    StorageReference ref_upload_res;
    private static final int GALLERY_INTENT = 2;
    String downloadUrl = null;
    ImageView userPic;
    FirebaseDatabase ref_saveAdvocate;
    TextInputLayout nameLayout, chamberLayout, mobileLayout, enrollLayout;
    TextInputEditText name, chamber, mobile, enroll;
    //used for uploading files
    Uri mImageUri = Uri.EMPTY;
    String Date, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_advocates);
        check_permission = new Check_Permisions(this);

        {
            nameLayout = findViewById(R.id.admin_advocates_nameLayout);
            name = findViewById(R.id.admin_advocates_name);
            chamberLayout = findViewById(R.id.admin_advocates_chamberLayout);
            chamber = findViewById(R.id.admin_advocates_chamber);
            mobileLayout = findViewById(R.id.admin_advocates_mobileLayout);
            mobile = findViewById(R.id.admin_advocates_mobile);
            enrollLayout = findViewById(R.id.admin_advocates_enrollLayout);
            enroll = findViewById(R.id.admin_advocates_enroll);
        }
    }

    public void Attach_file(View view) {
        String[] check = new String[]{Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (check_permission.checkPermissions(check)) {
            //Toast.makeText(Chats_screen.this, "accepted", Toast.LENGTH_SHORT).show();
            callgalary();
        } else {
            alreadyDeclined++;
            if (alreadyDeclined >= 3) {
                Toast.makeText(Admin_Advocates.this, "It seems you may have choose ''Don't ask for permission again''.\n Now Please try to reinstall the application.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void saveAdvocate(View view) {
        ref_saveAdvocate = FirebaseDatabase.getInstance();
        DatabaseReference ref = ref_saveAdvocate.getReference("Advocates");
        try {
            {
                if (name.getText().toString() == null || name.getText().toString().equals("")) {
                    nameLayout.setErrorEnabled(true);
                    nameLayout.setError("Please enter in this unit!");
                    name.requestFocus();
                    return;
                }
                if (enroll.getText().toString() == null || enroll.getText().toString().equals("")) {
                    enrollLayout.setErrorEnabled(true);
                    enrollLayout.setError("Please enter in this unit!");
                    enroll.requestFocus();
                    return;
                }
                if (mobile.getText().toString() == null || mobile.getText().toString().equals("")) {
                    mobileLayout.setErrorEnabled(true);
                    mobileLayout.setError("Please enter in this unit!");
                    mobile.requestFocus();
                    return;
                }
                if (chamber.getText().toString() == null || chamber.getText().toString().equals("")) {
                    chamber.setText("-");
                }
                ArrayMap<String, String> map = new ArrayMap<>();
                map.put("picPath", (downloadUrl==null?"null":downloadUrl));
                map.put("name", name.getText().toString());
                map.put("enrollNo", enroll.getText().toString());
                map.put("mobile", mobile.getText().toString());
                map.put("chamberNo", chamber.getText().toString());
                ref.push().setValue(map);
                Toast.makeText(this,"Advocate Saved!",Toast.LENGTH_LONG).show();
            }
//            else{
//                Toast.makeText(this, "Some Error found while uploading user picture!", Toast.LENGTH_LONG).show();
//            }
        } catch (Exception ex) {
            Toast.makeText(this, "Something wrong while saving data!", Toast.LENGTH_LONG).show();
            Log.e("Exception-generated", ex.toString());
        }
    }

    private void callgalary() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, GALLERY_INTENT);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_INTENT && resultCode == RESULT_OK) {

            mImageUri = data.getData();
            String[] fileName = mImageUri.getLastPathSegment().split("/");
            StorageReference filePath = FirebaseStorage.getInstance().getReference().child("Images").child("Advocates").child(fileName[fileName.length - 1]);
            Log.d("LOGGED", "ImageURI : " + fileName[fileName.length - 1]);

            @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat timeFormat = new SimpleDateFormat("hhmm");
            @SuppressLint("SimpleDateFormat") SimpleDateFormat secondFormat = new SimpleDateFormat("ssss");
            Date = dateFormat.format(Calendar.getInstance().getTime());
            time = timeFormat.format(Calendar.getInstance().getTime());
            filePath.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    userPic = findViewById(R.id.admin_advocates_userPic);
                                    downloadUrl = uri.toString();
                                    Glide.with(Admin_Advocates.this).load(downloadUrl).into(userPic);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("4545", e.toString());
                        }
                    });
        } else {
            Toast.makeText(this, "Something Wrong Happened!", Toast.LENGTH_SHORT).show();
        }
    }
}