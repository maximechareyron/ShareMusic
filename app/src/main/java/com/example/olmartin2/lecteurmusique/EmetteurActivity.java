package com.example.olmartin2.lecteurmusique;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
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
    private EditText party;

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
        party = (EditText) findViewById(R.id.id_party);

        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final YoutubeManager youtubeManager = new YoutubeManager();

                if(keyword.getText().toString().trim().isEmpty()){
                    showToast(getString(R.string.no_title));
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

                            //CharSequence text = value + getString(R.string.song_added);
                            showToast(value + " "+ getString(R.string.song_added));
                            Log.d("SYSO", "video trouvée ID : " + key + "; titre : " + value);

                            //dbRef = database.getReference("users").child("rKJZqDMk0lfomuFRxK08p1Z80xV2");

                            /*dbRef.setValue(p);
                            try {
                                p.enqueueSong(key, value);
                            }
                            catch(Exception e){

                            }
                            */

                        }else{
                            showToast(getString(R.string.no_title_found));
                            Log.d("SYSO","probleme de return");
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
                keyword.setText("");
            }
        });
    }

    // Permet d'afficher des Toasts depuis n'importe quel thread
    public void showToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            public void run()
            {
                Toast.makeText(EmetteurActivity.this, toast, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onStart(){
        super.onStart();
        /*
        playlistListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                p = dataSnapshot.getValue(Playlist.class);
                // ...
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("ERR", "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        dbRef.addValueEventListener(playlistListener);
        */
    }
}
