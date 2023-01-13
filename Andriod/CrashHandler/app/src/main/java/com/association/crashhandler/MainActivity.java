package com.association.crashhandler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import org.acra.ACRA;
import org.acra.BuildConfig;
import org.acra.config.CoreConfigurationBuilder;
import org.acra.config.MailSenderConfigurationBuilder;
import org.acra.data.StringFormat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ACRA.DEV_LOGGING = true;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        ACRA.init(this, new CoreConfigurationBuilder()
                .withBuildConfigClass(BuildConfig.class)
                .withReportFormat(StringFormat.JSON)
                .withPluginConfigurations(
                        new MailSenderConfigurationBuilder()
                                //required
                                .withMailTo("pbsos38@gmail.com")
                                //defaults to true
                                .withReportAsFile(true)
                                //defaults to ACRA-report.stacktrace
                                .withReportFileName("Crash.txt")
                                //defaults to "<applicationId> Crash Report"
                                .withSubject("Subject")
                                //defaults to empty
                                .withBody("Body")
                                .build()
                )
        );
    }
}