package com.Prince.forensicexams;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.Prince.forensicexams.Adapter.TestPaperAdapter;
import com.Prince.forensicexams.Adapter.TestResultAdapter;
import com.Prince.forensicexams.BeanFiles.TestQuestion_bean;
import com.Prince.forensicexams.SmallFeatures.ViewDialog;
import com.Prince.forensicexams.server.Conn;
import com.Prince.forensicexams.server.SetDataToDatabase;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Header;

public class Test extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager linearManager;
    TestPaperAdapter adapter;
    String id, subject = "mix";
    int maxQuizId = 0;
    public ArrayList<String> answers;
    public ArrayList<String> attempt;
    public ArrayList<String> choosedAnswer;
    ViewDialog viewDialog;
    RecyclerView recyclerView2;
    LinearLayoutManager linearManager2;
    TestResultAdapter adapter2;
    TextView totalAttempts, right, wrong;

    RelativeLayout testPaperLayout;
    LinearLayout testResultLayout;
    View v;
    ArrayList<TestQuestion_bean.UserBean> data;
    ArrayList<TestQuestion_bean.UserBean> question_res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        MobileAds.initialize(this, initializationStatus -> {});
        recyclerView = findViewById(R.id.recycler_testPaper);
        testPaperLayout = findViewById(R.id.testPaperLayout);
        testResultLayout = findViewById(R.id.testResultLayout);
        Button submit = findViewById(R.id.calcResult);
        Button testAgain = findViewById(R.id.testAgain);
        answers = new ArrayList<>();
        attempt = new ArrayList<>();
        choosedAnswer = new ArrayList<>();
        /*choosedAnswer.add(0, "no");
        choosedAnswer.add(1, "no");
        choosedAnswer.add(2, "no");
        choosedAnswer.add(3, "no");
        choosedAnswer.add(4, "no");
        choosedAnswer.add(5, "no");
        choosedAnswer.add(6, "no");
        choosedAnswer.add(7, "no");
        choosedAnswer.add(8, "no");
        choosedAnswer.add(9, "no");
        choosedAnswer.add(10, "no");
        choosedAnswer.add(11, "no");
        choosedAnswer.add(12, "no");*/

        subject = getIntent().getStringExtra("subject");
        viewDialog = new ViewDialog(this);
        linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);
        submit.setOnClickListener(v -> {
            calcResult();
        });

        ImageView reload = findViewById(R.id.reloadPaper);
        reload.setOnClickListener(v -> {
            reload();
        });
        testAgain.setOnClickListener(v -> {
            reload();
        });
        reload();

    }
    public void fetchPost(int securityKey) {
        testResultLayout.setVisibility(View.INVISIBLE);
        testResultLayout.setVisibility(View.GONE);
        testPaperLayout.setVisibility(View.VISIBLE);
        viewDialog.showDialog();
        question_res = new ArrayList<>();
        SetDataToDatabase con = Conn.doConnect();
        con.fTestQuestions(subject,securityKey, new Callback<TestQuestion_bean>() {

            @Override
            public void success(TestQuestion_bean testQuestion_bean, Response response) {
                data = testQuestion_bean.data;
                if (data.size() <= 3) {
                    Log.d("Tag", "" + data.size());
                    adapter = new TestPaperAdapter(data, Test.this, id, answers, attempt, choosedAnswer);
                    recyclerView.setAdapter(adapter);
                } else if (data.size() <= 7) {
                    data.add(3, new TestQuestion_bean.UserBean("", "ad", "", "", "", "", "", ""));
                    Log.d("Tag", "" + data.size());
                    adapter = new TestPaperAdapter(data, Test.this, id, answers, attempt, choosedAnswer);
                    recyclerView.setAdapter(adapter);
                } else if (data.size() <= 10) {
                    data.add(3, new TestQuestion_bean.UserBean("", "ad", "", "", "", "", "", ""));
                    data.add(7, new TestQuestion_bean.UserBean("", "ad", "", "", "", "", "", ""));
                    Log.d("Tag", "" + data.size());
                    adapter = new TestPaperAdapter(data, Test.this, id, answers, attempt, choosedAnswer);
                    recyclerView.setAdapter(adapter);
                } else
                    reload();
                viewDialog.hideDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(Test.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Test.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void calcResult() {
        //runFbADs();
        Log.d("answers", "" + answers.toString());
        Log.d("attempt", "" + attempt.toString());
     /*   Intent intent = new Intent(TestPaper.this, TestResult.class);
        intent.putCharSequenceArrayListExtra("answers",answers);
        intent.putCharSequenceArrayListExtra("attempt",attempt);
        startActivity(intent);*/

        testPaperLayout.setVisibility(View.INVISIBLE);
        testPaperLayout.setVisibility(View.GONE);
        testResultLayout.setVisibility(View.VISIBLE);
        totalAttempts = findViewById(R.id.justText_testresult_totalAttempts);
        right = findViewById(R.id.justText_testresult_right);
        wrong = findViewById(R.id.justText_testresult_wrong);

        recyclerView2 = findViewById(R.id.recycler_testResult);
        linearManager2 = new LinearLayoutManager(Test.this);
        recyclerView2.setLayoutManager(linearManager2);

        Log.d("answer", "" + choosedAnswer);
        int x = 0;
        for (int r = 0; r < attempt.size(); r++) {
            if (!attempt.get(r).equals("")) {
                x++;
            }
            totalAttempts.setText("Total Attempts are: " + x);
        }
        int correct = 0;
        int incorrect = 0;
        for (int i = 0; i < answers.size(); i++) {
            int r = i + 1;
            if (answers.get(i).equals("correct at " + i)) {
                correct++;
            }
            if (answers.get(i).equals("incorrect at " + i)) {
                incorrect++;
            }
            right.setText("Total Correct answers: " + correct);
            wrong.setText("Total Wrong answers: " + incorrect);

        }
        fetchPost2();

    }


    public void fetchPost2() {
        int corrrectness = 0;
        viewDialog.showDialog();
        adapter2 = new TestResultAdapter(data, Test.this, answers, attempt, choosedAnswer);
        recyclerView2.setAdapter(adapter2);
        viewDialog.hideDialog();
    }


    private void reload() {
        Random rand = new Random();
        int n = 0;
            n = rand.nextInt(200);
        choosedAnswer.clear();
        answers.clear();
        attempt.clear();
        choosedAnswer.add(0, "no");
        choosedAnswer.add(1, "no");
        choosedAnswer.add(2, "no");
        choosedAnswer.add(3, "no");
        choosedAnswer.add(4, "no");
        choosedAnswer.add(5, "no");
        choosedAnswer.add(6, "no");
        choosedAnswer.add(7, "no");
        choosedAnswer.add(8, "no");
        choosedAnswer.add(9, "no");
        choosedAnswer.add(10, "no");
        choosedAnswer.add(11, "no");
        choosedAnswer.add(12, "no");
        fetchPost(n);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        data.clear();
        finish();
    }

}