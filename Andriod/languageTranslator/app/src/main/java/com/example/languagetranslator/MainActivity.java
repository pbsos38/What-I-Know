package com.example.languagetranslator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.ml.common.modeldownload.FirebaseModelDownloadConditions;
import com.google.firebase.ml.naturallanguage.FirebaseNaturalLanguage;
import com.google.firebase.ml.naturallanguage.languageid.FirebaseLanguageIdentification;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslateLanguage;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslator;
import com.google.firebase.ml.naturallanguage.translate.FirebaseTranslatorOptions;

import org.w3c.dom.Text;

import java.util.Locale;
//https://www.youtube.com/watch?v=_so7BRd-sHw
public class MainActivity extends AppCompatActivity {

    private TextView mSourceLang;
    private EditText mSourcetext;
    private Button mtranslatebtn;
    private TextView mTranslatedText;
    String sourcetext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setLocalee("bg");
        //MyApp.setLocaleFa(MainActivity.this);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        mSourceLang = findViewById(R.id.sourceLang);
        mSourcetext = findViewById(R.id.sourcetext);
        mtranslatebtn = findViewById(R.id.translate);
        mTranslatedText = findViewById(R.id.translatedtext);

        mtranslatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indentifyLanguage();
            }
        });
    }

    private void indentifyLanguage() {

        sourcetext = mSourcetext.getText().toString();
        /*FirebaseLanguageIdentification identification = FirebaseNaturalLanguage.getInstance().getLanguageIdentification();

        identification.identifyLanguage(sourcetext).addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                if (s.equals("umd")){
                    Toast.makeText(MainActivity.this, "Language Not identified", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this, ""+s, Toast.LENGTH_SHORT).show();
            }
        });
*/
        mSourceLang.setText("Detecting..");
        FirebaseLanguageIdentification languageIdentifier =
                FirebaseNaturalLanguage.getInstance().getLanguageIdentification();
        languageIdentifier.identifyLanguage(sourcetext)
                .addOnSuccessListener(
                        new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(@Nullable String languageCode) {
                                if (languageCode != "und") {
                                    Log.i("TAG", "Language: " + languageCode);
                                    assert languageCode != null;
                                   // getLanguageCode(languageCode);
                                    setLocalee("hi");

                                } else {
                                    Log.i("TAG", "Can't identify language.");
                                }
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be loaded or other internal error.
                                // ...
                            }
                        });


    }

    private void getLanguageCode(String s) {
        int langCode;
        switch (s) {
            case "hi":
                langCode = FirebaseTranslateLanguage.HI;
                break;
            case "ar":
                langCode = FirebaseTranslateLanguage.AR;
                break;
            case "ur":
                langCode = FirebaseTranslateLanguage.UR;
                break;
            case "en":
                langCode = FirebaseTranslateLanguage.EN;
                break;
            default:
                langCode =0;

        }

        translateText(langCode);

    }

    private void translateText(int langCode) {
        FirebaseTranslatorOptions options = new FirebaseTranslatorOptions.Builder()
                //fromLanguage
                .setSourceLanguage(FirebaseTranslateLanguage.EN)
                //to language
                .setTargetLanguage(FirebaseTranslateLanguage.HI).build();
        final FirebaseTranslator translator = FirebaseNaturalLanguage.getInstance().getTranslator(options);

        FirebaseModelDownloadConditions conditions = new FirebaseModelDownloadConditions.Builder().build();

        translator.downloadModelIfNeeded(conditions).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                translator.translate(sourcetext).addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        mTranslatedText.setText(s);

                    }
                });
            }
        });
    }


   /* public static void setLocale(Activitycontext) {
        Locale locale;
        Sessions session = new Sessions(context);
        //Log.e("Lan",session.getLanguage());
        locale = new Locale(langCode);
        Configuration config = new Configuration(context.getResources().getConfiguration());
        Locale.setDefault(locale);
        config.setLocale(locale);

        context.getBaseContext().getResources().updateConfiguration(config,
                context.getBaseContext().getResources().getDisplayMetrics());
    }*/
    public void setLocalee(String lang) {
        Locale myLocale = new Locale(lang);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();


        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        //Intent refresh = new Intent(this, MainActivity.class);
        //finish();
        //startActivity(refresh);
    /*if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN_MR1){
        conf.setLocale(new Locale(lang.toLowerCase()));
    }
    else
        conf.locale = new Locale(lang.toLowerCase());

    res.updateConfiguration(conf,dm);
*/
    }
}
