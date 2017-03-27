package com.example.olmartin2.lecteurmusique;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmetteurActivity extends AppCompatActivity {

    private Button send;
    private EditText keyword;
    private Map<String,String> searchResult = null;

    private Playlist p;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef;
    private ValueEventListener playlistListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emetteur);

        p = new Playlist("ol");

        keyword = (EditText) findViewById(R.id.editText);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final YoutubeManager youtubeManager = new YoutubeManager();

                Context context = getApplicationContext();
                CharSequence text = "Vous n'avez pas saisi de titre.";
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
                        searchResult=youtubeManager.manage(keyword.getText().toString());

                        if(searchResult != null) {
                            Map.Entry entry = (Map.Entry) searchResult.entrySet().iterator().next();
                            String key = (String) entry.getKey();
                            String value = (String) entry.getValue();
                            System.out.println(" video trouvé ID : " + key + "; titre : " + value);
                            dbRef = database.getReference("users").child(R.id.id_party);
                            dbRef.setValue(p);
                        }else{
                            System.err.println("probleme de return");
                        }

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
