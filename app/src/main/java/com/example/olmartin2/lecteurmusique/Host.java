package com.example.olmartin2.lecteurmusique;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

/**
 * Created by Maxime on 3/25/2017.
 */

public class Host {


    private String pseudo;
    private List<String> playlist = new ArrayList<>();

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

    public List<String> getPlaylist() {
        return playlist;
    }

    public List<String> getGuests() {
        return guests;
    }



    public void addGuest(String t){
        guests.add(t);
    }

    public void rmGuest(String t){
        guests.remove(t);
    }


    public void addTitle(String t){
        playlist.add(t);
    }

    public String nextTitle(){
        if(playlist.size()==0)
            return "";
        String s = playlist.get(0);
        playlist.remove(0);
        return s;
    }




}
