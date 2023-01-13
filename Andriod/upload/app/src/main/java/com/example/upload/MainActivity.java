package com.example.upload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.upload.server.Conn;
import com.example.upload.server.Connection;
import com.example.upload.server.ServerResponse;
import com.example.upload.server.SetDataToDatabase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> name;
    ArrayList<String> image;
    ArrayList<String> postlink;
    ArrayList<String> ques, optA, optB, optC, optD, chooseAnswer;
    boolean check = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        name = new ArrayList<>();
        postlink = new ArrayList<>();
        image = new ArrayList<>();
        image = new ArrayList<>();
        ques = new ArrayList<>();
        optA = new ArrayList<>();
        optB = new ArrayList<>();
        optC = new ArrayList<>();
        optD = new ArrayList<>();
        chooseAnswer = new ArrayList<>();
        //image.add("    ENTER IMAGE NAME   ");
        //postlink.add(" ENTER POST LINK    ");
        //      "


        //for else
        {
            /*int xx = 0;
            while (xx != name.size()) {
                if (check) {
                    Log.d("name", "Location: " + xx + "  Name: " + name.get(xx));
                    //int r = xx + 6;
                    //String piccc = r +".jpg";
                    //Log.d("Image", "Location: " + xx + "  Image: " + image.get(xx));
                    Log.d("Link", "Location: " + xx + "  Name: " + postlink.get(xx));
                    Log.d("status", "upload starting for Location: " + xx);

                    //start(image.get(xx), name.get(xx), postlink.get(xx));
                    // startCourse(name.get(xx));
                    //video(name.get(xx), postlink.get(xx));
                    xx++;
                } else
                    Log.d("Error", "stopped at: " + xx);
            }*/
        }
        //for questions
        {
            Log.d("Total Length", String.valueOf(ques.size()));
            int xx = 96;
//            while (xx != ques.size() )
            {
                if (check) {
                    Log.d("name", "Location: " + xx + "  ques: " + ques.get(xx));
                    Log.d("name", "Location: " + xx + "  A: " + optA.get(xx));
                    Log.d("name", "Location: " + xx + "  B: " + optB.get(xx));
                    Log.d("name", "Location: " + xx + "  C: " + optC.get(xx));
                    Log.d("name", "Location: " + xx + "  D: " + optD.get(xx));
                    Log.d("name", "Location: " + xx + "  ANS: " + chooseAnswer.get(xx));
                    Log.d("status", "Upload starting for Location: " + xx);
                    try {
                        Thread.sleep(3000);
                        uploadquestions(ques.get(xx), optA.get(xx), optB.get(xx), optC.get(xx), optD.get(xx), chooseAnswer.get(xx),xx);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    xx++;
                } else
                    Log.d("myError#455", "stopped at: " + xx);
            }
        }
    }


    public void start(String pic, String name, String link) {
        SetDataToDatabase con = Conn.doConnect();
        //TypedFile imgpath = new TypedFile("image/*", new File(filePath));
        con.createPost(pic, name, link, "college", new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                if (serverResponse.msg != "Uploading Completed.") {
                    Log.d("myError", "" + serverResponse.msg);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("Error", error.toString());

            }
        });
    }

    public void startCourse(String name) {
        SetDataToDatabase c = Connection.doConnect();
        c.addCourse(name, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                if (!serverResponse.msg.equals("Course Registered.")) {
                    Log.d("myError", "" + serverResponse.msg);
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("myError", error.toString());
            }
        });
    }

    public void video(String name, String link) {
        SetDataToDatabase con = Conn.doConnect();
        con.addVideo(link, name, new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                if (!serverResponse.msg.equals("Ugc Net/Jrf.")) {
                    Log.d("myError", "" + serverResponse.msg);
                }
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

    }

    public void uploadquestions(String ques, String optA, String optB, String optC, String optD, String ans,int xx) {
        SetDataToDatabase con = Conn.doConnect();
        con.addQuizQuestion(ques, optA, optB, optC, optD, ans, "Fingerprints", new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                if (serverResponse.msg.equals("Uploading Completed.")) {
                    Log.d("myErrror#456 At " + xx, "" + serverResponse.msg);
                } else
                    Log.d("myErrror#457 At " + xx, "" + serverResponse.msg);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("myErrror#458 At " + xx, "" + error);

            }
        });
    }
        /*ques.add("  ");
        optA.add("");
        optB.add("");
        optC.add("");
        optD.add("");

        {

            chooseAnswer.add("A");
            //or
            chooseAnswer.add("B");
            //or
            chooseAnswer.add("C");
            //or
            chooseAnswer.add("D");
        }
        {

            chooseSubject.add("Forensic Biology & serology");
            //or
            chooseAnswer.add("Forensic chemistry & Toxicology");
            //or
            chooseAnswer.add("Forensic Ballistics & Explosives");
            //or
            chooseAnswer.add("Physical Evidences");
            //or
            chooseSubject.add("Crime Scene Investigation");
            //or
            chooseAnswer.add("Question Documents");
            //or
            chooseAnswer.add("Fingerprint Examination");
            //or
            chooseAnswer.add("Forensic Medicine");
            //or
            chooseAnswer.add("Digital Forensic");
            //or
            chooseAnswer.add("Cyber Forensic");
        }*/

}
//   “
//   ”
// \\ -> /