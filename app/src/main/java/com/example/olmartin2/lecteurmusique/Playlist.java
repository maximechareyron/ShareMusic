package com.example.olmartin2.lecteurmusique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 3/25/2017.
 */

public class Playlist {


    private String pseudo;

    //Liste qui aura tous les ID des videos jouées par player
    private List<String> playlistLink = new ArrayList<>();

    //Liste qui aura tous les noms des musiques jouées par le player
    private List<String> playlistTitle = new ArrayList<>();

    private List<String> guests = new ArrayList<>();

    public Playlist(){

    }

    public Playlist(String pseudo){
        this();
        this.pseudo = pseudo;
    }

    public Playlist(String pseudo, List<String> playlistLink, List<String> playlistTitle, List<String> guests) {
        this.pseudo = pseudo;
        this.playlistLink = playlistLink;
        this.playlistTitle = playlistTitle;
        this.guests = guests;
    }

    public String getPseudo() {
        return pseudo;
    }

    public List<String> getPlaylistTitle() {
        return playlistTitle;
    }

    public List<String> getPlaylistLink(){ return playlistLink; }

    public List<String> getGuests() {
        return guests;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setPlaylistLink(List<String> playlistLink) {
        this.playlistLink = playlistLink;
    }

    public void setPlaylistTitle(List<String> playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public void setGuests(List<String> guests) {
        this.guests = guests;
    }

    public void addGuest(String t){
        guests.add(t);
    }

    public void rmGuest(String t){
        guests.remove(t);
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
