package com.Prince.forensicmart_admin.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.Prince.forensicmart_admin.Bean.Competition_bean;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.SmallFeatures.ViewDialog;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.Connection;
import com.Prince.forensicmart_admin.server.ServerResponse;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ManageCompetitions extends AppCompatActivity {
    private Slider slider;
    private TextInputLayout compName_lay, ques_lay, optA_lay, optB_lay, optC_lay, optD_lay, ans_lay;
    private TextInputEditText compName_edit, ques_edit, optA_edit, optB_edit, optC_edit, optD_edit;
    private Button button, registerName;
    private MaterialCardView cardView;
    private MaterialToolbar materialToolbar;
    private String compID, isLive, optAnswer = "noSelected", mode = "update";
    private AutoCompleteTextView editTextFilledExposedDropdown;
    private int quesNo = 1;
    private ImageView editCompName;
    private ArrayList<Competition_bean.UserBean> data;
    private ViewDialog viewDialog;
    private TextView quesNoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_competitions);

        {
            slider = findViewById(R.id.manage_activity_slider_question);
            compName_lay = findViewById(R.id.manage_competition_name_layout);
            button = findViewById(R.id.manage_competition_button);
            registerName = findViewById(R.id.manage_competition_registerCompetition_button);
            cardView = findViewById(R.id.manage_competition_questionCard);
            materialToolbar = findViewById(R.id.topAppBar);
            ques_lay = findViewById(R.id.manage_competition_ques_layout);
            optA_lay = findViewById(R.id.manage_competition_optA_layout);
            optB_lay = findViewById(R.id.manage_competition_optB_layout);
            optC_lay = findViewById(R.id.manage_competition_optC_layout);
            optD_lay = findViewById(R.id.manage_competition_optD_layout);
            ans_lay = findViewById(R.id.manage_competition_ans_layout);
            compName_edit = findViewById(R.id.manage_competition_name_editabble);
            ques_edit = findViewById(R.id.manage_competition_ques_editabble);
            optA_edit = findViewById(R.id.manage_competition_optA_editabble);
            optB_edit = findViewById(R.id.manage_competition_optB_editabble);
            optC_edit = findViewById(R.id.manage_competition_optC_editabble);
            optD_edit = findViewById(R.id.manage_competition_optD_editabble);
            editCompName = findViewById(R.id.topAppBarIcon_edit);
            viewDialog = new ViewDialog(this);
            quesNoView = findViewById(R.id.manage_activity_questionNo);
        }
        String path = getIntent().getStringExtra("path");
        assert path != null;
        if (path.equals("add")) {
            setUpCompetition();
        } else {
            compID = getIntent().getStringExtra("id");
            registerName.setText(mode);
            button.setText(mode);
            setUpOldCompetition();
            getQues(compID);
            //EditCompetition(id);
        }
        slider.addOnSliderTouchListener(touchListener);


        materialToolbar.setNavigationOnClickListener(v -> {
            super.onBackPressed();
        });
        //setting option menu
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

        materialToolbar.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.manage_activity_topMenu_setLive:
                    updateStatus(1);
                    break;
                case R.id.manage_activity_topMenu_setclosed:
                    updateStatus(-1);
                    break;
                case R.id.manage_activity_topMenu_setSoon:
                    updateStatus(0);
                    break;
            }
            return true;
        });

    }

    private void updateStatus(int i) {
        viewDialog.showSnackBar();
        SetDataToDatabase con = Conn.doConnect();
        con.Competition("updateStatus", "", "", compID, "", "", "", "", "", "", "", String.valueOf(i), new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideSnackBar();
                Snackbar.make(findViewById(android.R.id.content), serverResponse.msg, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideSnackBar();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                    Snackbar.make(findViewById(android.R.id.content), "No Internet Connection!\nPlease try again later.", Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(findViewById(android.R.id.content), error.toString() + "", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private void getQues(String cid) {
        viewDialog.showSnackBar();
        SetDataToDatabase con = Conn.doConnect();
        con.Competition_list("getQues", cid, new Callback<Competition_bean>() {
            @Override
            public void success(Competition_bean competition_bean, Response response) {
                viewDialog.hideSnackBar();
                try {
                    data.clear();
                } catch (Exception ignored) {
                }
                data = competition_bean.data;
                modifyQuestionPallet(1);
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideSnackBar();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                    Snackbar.make(findViewById(android.R.id.content), "No Internet Connection!\nPlease try again later.", Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(findViewById(android.R.id.content), error.toString() + "", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    private final Slider.OnSliderTouchListener touchListener =
            new Slider.OnSliderTouchListener() {
                @Override
                public void onStartTrackingTouch(@NotNull Slider slider) {
                    ques_edit.setText("");
                    optA_edit.setText("");
                    optB_edit.setText("");
                    optC_edit.setText("");
                    optD_edit.setText("");
                    editTextFilledExposedDropdown.setListSelection(0);
                }

                @Override
                public void onStopTrackingTouch(@NotNull Slider slider) {
                    quesNo = (int) slider.getValue();
                    modifyQuestionPallet(quesNo);
                }
            };

    @SuppressLint("SetTextI18n")
    private void modifyQuestionPallet(int quesNo) {
        ques_edit.setText(data.get(quesNo - 1).getQues());
        optA_edit.setText(data.get(quesNo - 1).getOptA());
        optB_edit.setText(data.get(quesNo - 1).getOptB());
        optC_edit.setText(data.get(quesNo - 1).getOptC());
        optD_edit.setText(data.get(quesNo - 1).getOptD());
        quesNoView.setText(quesNo + ")");
        switch (data.get(quesNo).getAns()) {
            case "A":
                editTextFilledExposedDropdown.setListSelection(1);
                break;
            case "B":
                editTextFilledExposedDropdown.setListSelection(2);
                break;
            case "C":
                editTextFilledExposedDropdown.setListSelection(3);
                break;
            case "D":
                editTextFilledExposedDropdown.setListSelection(4);
                break;
            default:
                editTextFilledExposedDropdown.setListSelection(0);
                break;
        }
    }

    private void setUpCompetition() {
//        isLive.setChecked(false);
        compName_lay.setVisibility(View.VISIBLE);
        button.setClickable(false);
        slider.setVisibility(View.INVISIBLE);
        slider.setVisibility(View.GONE);
        registerName.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.INVISIBLE);
        cardView.setVisibility(View.GONE);
        editCompName.setVisibility(View.INVISIBLE);
        editCompName.setVisibility(View.GONE);
    }

    public void ActionButton(View view) {

        if (compID != null && checkEmptyNess()) {
            viewDialog.showSnackBar();
            button.setClickable(false);
            SetDataToDatabase con = Conn.doConnect();
            con.Competition("updateQues", "", "", compID, String.valueOf(quesNo), ques_edit.getText().toString(), optA_edit.getText().toString(),
                    optB_edit.getText().toString(), optC_edit.getText().toString(), optD_edit.getText().toString(), optAnswer, "", new Callback<ServerResponse>() {
                        @Override
                        public void success(ServerResponse serverResponse, Response response) {
                            button.setClickable(true);
                            viewDialog.hideSnackBar();
                            if (serverResponse.msg.equals("Updated")) {
                                data.set(quesNo-1,new Competition_bean.UserBean(compID,"","","",String.valueOf(quesNo),ques_edit.getText().toString(),optA_edit.getText().toString(),
                                        optB_edit.getText().toString(),optC_edit.getText().toString(),optD_edit.getText().toString(),optAnswer));
                                Snackbar.make(findViewById(android.R.id.content), serverResponse.msg, Snackbar.LENGTH_LONG).show();
                                modifyQuestionPallet((quesNo + 1));

                            } else
                                Snackbar.make(findViewById(android.R.id.content), serverResponse.msg, Snackbar.LENGTH_LONG).show();
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            button.setClickable(true);
                            viewDialog.hideSnackBar();
                            if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                                Snackbar.make(findViewById(android.R.id.content), "No Internet Connection!\nPlease try again later.", Snackbar.LENGTH_LONG).show();
                            else
                                Snackbar.make(findViewById(android.R.id.content), error.toString() + "", Snackbar.LENGTH_LONG).show();
                        }
                    });
        }

    }

    private boolean checkEmptyNess() {
        if (ques_edit.getText().toString().isEmpty()) {
            ques_lay.setHelperTextEnabled(false);
            ques_lay.setErrorEnabled(true);
            ques_lay.setError("This field cannot be empty!");
            return false;
        }

        if (optA_edit.getText().toString().isEmpty()) {
            optA_lay.setHelperTextEnabled(false);
            optA_lay.setErrorEnabled(true);
            optA_lay.setError("This field cannot be empty!");
            return false;
        }
        if (optB_edit.getText().toString().isEmpty()) {
            optB_lay.setHelperTextEnabled(false);
            optB_lay.setErrorEnabled(true);
            optB_lay.setError("This field cannot be empty!");
            return false;
        }
        if (optC_edit.getText().toString().isEmpty()) {
            optC_lay.setHelperTextEnabled(false);
            optC_lay.setErrorEnabled(true);
            optC_lay.setError("This field cannot be empty!");
            return false;
        }
        if (optD_edit.getText().toString().isEmpty()) {
            optD_lay.setHelperTextEnabled(false);
            optD_lay.setErrorEnabled(true);
            optD_lay.setError("This field cannot be empty!");
            return false;
        }
        if (optAnswer.equals("noSelected") || optAnswer.equals("Select")) {
            Snackbar.make(findViewById(android.R.id.content), "Select correct option for Answer", Snackbar.LENGTH_LONG).show();
            return false;
        }
        return true;
    }


    public void RegisterCompetition(View view) {

        if (compName_edit.getText().length() == 0) {
            compName_lay.setHelperTextEnabled(false);
            compName_lay.setErrorEnabled(true);
            compName_edit.requestFocus();
            compName_lay.setError("Competition Name cannot be empty!");
            return;
        }

        viewDialog.showSnackBar();
        registerName.setClickable(false);
        SetDataToDatabase con = Conn.doConnect();
        con.Competition(registerName.getText().toString() + "Comp", compName_edit.getText().toString(), "", "", "", "", "", "", "", "", "", "", new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                viewDialog.hideSnackBar();
                registerName.setClickable(true);
                if (serverResponse.msg.equals("Done")) {
                    Snackbar.make(findViewById(android.R.id.content), serverResponse.msg, Snackbar.LENGTH_LONG).show();
                    setUpOldCompetition();
                    compID = serverResponse.msg2;
                    getQues(compID);
                } else
                    Snackbar.make(findViewById(android.R.id.content), serverResponse.msg, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideSnackBar();
                registerName.setClickable(true);
                if (error.getKind().equals(RetrofitError.Kind.NETWORK))
                    Snackbar.make(findViewById(android.R.id.content), "No Internet Connection!\nPlease try again later.", Snackbar.LENGTH_LONG).show();
                else
                    Snackbar.make(findViewById(android.R.id.content), error.toString() + "", Snackbar.LENGTH_LONG).show();

            }
        });

    }

    private void setUpOldCompetition() {
        compName_lay.setVisibility(View.INVISIBLE);
        compName_lay.setVisibility(View.GONE);
        button.setClickable(true);
        slider.setVisibility(View.VISIBLE);
        registerName.setVisibility(View.INVISIBLE);
        registerName.setVisibility(View.GONE);
        cardView.setVisibility(View.VISIBLE);
        materialToolbar.setTitle(compName_edit.getText().toString());
        editCompName.setVisibility(View.VISIBLE);
    }

    public void EditCompetitionName(View view) {
        setUpCompetition();
        compName_edit.setText(materialToolbar.getTitle().toString());
        registerName.setText(mode);
    }
}