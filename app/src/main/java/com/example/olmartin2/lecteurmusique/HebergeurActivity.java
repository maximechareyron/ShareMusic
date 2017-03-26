package com.example.olmartin2.lecteurmusique;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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
    static RecyclerView recyclerView ;
    //Liste qui aura tous les ID des videos utilisé par player
    static List<String> playlistID = new ArrayList<>();
    //Liste qui sert a l'affichage pour la recyclerView afin de savoir quelle est le nom de la video
    static List<String> playlistName = new ArrayList<>();

    public Button refreshButton;
    static YouTubePlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebergeur);

        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youtubeView.initialize(Config.YOUTUBE_API_KEY,this);

        recyclerView  = (RecyclerView) findViewById(R.id.list_music);

        // Ici on instancie des videos pour le player Youtube, c'est ici qu'il faudra
        // recevoir les vidéos avec firebase et les ajouter aux 2 listes
        playlistName.add("Vivaldi");
        playlistID.add("GRxofEmo3HA");
        playlistName.add("Mozart");
        playlistID.add("Rb0UmrCXxVA");
        playlistName.add("Mozart requiem");
        playlistID.add("Zi8vJ_lMxQI");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(new PlaylistAdaptateur(playlistName,playlistID));



    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

        this.player = youTubePlayer;

        YouTubeBaseActivity y = new YouTubeBaseActivity();


        if(!b){
            player.loadVideos(playlistID);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        System.err.println("ça marche pas :(");
    }

    public static void changeMusic(String id){

        for(int i=0; i<playlistID.size();i++){
            if(playlistID.get(i).equals(id)){
                player.cueVideos(playlistID,i,0);
                player.play();
            }
        }

    }

    public static void deleteMusic(String id){
        int timeSafe = player.getCurrentTimeMillis();
        String videoSafe;

        for(int i=0; i<playlistID.size();i++){
            if(playlistID.get(i).equals(id)){
                playlistID.remove(i);
                playlistName.remove(i);
                recyclerView.setAdapter(new PlaylistAdaptateur(playlistName,playlistID));
                player.loadVideos(playlistID);
                return;
            }
        }
        System.err.println("vidéo a supprimer inconnue");
    }
}
