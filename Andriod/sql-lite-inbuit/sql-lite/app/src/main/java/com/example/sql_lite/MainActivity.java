package com.example.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText name,surname,marks,id;
    Button add,fetch,update,delete,count,distinct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);

        name = (EditText)findViewById(R.id.name);
        surname = (EditText)findViewById(R.id.surname);
        marks = (EditText)findViewById(R.id.marks);
        id = (EditText)findViewById(R.id.id);
        add = (Button)findViewById(R.id.btnadd);
        fetch = (Button)findViewById(R.id.btnfetch);
        update = (Button)findViewById(R.id.btnupdate);
        delete = (Button)findViewById(R.id.btndelete);
        count = findViewById(R.id.btncount);
        distinct = findViewById(R.id.distinctQuery);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 boolean isInserted = mydb.insertData(name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isInserted == true)
                    Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "not Inserted", Toast.LENGTH_SHORT).show();
            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.fetchData();
                if(res.getCount() == 0)
                {
                    //Toast.makeText(MainActivity.this, "no data found!", Toast.LENGTH_SHORT).show();
                    showMessage("Error","No data found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("id: "+res.getString(0)+"\n");
                    buffer.append("name: "+res.getString(1)+"\n");
                    buffer.append("surname: "+res.getString(2)+"\n");
                    buffer.append("Marks: "+res.getString(3)+"\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = mydb.updateData(id.getText().toString(),name.getText().toString(),surname.getText().toString(),marks.getText().toString());
                if(isUpdated == true)
                {
                    Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, "Not updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = mydb.deleteData(id.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(MainActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int profile_counts = mydb.getProfilesCount();
                mydb.close();
                count.setText(String.valueOf(profile_counts));
            }
        });

        distinct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.distictvalues();
                if(res.getCount() == 0)
                {
                    //Toast.makeText(MainActivity.this, "no data found!", Toast.LENGTH_SHORT).show();
                    showMessage("Error","No data found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append("marks:"+res.getString(0)+"\n");
                }
                showMessage("Data", buffer.toString());
            }
        });

    }

    public void showMessage(String title,String Message)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }
}
// not case senstive
// to check data base just click on device file explorer in right bottom corner and then locate to data/data/sql_lite(project name)/databases
