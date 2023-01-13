package com.forensic.mart.Activities.Fr;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.forensic.mart.Adapter.TestPaperAdapter;
import com.forensic.mart.Adapter.TestResultAdapter;
import com.forensic.mart.BeanFiles.TestQuestion_bean;
import com.forensic.mart.BeanFiles.TestQuestion_bean_result;
import com.forensic.mart.R;
import com.forensic.mart.SmallFeatures.ViewDialog;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.ServerResponse;
import com.forensic.mart.server.SetDataToDatabase;

import java.util.ArrayList;
import java.util.Random;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Test extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearManager;
    TestPaperAdapter adapter;
    String id;
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

    ArrayList<TestQuestion_bean.UserBean> question_res;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_test, container, false);

        recyclerView = v.findViewById(R.id.recycler_testPaper);
        testPaperLayout = v.findViewById(R.id.testPaperLayout);
        testResultLayout = v.findViewById(R.id.testResultLayout);
        Button submit = v.findViewById(R.id.calcResult);
        Button testAgain = v.findViewById(R.id.testAgain);
        answers = new ArrayList<>();
        attempt = new ArrayList<>();
        choosedAnswer = new ArrayList<>();
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

        maxQuizid();
        viewDialog = new ViewDialog(getActivity());
        linearManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearManager);
        submit.setOnClickListener(v -> {
            calcResult();
        });

        ImageView reload = v.findViewById(R.id.reloadPaper);
        reload.setOnClickListener(v -> {
            reload();
        });
        testAgain.setOnClickListener(v -> {
            reload();
        });
        reload();
        return v;
    }

    public void fetchPost(int quizId) {
        testResultLayout.setVisibility(View.INVISIBLE);
        testResultLayout.setVisibility(View.GONE);
        testPaperLayout.setVisibility(View.VISIBLE);
        viewDialog.showDialog();
        question_res = new ArrayList<>();
        SetDataToDatabase con = Conn.doConnect();
        con.fTestQuestions(quizId, new Callback<TestQuestion_bean>() {
            @Override
            public void success(TestQuestion_bean testQuestion_bean, Response response) {
                ArrayList<TestQuestion_bean.UserBean> data = testQuestion_bean.data;
                if (data.size() != 0) {
                    data.add(3, new TestQuestion_bean.UserBean(0, 0, "ad", "", "", "", "", "", "", "", ""));
                    data.add(7, new TestQuestion_bean.UserBean(0, 0, "ad", "", "", "", "", "", "", "", ""));
                    Log.d("Tag", "" + data.size());
                    adapter = new TestPaperAdapter(data, getActivity(), id, answers, attempt, choosedAnswer);
                    id = String.valueOf(quizId);
                    recyclerView.setAdapter(adapter);
                } else reload();
                viewDialog.hideDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(getActivity(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void calcResult() {

        Log.d("answers", "" + answers.toString());
        Log.d("attempt", "" + attempt.toString());
     /*   Intent intent = new Intent(TestPaper.this, TestResult.class);
        intent.putCharSequenceArrayListExtra("answers",answers);
        intent.putCharSequenceArrayListExtra("attempt",attempt);
        startActivity(intent);*/

        testPaperLayout.setVisibility(View.INVISIBLE);
        testPaperLayout.setVisibility(View.GONE);
        testResultLayout.setVisibility(View.VISIBLE);
        totalAttempts = v.findViewById(R.id.justText_testresult_totalAttempts);
        right = v.findViewById(R.id.justText_testresult_right);
        wrong = v.findViewById(R.id.justText_testresult_wrong);

        recyclerView2 = v.findViewById(R.id.recycler_testResult);
        linearManager2 = new LinearLayoutManager(getActivity());
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
        SetDataToDatabase con = Conn.doConnect();
        con.fTestQuestions_result(id, new Callback<TestQuestion_bean_result>() {
            @Override
            public void success(TestQuestion_bean_result testQuestion_bean, Response response) {
                ArrayList<TestQuestion_bean_result.UserBean> data = testQuestion_bean.data;
                data.add(3, new TestQuestion_bean_result.UserBean(0, 0, "ad", "", "", "", "", "", "", "", ""));
                data.add(7, new TestQuestion_bean_result.UserBean(0, 0, "ad", "", "", "", "", "", "", "", ""));

                adapter2 = new TestResultAdapter(data, getActivity(), corrrectness, answers, attempt, choosedAnswer);
                recyclerView2.setAdapter(adapter2);
                viewDialog.hideDialog();
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(getActivity(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void maxQuizid() {
        SetDataToDatabase con = Conn.doConnect();
        con.maxQuizId(new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                maxQuizId = Integer.parseInt(serverResponse.msg);
            }

            @Override
            public void failure(RetrofitError error) {
                viewDialog.hideDialog();
                if (error.getKind().equals(RetrofitError.Kind.NETWORK)) {
                    Toast.makeText(getActivity(), "No Internet connection found.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void reload() {
        Random rand = new Random();
        int n = 0;
        if (maxQuizId == 0) {
            n = rand.nextInt(53);
        } else
            n = rand.nextInt(maxQuizId);

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
}