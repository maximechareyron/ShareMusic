package com.example.olmartin2.lecteurmusique;


import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.*;
import com.google.api.services.youtube.model.ResourceId;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.Thumbnail;
import com.google.api.client.auth.oauth2.Credential;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by olmartin2 on 15/03/17.
 */
public class YoutubeManager {

    private static YouTube youTube;
    private static final long NUMBER_OF_VIDEOS_RETURNED = 10;
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";
 //   private static final String PROPERTIES_FILENAME = Config.YOUTUBE_API_KEY;


    public Map<String, String> manage(String queryTerm){
        Map<String,String> resultat = null;
        //String apiKey = Config.YOUTUBE_API_KEY;
        //queryTerm = "dvorak 9eme symphonie";


        try {

            youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest httpRequest) throws IOException {

                }
            }).setApplicationName("youtube-cmdline-search-sample").build();



            YouTube.Search.List search = youTube.search().list("id,snippet");

            search.setKey(Config.YOUTUBE_API_KEY);
            search.setQ(queryTerm);
            search.setType("video");



            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);



            Log.d("appel","");
            SearchListResponse searchResponse = null;

            searchResponse = search.execute();

            Log.d("fin appel","");

            if (searchResponse == null) {
                return null;
            }

            //List<SearchResult> searchListResponse = searchResponse.getItems();


            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                resultat = prettyPrint(searchResultList.iterator(), queryTerm);
                return resultat;
            }
            return null;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /*
    private static String getInputQuery(){

        String inputQuery = "";

        System.out.print("Please enter a search term: ");
        inputQuery = "mozart";

        if (inputQuery.length() < 1) {
            // Use the string "YouTube Developers Live" as a default.
            inputQuery = "YouTube Developers Live";
        }
        return inputQuery;
    }
    */

    private static Map<String, String> prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {
        Map<String,String> res = new HashMap<>();
//        System.out.println("\n=============================================================");
//        System.out.println(
//                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
//        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        SearchResult singleVideo = iteratorSearchResults.next();
        ResourceId rId = singleVideo.getId();

        iteratorSearchResults.hasNext();

        res.put(rId.getVideoId(),singleVideo.getSnippet().getTitle());

//        Map.Entry entry = (Map.Entry) res.entrySet().iterator().next();
//        String key = (String) entry.getKey();
//        String value = (String) entry.getValue();
//        System.out.println(" video trouvé ID : " + key + "; titre : " + value);

        return res;
//        while (iteratorSearchResults.hasNext()) {
//
//            SearchResult singleVideo = iteratorSearchResults.next();
//            ResourceId rId = singleVideo.getId();
//
//            // Confirm that the result represents a video. Otherwise, the
//            // item will not contain a video ID.
//            if (rId.getKind().equals("youtube#video")) {
//                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();
//
//                System.out.println(" Video Id : " + rId.getVideoId());
//                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
//                System.out.println(" Thumbnail: " + thumbnail.getUrl());
//                System.out.println("\n-------------------------------------------------------------\n");
//            }
//        }



    }


}
