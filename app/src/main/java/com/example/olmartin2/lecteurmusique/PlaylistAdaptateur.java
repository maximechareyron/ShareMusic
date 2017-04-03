package com.example.olmartin2.lecteurmusique;

/**
 * Created by Olivier on 25/03/2017.
 */
import android.media.Image;
import android.provider.ContactsContract;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class PlaylistAdaptateur extends RecyclerView.Adapter<PlaylistAdaptateur.ViewHolder>{
    private List<String> playlistName;
    private List<String> playlistID;


    public PlaylistAdaptateur(List<String> playlistName, List<String> playlistID){

        this.playlistName=playlistName;
        this.playlistID=playlistID;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String itemName = playlistName.get(position);
        String itemID = playlistID.get(position);
        holder.bind(itemName,itemID);
    }

    @Override
    public int getItemCount() {

        return playlistName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView monItemName;
        // public Button playButton;
        public ImageButton playButton;
        // public Button deleteButton;
        public ImageButton deleteButton;
        public String monItemID;

        public ViewHolder(View ItemView){
            super(ItemView);

            monItemName = (TextView) itemView.findViewById(R.id.item_playlist);
            playButton = (ImageButton) itemView.findViewById(R.id.item_play_img);
            //playButton = (Button) itemView.findViewById(R.id.item_play);
            deleteButton = (ImageButton) itemView.findViewById(R.id.item_del_img);
            // deleteButton = (Button) itemView.findViewById(R.id.item_delete);

            playButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    //faire jouer la musique
                    Log.d("SYSO", "Nom video : " + monItemName.getText().toString() + " ID : " + monItemID);
                    HebergeurActivity.changeMusic(monItemID);
                }
            });

            deleteButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    HebergeurActivity.deleteMusic(monItemID);

                }
            });
        }

        public void bind(String itemName, String itemID){
            monItemName.setText(itemName);
            monItemID = itemID;

        }
    }
}