package com.example.olmartin2.lecteurmusique;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


import com.google.api.services.youtube.*;


import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HebergeurActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    //Button stop_play_button;
    private YouTubePlayerView youtubeView;
    RecyclerView playlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebergeur);

        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youtubeView.initialize(Config.YOUTUBE_API_KEY,this);

        playlist = (RecyclerView) findViewById(R.id.recyclerView);




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
