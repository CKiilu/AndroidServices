package com.scurrae.chris.androidservices;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(getBaseContext(), HelloService.class));
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(getBaseContext(), HelloService.class));
    }
}
