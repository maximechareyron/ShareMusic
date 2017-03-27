package com.example.olmartin2.lecteurmusique;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maxime on 3/25/2017.
 */

public class Host {


    private String pseudo;

    //Liste qui aura tous les ID des videos jouées par player
    private List<String> playlistLink = new ArrayList<>();

    //Liste qui aura tous les noms des musiques jouées par le player
    private List<String> playlistTitle = new ArrayList<>();

    private List<String> guests = new ArrayList<>();

    public Host(){

    }

    public Host(String pseudo){
        this();
        this.pseudo = pseudo;
    }


    public String getPseudo() {
        return pseudo;
    }

    public List<String> getTitles() {
        return playlistTitle;
    }

    public List<String> getLinks(){ return playlistLink; }

    public List<String> getGuests() {
        return guests;
    }

    public void addGuest(String t){
        guests.add(t);
    }

    public void rmGuest(String t){
        guests.remove(t);
    }

    /**
     * Enqueues a song to the playlist of the Host
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
