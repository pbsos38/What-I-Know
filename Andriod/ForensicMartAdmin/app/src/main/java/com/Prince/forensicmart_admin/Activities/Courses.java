package com.Prince.forensicmart_admin.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.Prince.forensicmart_admin.Adapter.CourseAdapter;
import com.Prince.forensicmart_admin.Bean.Courses_bean;
import com.Prince.forensicmart_admin.R;
import com.Prince.forensicmart_admin.server.Conn;
import com.Prince.forensicmart_admin.server.SetDataToDatabase;
import com.google.android.material.appbar.MaterialToolbar;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class Courses extends AppCompatActivity {

   private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private LinearLayoutManager linearManager;
    private MaterialToolbar materialToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);
        materialToolbar = findViewById(R.id.topAppBar);
        recyclerView = findViewById(R.id.recycler_courses);
        linearManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearManager);
        fetch();

        materialToolbar.setNavigationOnClickListener(v->{
            super.onBackPressed();
        });
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