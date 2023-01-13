package com.forensic.mart.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.forensic.mart.Adapter.Edit_education_adapter;
import com.forensic.mart.Adapter.Edit_employment_adapter;
import com.forensic.mart.BeanFiles.Education_bean;
import com.forensic.mart.BeanFiles.Employments_bean;
import com.forensic.mart.BeanFiles.Profile_data_bean;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.CircleTransform;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;
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

public class Edit_user_profile extends AppCompatActivity {
    private TextInputLayout username, email, mobile, designation;
    private TextInputEditText username_editText, email_editText, mobile_editText, designation_editText;
    private ImageView profilePic;
    private String myUsername, user_id, filePath;
    private RecyclerView recyclerView, recyclerView2;
    private Edit_education_adapter adapter;
    private Edit_employment_adapter adapter2;
    private LinearLayoutManager linearManager, linearManager2;
    private ViewDialog viewDialog;
    private TextView updatePic;
    private Button update_designation, add_edu, add_emplpy, savePic;
    private String[] permissions = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,};
    public static final int READ_EXTERNAL_STORAGE = 0, MULTIPLE_PERMISSIONS = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);

        // creating references
        {
            username = findViewById(R.id.username_edit_user_profile);
            email = findViewById(R.id.email_edit_user_profile);
            mobile = findViewById(R.id.mobile_edit_user_profile);
            designation = findViewById(R.id.designation_edit_user_profile);
            username_editText = findViewById(R.id.username_edit_user_profile_editable);
            email_editText = findViewById(R.id.email_edit_user_profile_editable);
            mobile_editText = findViewById(R.id.mobile_edit_user_profile_editable);
            designation_editText = findViewById(R.id.designation_edit_user_profile_editable);
            profilePic = findViewById(R.id.profile_image_edit_user_profile);
            update_designation = findViewById(R.id.update_designation_edit_user_profile);
            add_edu = findViewById(R.id.new_edu_edit_user_profile);
            add_emplpy = findViewById(R.id.new_employ_edit_user_profile);
            updatePic = findViewById(R.id.edit_profile_pic_edit_user_profile);
            savePic = findViewById(R.id.save_pic_edit_user_profile);


            recyclerView = findViewById(R.id.recycler_education_edit_users);
            linearManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(linearManager);

            recyclerView2 = findViewById(R.id.recycler_employ_edit_users);
            linearManager2 = new LinearLayoutManager(this);
            recyclerView2.setLayoutManager(linearManager2);
            viewDialog = new ViewDialog(this);
        }
        {
            myUsername = getIntent().getStringExtra("username");
        }
        fetchData();
        updatePic.setOnClickListener(v -> {
            if (checkPermissions()) {
                doPic();
            }
        });
        profilePic.setOnClickListener(v -> {
            if (checkPermissions()) {
                doPic();
            }
        });

    }

    public void fetchData() {
        viewDialog.showDialog();
        SetDataToDatabase con = Conn.doConnect();
        con.fetchData(myUsername, "main", new Callback<Profile_data_bean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void success(Profile_data_bean profile_data_bean, Response response) {
                viewDialog.hideDialog();
                username_editText.setText(profile_data_bean.data.get(0).getUsername());
                mobile_editText.setText("Mobile:" + profile_data_bean.data.get(0).getPhone());
                designation_editText.setText("Designation: " + profile_data_bean.data.get(0).getDesignation());
                email_editText.setText("Email: " + profile_data_bean.data.get(0).getEmail());
                user_id = profile_data_bean.data.get(0).getId();
                Picasso.with(Edit_user_profile.this).load("https://www.forensicmart.com/uploads/" + profile_data_bean.data.get(0).getPicture())
                        .transform(new CircleTransform()).fit().error(R.drawable.noprofilepic).into(profilePic);
                SetDetailData(profile_data_bean.data.get(0).getId());
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Edit_user_profile.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Edit_user_profile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void SetDetailData(String id) {
        SetDataToDatabase con = Conn.doConnect();
        con.fetchEduData(id, "edu", new Callback<Education_bean>() {
            @Override
            public void success(Education_bean education_bean, Response response) {
                adapter = new Edit_education_adapter(education_bean.data, Edit_user_profile.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Edit_user_profile.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Edit_user_profile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        con.fetchEmployData(id, "employ", new Callback<Employments_bean>() {
            @Override
            public void success(Employments_bean employments_bean, Response response) {
                adapter2 = new Edit_employment_adapter(employments_bean.data, Edit_user_profile.this);
                recyclerView2.setAdapter(adapter2);
            }

            @Override
            public void failure(RetrofitError error) {
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Edit_user_profile.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Edit_user_profile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @SuppressLint("SetTextI18n")
    public void update_mainProfile(View view) {
        update_designation.setText("Updating");
        update_designation.setClickable(false);
        SetDataToDatabase con = Conn.doConnect();
        con.EditProfile_desi(user_id, designation_editText.getText().toString(), new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                update_designation.setText("Update");
                update_designation.setClickable(true);
                fetchData();
                Snackbar.make(view, serverResponse.msg, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                update_designation.setText("Update");
                update_designation.setClickable(true);
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Edit_user_profile.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Edit_user_profile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void Add_Education(View view) {
        LayoutInflater inf = LayoutInflater.from(this);
        View vw = inf.inflate(R.layout.edit_add_user_profile_skill, null);
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setView(vw);
        dlg.setCancelable(true);

        Button add = vw.findViewById(R.id.add_edit_user_profile_skill);
        Button update = vw.findViewById(R.id.update_edit_user_profile_skill);
        Button delete = vw.findViewById(R.id.delete_edit_user_profile_skill);
        update.setVisibility(View.INVISIBLE);
        update.setVisibility(View.GONE);
        delete.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.GONE);
        TextInputLayout title = vw.findViewById(R.id.edit_level_user_profile_skill);
        TextInputLayout from = vw.findViewById(R.id.edit_start_user_profile_skill);
        TextInputLayout to = vw.findViewById(R.id.edit_end_user_profile_skill);
        TextInputLayout details = vw.findViewById(R.id.edit_other_user_profile_skill);
        TextInputEditText title_editText = vw.findViewById(R.id.edit_level_user_profile_skill_editable);
        TextInputEditText from_editText = vw.findViewById(R.id.edit_start_user_profile_skill_editable);
        TextInputEditText to_editText = vw.findViewById(R.id.edit_end_user_profile_skill_editable);
        TextInputEditText details_editText = vw.findViewById(R.id.edit_other_user_profile_skill_editable);
        final AlertDialog dialog = dlg.create();
        title.setHint("Education Level");
        details.setHint("Institute Name");
        title.setHelperText("Enter your Education Level here");
        details.setHelperText("Enter your Institute Name Here");
        add.setOnClickListener(v -> {
            add_edu.setText("Updating");
            add_edu.setClickable(false);
            SetDataToDatabase con = Conn.doConnect();
            con.EditProfile_add_edu("add_edu", user_id, title_editText.getText().toString(), from_editText.getText().toString(),
                    to_editText.getText().toString(), details_editText.getText().toString(), "", new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            add_edu.setText("Update");
                            add_edu.setClickable(true);
                            dialog.dismiss();
                            fetchData();
                            Snackbar.make(view, serverResponse.msg, Snackbar.LENGTH_LONG).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            add_edu.setText("Update");
                            add_edu.setClickable(true);
                            dialog.dismiss();
                            if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                                Toast.makeText(Edit_user_profile.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Edit_user_profile.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });
        dialog.show();

    }


    public void add_employment(View view) {
        LayoutInflater inf = LayoutInflater.from(this);
        View vw = inf.inflate(R.layout.edit_add_user_profile_skill, null);
        final AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setView(vw);
        dlg.setCancelable(true);

        Button add = vw.findViewById(R.id.add_edit_user_profile_skill);
        Button update = vw.findViewById(R.id.update_edit_user_profile_skill);
        Button delete = vw.findViewById(R.id.delete_edit_user_profile_skill);
        update.setVisibility(View.INVISIBLE);
        update.setVisibility(View.GONE);
        delete.setVisibility(View.INVISIBLE);
        delete.setVisibility(View.GONE);
        TextInputLayout title = vw.findViewById(R.id.edit_level_user_profile_skill);
        TextInputLayout from = vw.findViewById(R.id.edit_start_user_profile_skill);
        TextInputLayout to = vw.findViewById(R.id.edit_end_user_profile_skill);
        TextInputLayout details = vw.findViewById(R.id.edit_other_user_profile_skill);
        TextInputEditText title_editText = vw.findViewById(R.id.edit_level_user_profile_skill_editable);
        TextInputEditText from_editText = vw.findViewById(R.id.edit_start_user_profile_skill_editable);
        TextInputEditText to_editText = vw.findViewById(R.id.edit_end_user_profile_skill_editable);
        TextInputEditText details_editText = vw.findViewById(R.id.edit_other_user_profile_skill_editable);
        final AlertDialog dialog = dlg.create();
        title.setHint("Employment Level");
        details.setHint("Details");
        title.setHelperText("Enter your Employment Level here");
        details.setHelperText("Enter your Details Here");
        add.setOnClickListener(v -> {
            add_emplpy.setText("Updating");
            add_emplpy.setClickable(false);
            SetDataToDatabase con = Conn.doConnect();
            con.EditProfile_add_employ("add_employ", user_id, title_editText.getText().toString(), from_editText.getText().toString(),
                    to_editText.getText().toString(), details_editText.getText().toString(), new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            dialog.dismiss();
                            add_emplpy.setText("Update");
                            add_emplpy.setClickable(true);
                            fetchData();
                            Snackbar.make(view, serverResponse.msg, Snackbar.LENGTH_LONG);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            dialog.dismiss();
                            add_emplpy.setText("Update");
                            add_emplpy.setClickable(true);
                            if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                                Toast.makeText(Edit_user_profile.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(Edit_user_profile.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        });
        dialog.show();
    }

    public void save_pic(View view) {
        SetDataToDatabase con = Conn.doConnect();
        TypedFile imgpath = new TypedFile("image/*", new File(filePath));
        if (filePath.isEmpty()) {
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
            return;
        }
        viewDialog.showDialog();
        con.Update_profile_pic(imgpath, user_id, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideDialog();
                Snackbar.make(view, serverResponse.msg, Snackbar.LENGTH_LONG);
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Edit_user_profile.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Edit_user_profile.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
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
            profilePic.setImageBitmap(bitmap);
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

                profilePic.setImageBitmap(bitmap);
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