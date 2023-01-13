package com.Prince.forensicmart_admin.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.ViewDialog;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.ServerResponse;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class CertificateManager extends AppCompatActivity {

    private String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,};
    public static final int READ_EXTERNAL_STORAGE = 0, MULTIPLE_PERMISSIONS = 10;
    private ViewDialog viewDialog;
    private ImageView imageView;
    private String filePath;
    private TypedFile imgpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate_manager);

        MaterialToolbar materialToolbar;
        Button button;
        {
            materialToolbar = findViewById(R.id.topAppBar);
            imageView = findViewById(R.id.Image_CertificateManager);
            button = findViewById(R.id.addCertificate);
            viewDialog = new ViewDialog(this);
        }
        LoadCertificate();
        button.setOnClickListener(v -> {
            Upload();
        });
        materialToolbar.setNavigationOnClickListener(v -> {
            super.onBackPressed();
        });

        }

    private void LoadCertificate(){
        Picasso.with(this).load("https://www.forensicmart.com/Android/Certificate/format.png")
                .error(R.drawable.nodata).into(imageView);
    }

    @SuppressLint("IntentReset")
    private void Upload() {
        if (checkPermissions()) {
            Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            it.setType("image/*");
            startActivityForResult(Intent.createChooser(it, "Choose"), 200);

            if (filePath==null)return;
            if ( filePath.isEmpty()) {
                Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
                return;
            }

        }
    }

    private void ProceedToUpload(){
        viewDialog.showDialog();
        SetDataToDatabase con = Conn.doConnect();
        con.updateCertificate(imgpath,new Callback<ServerResponse>(){
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideDialog();
                LoadCertificate();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(CertificateManager.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CertificateManager.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getApplicationContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    return;

            case MULTIPLE_PERMISSIONS: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //callCamera();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            File destination = new File(Environment.getExternalStorageDirectory(), "/DCIM/ForensicMart" + System.currentTimeMillis() + ".jpg");
            try {
                destination.createNewFile();
                FileOutputStream os = new FileOutputStream(destination);
                os.write(bytes.toByteArray());
                os.close();
                filePath = destination.getAbsolutePath();
                //Toast.makeText(CreatePost.this, filePath, Toast.LENGTH_SHORT).show();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (requestCode == 200) {
            Uri uri = null;
            try {
                if (data != null) {
                    uri = data.getData();
                    Bitmap bitmap = null;
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                   // imageView.setImageBitmap(bitmap);

                    if (uri != null) {
                        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.SIZE};
                        Cursor cursor1 = managedQuery(uri, projection, null, null, null);
                        int column_index = cursor1.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                        cursor1.moveToFirst();
                        filePath = cursor1.getString(column_index);
                        if (!filePath.endsWith(".png")){
                            imgpath = new TypedFile("image/*", new File(filePath));
                            Toast.makeText(this,"Please Select Image with Png Format.",Toast.LENGTH_LONG).show();
                        }
                        else {
                            imgpath = new TypedFile("image/*", new File(filePath));
                            System.out.println(imgpath+" "+filePath);
                            ProceedToUpload();
                        }
                    }
                }
                //Toast.makeText(CreatePost.this, filePath, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}