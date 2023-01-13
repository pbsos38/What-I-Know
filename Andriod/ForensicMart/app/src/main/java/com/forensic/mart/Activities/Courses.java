package com.forensic.mart.Activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.forensic.mart.Adapter.CourseAdapter;
import com.forensic.mart.BeanFiles.Courses_bean;
import com.forensic.mart.R;
import com.forensic.mart.server.Conn;
import com.forensic.mart.server.SetDataToDatabase;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Courses extends AppCompatActivity {

    RecyclerView recyclerView;
    CourseAdapter adapter;
    LinearLayoutManager linearManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        recyclerView = findViewById(R.id.recycler_courses);
        linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);
        fetch();
    }

    private void fetch(){
        SetDataToDatabase con = Conn.doConnect();
        con.fetchCourses(new Callback<Courses_bean>() {
            @Override
            public void success(Courses_bean courses_bean, Response response) {
                adapter = new CourseAdapter(courses_bean.data, Courses.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                if(error.getKind().equals(RetrofitError.Kind.NETWORK))
                {
                    Toast.makeText(Courses.this, "No Internet connection found.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Courses.this, error.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}