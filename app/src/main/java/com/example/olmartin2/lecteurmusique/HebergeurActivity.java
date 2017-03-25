package com.example.olmartin2.lecteurmusique;


import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

public class HebergeurActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youtubeView;
    Button createPlaylistButton;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("users");

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Host h;

    RecyclerView playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebergeur);

        h = new Host("maxiaus");

        h.addTitle("GRxofEmo3HA");
        h.addTitle("Rb0UmrCXxVA");
        h.addTitle("Zi8vJ_lMxQI");



        createPlaylistButton = (Button) findViewById(R.id.create_playlist);
        createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.child(user.getUid()).setValue(h);
            }
        });

        //playlist = (RecyclerView) findViewById(R.id.recyclerView);




    }




    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        List<String> playlist = new ArrayList<>();
        playlist.add("GRxofEmo3HA");
        playlist.add("Rb0UmrCXxVA");
        playlist.add("Zi8vJ_lMxQI");

        YouTubeBaseActivity y = new YouTubeBaseActivity();


        if(!b){
            youTubePlayer.cueVideos(playlist);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }


}
