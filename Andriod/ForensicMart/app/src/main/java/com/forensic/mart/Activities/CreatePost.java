package com.forensic.mart.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;
import com.google.android.material.snackbar.Snackbar;
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

    private TextInputLayout title, url, desc;
    private TextInputEditText title_editText, url_editText, desc_editText;
    private AutoCompleteTextView editTextFilledExposedDropdown;
    private String type_editText = null, filePath = "null", userId;
    private TextView addPhotoText;
    private CardView addPhoto;
    private String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,};
    public static final int READ_EXTERNAL_STORAGE = 0, MULTIPLE_PERMISSIONS = 10;
    private TypedFile imgpath;
    private ViewDialog viewDialog;

    @SuppressLint("IntentReset")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);

        // References
        {
            title = findViewById(R.id.createPost_Title);
            url = findViewById(R.id.createPost_Url);
            desc = findViewById(R.id.createPost_description);
            title_editText = findViewById(R.id.createPost_Title_editable);
            url_editText = findViewById(R.id.createPost_url_editable);
            desc_editText = findViewById(R.id.createPost_description_editable);
            addPhoto = findViewById(R.id.createPost_addPhoto);
            addPhotoText = findViewById(R.id.createPost_addPhotoText);
            viewDialog = new ViewDialog(this);
        }
        //setting Post type
        {
            final String[] types = new String[]{"Select", "Event", "Book", "Research Paper", "Blog"};

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<>(
                            getBaseContext(),
                            R.layout.dropdown_menu_popup_item,
                            types);
            type_editText = "Select";
            editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
            editTextFilledExposedDropdown.setAdapter(adapter);

            editTextFilledExposedDropdown.setOnItemClickListener((parent, view, position, id) ->
                    type_editText = parent.getItemAtPosition(position).toString());
        }
        // Get Intents{
        {
            userId = getIntent().getStringExtra("userId");
        }

        addPhoto.setOnClickListener(v -> {
            if (checkPermissions()) {
                Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                it.setType("image/*");
                startActivityForResult(Intent.createChooser(it, "Choose"), 200);
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


    public void AddPost(View view) {
        if (checkEmptyNess()) {
            if (!filePath.equals("null"))
                addWithPic();
            else
                addWithPic();
        }
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

    @SuppressLint("SetTextI18n")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            //image.setImageBitmap(bitmap);
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            //bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            File destination = new File(Environment.getExternalStorageDirectory(), "/DCIM/ForensicMart" + System.currentTimeMillis() + ".jpg");
            try {
                destination.createNewFile();
                FileOutputStream os = new FileOutputStream(destination);
                os.write(bytes.toByteArray());
                os.close();
                filePath = destination.getAbsolutePath();
                imgpath = new TypedFile("image/*", new File(filePath));
                addPhotoText.setText("Ready to Upload");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        if (requestCode == 200) {
            try {
                Uri uri = data.getData();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                //image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.SIZE};
            Cursor cursor1 = managedQuery(uri, projection, null, null, null);
            int column_index = cursor1.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            cursor1.moveToFirst();
            filePath = cursor1.getString(column_index);
            imgpath = new TypedFile("image/*", new File(filePath));
            addPhotoText.setText("Ready to Upload");
            //Toast.makeText(CreatePost.this, filePath, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                //Toast.makeText(this, "Some Internal Error. Try Again.", Toast.LENGTH_SHORT).show();
            }

        }
    }


    private boolean checkEmptyNess() {
        if (type_editText.equals("Select")) {
            Toast.makeText(CreatePost.this, "Please Select Post type.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (title_editText.getText().toString().trim().isEmpty()) {
            title.setHelperTextEnabled(false);
            title.setErrorEnabled(true);
            title.setError("Please add title before you proceed.");
            return false;
        }
        if (!url_editText.getText().toString().startsWith("http") && !url_editText.getText().toString().trim().isEmpty()) {
            url.setHelperTextEnabled(false);
            url.setErrorEnabled(true);
            url.setError("Please make sure url starts with Http \n You can copy link from your local browser");
            return false;
        }
        return true;
    }

    private void addWithPic() {
        viewDialog.showDialog();
        SetDataToDatabase con = Conn.doConnect();
        con.createPostWithPic("withImg", title_editText.getText().toString(), url_editText.getText().toString(), desc_editText.getText().toString(),
                type_editText, userId, imgpath, new Callback<ServerResponse>() {
                    @Override
                    public void success(ServerResponse serverResponse, Response response) {
                        viewDialog.hideDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+serverResponse.msg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        viewDialog.hideDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Something Went Wrong.\nPlease try after sometime.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });

    }

    private void addWithoutPic() {
        viewDialog.showDialog();
        SetDataToDatabase con = Conn.doConnect();
        con.createPostWithoutPic("withoutImg", title_editText.getText().toString(), url_editText.getText().toString(), desc_editText.getText().toString(),
                type_editText, userId, new Callback<ServerResponse>() {
                    @Override
                    public void success(ServerResponse serverResponse, Response response) {
                        viewDialog.hideDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), ""+serverResponse.msg, Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        viewDialog.hideDialog();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Something Went Wrong.\nPlease try after sometime.", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
    }
}