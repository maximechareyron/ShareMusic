package com.example.olmartin2.lecteurmusique;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button emetteur;
    Button hebergeur;
    Button manageAcc;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emetteur = (Button) findViewById(R.id.emetteur);
        emetteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EmetteurActivity.class));

            }
        });

        hebergeur = (Button) findViewById(R.id.hebergeur);
        hebergeur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HebergeurActivity.class));

            }
        });

        manageAcc = (Button) findViewById(R.id.manage_acc);
        manageAcc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this, ManageAccActivity.class));

            }
        });


    }


}
