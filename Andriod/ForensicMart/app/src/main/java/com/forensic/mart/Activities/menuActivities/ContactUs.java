package com.forensic.mart.Activities.menuActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.forensic.mart.R;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
    }

    public void contactUs(View view) {
        String[] to = new String[]{"forensicmart@gmail.com"};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        String sub = "";
        String body = "";
        email.setType("text/plain");
        email.putExtra(Intent.EXTRA_EMAIL, to);
        email.putExtra(Intent.EXTRA_SUBJECT, "Subject:" + sub);
        email.putExtra(Intent.EXTRA_TEXT, "Body:" + body);
        email.setType("message/rfc822");//mailing apps only
        startActivity(Intent.createChooser(email, "Choose an email client from..."));
    }

    public void openWebsite(View view) {
        Uri uriUrl = Uri.parse("https://www.forensicmart.com/dashboard");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    /*public void Whatsapp(View view) {
        try {
            Uri uri = Uri.parse("smsto:+" + "917986512092");
            Intent it = new Intent();
            it.setAction(Intent.ACTION_SENDTO);
            it.setData(uri);
            it.setPackage("com.whatsapp");
            it.putExtra("chat", true);
            startActivity(it);
        } catch (Exception e) {
            Uri uriUrl = Uri.parse("https://play.google.com/store/apps/details?id=com.whatsapp");
            Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
            startActivity(launchBrowser);
        }
    }*/

    public void Linkedin(View view){
        Uri uriUrl = Uri.parse("https://www.linkedin.com/company/68365586/");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void likeOnfb(View view) {
        Uri uriUrl = Uri.parse("https://www.facebook.com/Forensic-Mart-1119340918230840");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }

    public void LikeOninsta(View view) {
        Uri uriUrl = Uri.parse("https://www.instagram.com/forensic_mart/");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void youtube(View view) {
        Uri uriUrl = Uri.parse("https://www.youtube.com/c/OrmapForensicMartsPrivatelimited");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public void Rateus(View view) {
        Uri uriUrl = Uri.parse("https://g.page/forensicmart/review?gm");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


    public void twitter(View view){
        Uri uriUrl = Uri.parse("https://twitter.com/ForensicMart");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);

    }
}