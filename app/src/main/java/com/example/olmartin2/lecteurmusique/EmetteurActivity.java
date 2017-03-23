package com.example.olmartin2.lecteurmusique;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.api.services.youtube.YouTube;

import java.util.Arrays;
import java.util.List;

public class EmetteurActivity extends AppCompatActivity {

    Button send;
    EditText keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emetteur);

        keyword = (EditText) findViewById(R.id.editText);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final YoutubeManager youtubeManager = new YoutubeManager();

                Context context = getApplicationContext();
                CharSequence text = "Vous n'avez pas saisie de titre.";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);

                if(keyword.getText().toString().equals("")) {
                    toast.show();
                    return;
                }


                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        /*final List<Result> results = */
                        youtubeManager.manage(keyword.getText().toString());


                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                //s'execute dans le main thread

                            }
                        });
                    }
                };
                new Thread(runnable).start();

            }
        });
    }
}
