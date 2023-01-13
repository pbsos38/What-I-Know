package com.Prince.forensicexams;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class TestList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_test_list2);

        AdView mAdView = findViewById(R.id.adView);
        MobileAds.initialize(this, getString(R.string.admob_app_id));
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


    }

    public void a0(View view) {
        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "mix");
        startActivity(intent);
    }

    public void a(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Crime scene Investigation");
        startActivity(intent);
    }

    public void b(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "History and Development");
        startActivity(intent);
    }

    public void c(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Forensic Chemistry and Toxicology");
        startActivity(intent);
    }

    public void d(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Forensic Ballistics and Explosives");
        startActivity(intent);
    }

    public void e(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Question Documents");
        startActivity(intent);
    }

    public void f(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Instrumental Analysis");
        startActivity(intent);
    }

    public void g(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Physical Evidences");
        startActivity(intent);
    }

    public void h(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Forensic Medicine");
        startActivity(intent);
    }

    public void i(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "History of Indian Forensic");
        startActivity(intent);
    }

    public void j(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Fingerprint Examination");
        startActivity(intent);
    }

    public void k(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Forensic psychology");
        startActivity(intent);
    }

    public void l(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Anthropology");
        startActivity(intent);
    }

    public void m(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Digital Forensic");
        startActivity(intent);
    }

    public void n(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Forensic Biology and Serology");
        startActivity(intent);
    }

    public void o(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Facial");
        startActivity(intent);
    }

    public void p(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Criminal Investigation");
        startActivity(intent);
    }

    public void q(View view) {

        Intent intent = new Intent(TestList.this, Test.class);
        intent.putExtra("subject", "Fingerprints");
        startActivity(intent);
    }


}