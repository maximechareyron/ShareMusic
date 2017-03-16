package com.example.olmartin2.lecteurmusique;


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
import java.util.Iterator;
import java.util.List;
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


    public static void manage(){

        //String apiKey = Config.YOUTUBE_API_KEY;


        try {

            youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest httpRequest) throws IOException {

                }
            }).setApplicationName("youtube-cmdline-search-sample").build();


            String queryTerm = "YouTube Developers Live";

            YouTube.Search.List search = youTube.search().list("id,snippet");

            search.setKey(Config.YOUTUBE_API_KEY);
            search.setQ(queryTerm);
            search.setType("video");



           // search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            System.out.println("appel");
            SearchListResponse searchResponse = search.execute();
            System.out.println("fin appel");

            List<SearchResult> searchListResponse = searchResponse.getItems();


            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                //prettyPrint(searchResultList.iterator(), queryTerm);
                System.out.println("liste avec des elements");
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

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

    private static void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id" + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println("\n-------------------------------------------------------------\n");
            }
        }
    }


}
