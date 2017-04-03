package com.example.olmartin2.lecteurmusique;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class EmetteurActivity extends AppCompatActivity {

    private Button send;
    private EditText keyword;
    private EditText party;

    private Map<String,String> searchResult = null;


    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emetteur);

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

                if(party.getText().toString().trim().isEmpty()){
                    showToast(getString(R.string.no_party));
                    return;
                }

                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        searchResult=youtubeManager.manage(keyword.getText().toString());

                        if(searchResult != null) {
                            Map.Entry entry = (Map.Entry) searchResult.entrySet().iterator().next();
                            final String key = (String) entry.getKey();
                            final String value = (String) entry.getValue();

                            String size;

                            showToast(value + " "+ getString(R.string.song_added));
                            Log.d("SYSO", "video trouvée ID : " + key + "; titre : " + value);


                            dbRef = database.getReference("users").child(party.getText().toString());


                            dbRef.child("size").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String taille = dataSnapshot.getValue().toString();
                                    int i = Integer.parseInt(taille);
                                    i++;
                                    dbRef.child("size").setValue(String.valueOf(i));

                                    Map<String, Object> childUpdates = new HashMap<>();
                                    childUpdates.put("/playlistLink/" + taille,key);

                                    childUpdates.put("/playlistTitle/" + taille,value);
                                    dbRef.updateChildren(childUpdates);

                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
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
    }
}
