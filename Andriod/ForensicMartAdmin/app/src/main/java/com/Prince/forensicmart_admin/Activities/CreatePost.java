package com.Prince.forensicmart_admin.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.ViewDialog;
import com.Prince.forensicmart_admin.SmallFeatures.myString;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.ServerResponse;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;
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
    private com.Prince.forensicmart_admin.SmallFeatures.myString myString;
    private String filePath, website;
    private TextInputLayout postlink_lay, postname_lay, website_lay;
    private TextInputEditText postname, postlink;
    private Button create;
    private ImageView image;
    private String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,};

    private ViewDialog viewDialog;
    private AutoCompleteTextView editTextFilledExposedDropdown;
    private MaterialToolbar materialToolbar;

    public static final int READ_EXTERNAL_STORAGE = 0, MULTIPLE_PERMISSIONS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post);
        myString = new myString(this);

        image = findViewById(R.id.addImage);
        postname = findViewById(R.id.postName);
        postname_lay = findViewById(R.id.postName_lay);
        postlink = findViewById(R.id.postLink);
        postlink_lay = findViewById(R.id.postLink_lay);
        create = findViewById(R.id.createPost);
        //website = findViewById(R.id.websiteName);
        //website_lay = findViewById(R.id.websiteName_lay);
        viewDialog = new ViewDialog(this);
        materialToolbar = findViewById(R.id.topAppBar);

        image.setOnClickListener(v -> {
            if (checkPermissions()) {
                doPic();
            }
        });
        //setting gender menu
        {
            final String[] COUNTRIES = new String[]{"company", "college", "publication", "society", "facebook", "youtube", "fsl", "course", "competition"};

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(
                            getBaseContext(),
                            R.layout.dropdown_menu_popup_item,
                            COUNTRIES);

            editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
            editTextFilledExposedDropdown.setAdapter(adapter);

            editTextFilledExposedDropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    String x = parent.getItemAtPosition(position).toString();
                    website = x.toLowerCase();
                }
            });

        }

        create.setOnClickListener(v -> {
            SetDataToDatabase con = Conn.doConnect();
            viewDialog.showDialog();
            TypedFile imgpath = new TypedFile("image/*", new File(filePath));
            if (filePath.isEmpty()) {
                Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
                return;
            }
            con.createPost(imgpath, postname.getText().toString(), myString.no(postlink.getText()), website, new Callback<ServerResponse>() {
                @Override
                public void success(ServerResponse serverResponse, Response response) {
                    viewDialog.hideDialog();
                    Toast.makeText(CreatePost.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
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

        materialToolbar.setNavigationOnClickListener(v -> {
            super.onBackPressed();
        });
    }

    public void doPic() {
        Intent it = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Choose"), 200);
        /*final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(CreatePost.this);
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

                *//*if (options[which].equals("Take Photo"))
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, 0);
                }
                else if (options[which].equals("Choose from Gallery"))
                {
                    Intent it=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    it.setType("image/*");
                    startActivityForResult(Intent.createChooser(it, "Choose"), 200);
                }
                else if (options[which].equals("Cancel")) {
                    dialog.dismiss();
                }*//*

            }
        });
        builder.show();*/

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
            Uri uri = null;
            try {
                if (data != null) {
                    uri = data.getData();
                    Bitmap bitmap = null;
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                    image.setImageBitmap(bitmap);

                    if (uri != null) {
                        String[] projection = {MediaStore.MediaColumns.DATA, MediaStore.MediaColumns.SIZE};
                        Cursor cursor1 = managedQuery(uri, projection, null, null, null);
                        int column_index = cursor1.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
                        cursor1.moveToFirst();
                        filePath = cursor1.getString(column_index);
                    }
                }
                //Toast.makeText(CreatePost.this, filePath, Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }


}