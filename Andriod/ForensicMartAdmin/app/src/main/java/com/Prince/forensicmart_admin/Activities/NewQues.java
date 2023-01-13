package com.Prince.forensicmart_admin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.ViewDialog;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.ServerResponse;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class NewQues extends AppCompatActivity {
    AutoCompleteTextView editTextFilledExposedDropdown, editTextFilledExposedDropdown2;
    String optAnswer = "Select", choosedsubject = "Select";
    EditText ques, optA, optB, optC, optd;
    ViewDialog viewDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_ques);

        {
            ques = findViewById(R.id.editText_question_newQuestion);
            optA = findViewById(R.id.editText_optA_newQuestion);
            optB = findViewById(R.id.editText_optB_newQuestion);
            optC = findViewById(R.id.editText_optC_newQuestion);
            optd = findViewById(R.id.editText_optD_newQuestion);
            viewDialog = new ViewDialog(this);
        }
        //setting subject menu
        {
            final String[] options = new String[]{"Select", "A", "B", "C", "D"};

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(
                            getBaseContext(),
                            R.layout.dropdown_menu_popup_item,
                            options);

            editTextFilledExposedDropdown = findViewById(R.id.filled_exposed_dropdown);
            editTextFilledExposedDropdown.setAdapter(adapter);
            editTextFilledExposedDropdown.setOnItemClickListener((parent, view, position, id) -> optAnswer = parent.getItemAtPosition(position).toString());
        }

        //setting Subject menu
        {
            final String[] Subjects = new String[]{"Select", "Forensic Biology & serology", "Forensic chemistry & Toxicology",
                    "Forensic Ballistics & Explosives", "Physical Evidences", "Crime Scene Investigation", "Question Documents",
                    "Fingerprint Examination", "Forensic Medicine", "Digital Forensic", "History and development","Forensic psychology","Anthropology"};

            ArrayAdapter<String> adapter =
                    new ArrayAdapter<String>(
                            getBaseContext(),
                            R.layout.dropdown_menu_popup_item,
                            Subjects);

            editTextFilledExposedDropdown2 = findViewById(R.id.filled_exposed_dropdown2);
            editTextFilledExposedDropdown2.setAdapter(adapter);

            editTextFilledExposedDropdown2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    choosedsubject = parent.getItemAtPosition(position).toString();
                }
            });
        }
        //google ads
        {
            MobileAds.initialize(this, initializationStatus -> {
            });
            AdView mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }

    }

    public void AddBtn(View view) {
        if (check_EmptyNess()) {
            viewDialog.showDialog();
            SetDataToDatabase con = Conn.doConnect();
            con.addQuizQuestion(ques.getText().toString(), optA.getText().toString(), optB.getText().toString(), optC.getText().toString(),
                    optd.getText().toString(), optAnswer, choosedsubject, new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            if (serverResponse.msg.equals("Uploading Completed.")) {
                                viewDialog.hideDialog();
                                Toast.makeText(NewQues.this, "Upload Successful", Toast.LENGTH_SHORT).show();
                                ques.setText("");
                                optA.setText("");
                                optB.setText("");
                                optC.setText("");
                                optd.setText("");
                                optAnswer = "Select";
                                choosedsubject = "Select";
                                editTextFilledExposedDropdown.setText("Select");
                                editTextFilledExposedDropdown2.setText("Select");
                            } else
                                Toast.makeText(NewQues.this, "" + serverResponse.msg, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            viewDialog.hideDialog();
                            if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                                Toast.makeText(NewQues.this, "No Internet connection found.", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(NewQues.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }


    private boolean check_EmptyNess() {
        if (ques.getText().toString().trim().isEmpty() || optA.getText().toString().trim().isEmpty() || optB.getText().toString().trim().isEmpty() ||
                optC.getText().toString().trim().isEmpty() || optd.getText().toString().trim().isEmpty()) {

            Toast.makeText(this, "Please fill all columns", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (optAnswer.equals("Select")) {
            Toast.makeText(this, "Please select Appropriate Answer", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (choosedsubject.equals("Select")) {
            Toast.makeText(this, "Please select Appropriate Subject", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}