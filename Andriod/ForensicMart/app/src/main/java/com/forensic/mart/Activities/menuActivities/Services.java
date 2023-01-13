package com.forensic.mart.Activities.menuActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.forensic.mart.Activities.menuActivities.menu_adapter.ServicesAdapter;
import com.forensic.mart.R;

public class Services extends AppCompatActivity {
    private String[] services = {"Finger Print Matching", "Hand writing Examination", "Signature Verification ",
            "Document Examination ", "Cross Examination", "Crime scene Investigation", "Audio Examination", "Video Examination",
            "Photograph Verification", "Certificate Verification", "Finger Print Development", "Mobile Investigation", "DNA Profiling",
            "Forensic Biology", "Insurance Investigation", "Employ Verification", "Image analysis", "Forensic Consultation", "Forensic Trainings",
            "Forensic Conferences & Seminars", "Forensic Workshops & Trainings", "Competitive Exams", "Certified Courses", "Physical Evidence Examination",
            "Background Check", "Cyber Forensic Investigation", "Data recovery from laptop, desktop, portable hard drive, pen drive, memory card etc.",
            "Collection of digital evidence from crime scene.", "Password recovery", "Email analysis", "Internet artifacts analysis",
            "Live system analysis for vulnerability", "Volatility analysis", "Vulnerability testing and penetration testing (VA- PT) of Web application, Mobile application, Devices",
            "Source code review", "Implementation of IT security", "Implementation of ISO 27001", "IT audits", "Cloud security", "Financial fraud investigation",
            "Undercover operations", "Insurance investigation", "Fraud investigation"};

    ServicesAdapter adapter;
    RecyclerView recycler;
    LinearLayoutManager linearManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);
        recycler = (RecyclerView) findViewById(R.id.main_recycler);
        linearManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(linearManager);

        adapter = new ServicesAdapter(services, Services.this);
        recycler.setAdapter(adapter);


    }
}