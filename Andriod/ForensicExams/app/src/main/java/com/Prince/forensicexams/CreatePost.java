package com.Prince.forensicexams;

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
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.Prince.forensicexams.SmallFeatures.PushNoti;
import com.Prince.forensicexams.SmallFeatures.ViewDialog;
import com.Prince.forensicexams.server.Conn;
import com.Prince.forensicexams.server.ServerResponse;
import com.Prince.forensicexams.server.SetDataToDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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

public class CreatePost extends AppCompatActivity {
    String filePath;
    TextInputLayout postlink_lay;
    TextInputEditText postlink;
    Button create;
    ImageView image;
    String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,};

    ViewDialog viewDialog;
    PushNoti push;


    public static final int READ_EXTERNAL_STORAGE = 0, MULTIPLE_PERMISSIONS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        image = findViewById(R.id.addImage);
        postlink = findViewById(R.id.postLink);
        postlink_lay = findViewById(R.id.postLink_lay);
        create = findViewById(R.id.createPost);
        viewDialog = new ViewDialog(this);
        push = new PushNoti(this);

        image.setOnClickListener(v -> {
            if (checkPermissions()) {
                doPic();
            }
        });

        create.setOnClickListener(v -> {
            SetDataToDatabase con = Conn.doConnect();
            TypedFile imgpath = new TypedFile("image/*", new File(filePath));
            if (filePath.isEmpty()) {
                Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
                return;
            }
            if (postlink.getText().toString().trim().isEmpty()) {
                postlink_lay.setHelperTextEnabled(false);
                postlink_lay.setErrorEnabled(true);
                postlink_lay.setError("Please Fill competition url here.");
                return;
            }
            if (!postlink.getText().toString().startsWith("http"))
            {
                postlink_lay.setHelperTextEnabled(false);
                postlink_lay.setErrorEnabled(true);
                postlink_lay.setError("Please make sure url starts with Http \n You can copy link from your loacal browser");
                return;
            }

            viewDialog.showDialog();
            con.createPost(imgpath, postlink.getText().toString(), new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    viewDialog.hideDialog();
                    Log.d("Tag",filePath);
                    Toast.makeText(CreatePost.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
                    if (serverResponse.msg.equals("Uploading Completed.")) {
                        Intent intent = new Intent(CreatePost.this, CompetitionPosts.class);
                        intent.putExtra("from", "cp");
                        intent.putExtra("file", filePath);
                        intent.putExtra("url", postlink.getText().toString());
                        push.sendNotification_segment(getString(R.string.header_newComp),getString(R.string.msg_newComp));
                        startActivity(intent);
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    viewDialog.hideDialog();
                    if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                        Toast.makeText(CreatePost.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(CreatePost.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });


    }

    @SuppressLint("IntentReset")
    public void doPic() {
        Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Choose"), 200);
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
            image.setImageBitmap(bitmap);
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
            Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.SIZE};
            Cursor cursor1 = managedQuery(uri, projection, null, null, null);
            int column_index = cursor1.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor1.moveToFirst();
            filePath = cursor1.getString(column_index);
            //Toast.makeText(CreatePost.this, filePath, Toast.LENGTH_SHORT).show();

        }
    }

}