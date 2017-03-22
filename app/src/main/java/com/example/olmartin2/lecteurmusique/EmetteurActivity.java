package com.example.olmartin2.lecteurmusique;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.api.services.youtube.YouTube;

import java.util.Arrays;
import java.util.List;

public class EmetteurActivity extends AppCompatActivity {

    Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emetteur);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final YoutubeManager youtubeManager = new YoutubeManager();

                Log.d("appel Youtube manage","");
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        /*final List<Result> results = */
                        youtubeManager.manage();


                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                //s'execute dans le main thread

                            }
                        });
                    }
                };
                new Thread(runnable).start();
                Log.d("fin appel Youtube manage","");

            }
        });
    }
}
