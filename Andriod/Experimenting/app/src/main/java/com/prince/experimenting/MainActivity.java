package com.prince.experimenting;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.IOException;

import io.github.ponnamkarthik.richlinkpreview.MetaData;
import io.github.ponnamkarthik.richlinkpreview.ResponseListener;
import io.github.ponnamkarthik.richlinkpreview.RichLinkView;
import io.github.ponnamkarthik.richlinkpreview.RichPreview;
import io.github.ponnamkarthik.richlinkpreview.ViewListener;

public class MainActivity extends AppCompatActivity {
    RichLinkView richLinkView;
    //PDFView pdfView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*LayoutInflater inf = LayoutInflater.from(this);
        View vw = inf.inflate(R.layout.test_inflator, null);
         AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlg.setView(vw);
        dlg.setCancelable(true);
        Button button=vw.findViewById(R.id.btn);
        final AlertDialog dialog = dlg.create();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
*/
        richLinkView = (RichLinkView) findViewById(R.id.richLinkView);



        richLinkView.setLink("https://stackoverflow.com", new ViewListener() {

            @Override
            public void onSuccess(boolean status) {

            }

            @Override
            public void onError(Exception e) {

            }
        });

        MetaData data;
        RichPreview richPreview= new RichPreview(new ResponseListener() {
            @Override
            public void onData(MetaData metaData) {
                Toast.makeText(MainActivity.this, ""+metaData.getTitle(), Toast.LENGTH_SHORT).show();
                //Implement your Layout
            }

            @Override
            public void onError(Exception e) {
                Toast.makeText(MainActivity.this, ""+e.toString(), Toast.LENGTH_SHORT).show();
                //handle error
            }
        });


        // pdf viewing
        //pdfView.fromFile("https://www.forensicmart.com/uploads/5c0baef9d70a8_1544269561.pdf");
    }
    public void pdf(View view){
        //startActivity(new Intent(this,LoadPdf.class));
        PDFTools pdfTools =  new PDFTools();
        pdfTools.showPDFUrl(this,"5c0175cae6cd5_1543599562.pdf");
    }



}