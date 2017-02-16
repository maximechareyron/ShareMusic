package com.example.olmartin2.lecteurmusique;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HebergeurActivity extends AppCompatActivity {

    Button stop_play_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebergeur);

        stop_play_button = (Button) findViewById(R.id.stop_play_button);

        stop_play_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(stop_play_button.getText().equals("stop")){
                    stop_play_button.setText("lecture");
                }
                else stop_play_button.setText("stop");

            }
        });
    }
}
