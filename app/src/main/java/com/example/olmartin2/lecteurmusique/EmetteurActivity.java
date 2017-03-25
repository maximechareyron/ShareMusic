package com.example.olmartin2.lecteurmusique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.api.services.youtube.YouTube;

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

                Log.d("SYSO", "appel Youtube Manage");
                YoutubeManager.manage();
                Log.d("SYSO", "FIN Youtube manage");

            }
        });
    }
}
