package com.example.olmartin2.lecteurmusique;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HebergeurActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private YouTubePlayerView youtubeView;

    static RecyclerView recyclerView ;

    static YouTubePlayer player;

    private TextView linkToShare;
    private ImageButton addButton;

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();

    private DatabaseReference dbRef = database.getReference("users").child(user.getUid());
    private static Playlist p;
    private ValueEventListener playlistListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hebergeur);

        linkToShare = (TextView) findViewById(R.id.userID);
        linkToShare.setText(getString(R.string.link_to_playlist) + " : " + user.getUid());

        addButton = (ImageButton) findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("Bouton PLUS");
                //builder.show();
            }
        });

        p = new Playlist();

        youtubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);

        recyclerView  = (RecyclerView) findViewById(R.id.list_music);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new PlaylistAdaptateur(p.getPlaylistTitle(), p.getPlaylistLink()));

    }

    @Override
    public void onStart(){
        super.onStart();



        playlistListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Playlist tmp = dataSnapshot.getValue(Playlist.class);
                if(tmp == null){
                    try {
                        p.enqueueSong("vHqtJH2f1Yk", "Gustavo Dudamel : Dvorak - Symphony no. 9 - 4th movement - Allegro con fuoco");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    dbRef.setValue(p);
                }
                else{
                     p = tmp;
                }
                recyclerView.setAdapter(new PlaylistAdaptateur(p.getPlaylistTitle(), p.getPlaylistLink()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("ERR", "loadPost:onCancelled", databaseError.toException());
            }
        };
        dbRef.addValueEventListener(playlistListener);

        youtubeView.initialize(Config.YOUTUBE_API_KEY,this);
    }


    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        player = youTubePlayer;
        YouTubeBaseActivity y = new YouTubeBaseActivity();
        if(!b){
            player.loadVideos(p.getPlaylistLink());
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Log.d("SYSO", "Erreur initialisation yt player");
    }

    public static void changeMusic(String id){
        for(int i = 0; i< p.getPlaylistLink().size(); i++){
            if(p.getPlaylistLink().get(i).equals(id)){
                player.cueVideos(p.getPlaylistLink(),i,0);
                player.play();
            }
        }
    }

    public static void deleteMusic(String id){
        int timeSafe = player.getCurrentTimeMillis();
        String videoSafe;
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());


        for(int i = 0; i< p.getPlaylistLink().size(); i++){
            if(p.getPlaylistLink().get(i).equals(id)){
                p.getPlaylistLink().remove(i);
                p.getPlaylistTitle().remove(i);
                recyclerView.setAdapter(new PlaylistAdaptateur(p.getPlaylistTitle(), p.getPlaylistLink()));
                player.loadVideos(p.getPlaylistLink());

                dbRef.setValue(p);

                return;
            }
        }
        Log.d("SYSO", "vidéo à supprimer inconnue");
    }

    // Permet d'afficher des Toasts depuis n'importe quel thread
    public void showToast(final String toast)
    {
        runOnUiThread(new Runnable() {
            public void run()
            {
                Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

