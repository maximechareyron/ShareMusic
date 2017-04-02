package com.example.olmartin2.lecteurmusique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 3/25/2017.
 */

public class Playlist {

    //Liste qui aura tous les ID des videos jouées par player
    private List<String> playlistLink = new ArrayList<>();

    //Liste qui aura tous les noms des musiques jouées par le player
    private List<String> playlistTitle = new ArrayList<>();

    public Playlist(){

    }

    private int size;

    public int getSize() { return playlistLink.size(); }

    public Playlist(List<String> playlistLink, List<String> playlistTitle, int size) {
        this.playlistLink = playlistLink;
        this.playlistTitle = playlistTitle;
    }


    public List<String> getPlaylistTitle() {
        return playlistTitle;
    }

    public List<String> getPlaylistLink(){ return playlistLink; }

    public void setPlaylistLink(List<String> playlistLink) {
        this.playlistLink = playlistLink;
    }

    public void setPlaylistTitle(List<String> playlistTitle) {
        this.playlistTitle = playlistTitle;
    }


    /**
     * Enqueues a song to the playlist of the Playlist
     * @param link YouTube link of the song
     * @param title Title of the song
     * @throws Exception whenever the link or the title of the song is empty ;
     */
    public void enqueueSong(String link, String title) throws Exception {
        if(link.isEmpty() || title.isEmpty()){
            throw new Exception("Link can't be empty");
        }
        playlistLink.add(link);
        playlistTitle.add(title);
    }

    /**
     *
     * @return The title of the upcoming song
     */
    public String upcomingTitle(){
        if(playlistTitle.size()==0)
            return "";
        String s = playlistTitle.get(0);
        return s;
    }

    /**
     * Get the next song and pops it from both lists.
     * @return link of the next title to be played
     */
    public String nextTitle(){
        if(playlistLink.size()==0)
            return "";
        String s = playlistLink.get(0);
        playlistLink.remove(0);
        playlistTitle.remove(0);
        return s;
    }




}
