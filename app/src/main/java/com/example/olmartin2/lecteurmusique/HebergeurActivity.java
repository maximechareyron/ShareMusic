package com.example.olmartin2.lecteurmusique;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

    static RecyclerView recyclerView ;
    //Liste qui aura tous les ID des videos utilisé par player
    static List<String> playlistID = new ArrayList<>();
    //Liste qui sert a l'affichage pour la recyclerView afin de savoir quelle est le nom de la video
    static List<String> playlistName = new ArrayList<>();

    public Button refreshButton;
    static YouTubePlayer player;

    Button createPlaylistButton;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = database.getReference("users");

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private Host h;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebergeur);

        h = new Host("maxiaus");

        try{
            h.enqueueSong("GRxofEmo3HA", "Four Seasons ~ Vivaldi");
            h.enqueueSong("Rb0UmrCXxVA","Le Meilleur de Mozart");
            h.enqueueSong("Zi8vJ_lMxQI", "Mozart - Requiem");
            h.enqueueSong("vHqtJH2f1Yk", "Gustavo Dudamel : Dvorak - Symphony no. 9 - 4th movement - Allegro con fuoco");
        }
        catch (Exception e){

        }



        createPlaylistButton = (Button) findViewById(R.id.create_playlist);
        createPlaylistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.child(user.getUid()).setValue(h);
            }
        });


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
