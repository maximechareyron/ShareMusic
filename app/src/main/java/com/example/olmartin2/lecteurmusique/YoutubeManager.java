package com.example.olmartin2.lecteurmusique;


import android.support.annotation.Nullable;
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
    private static final long NUMBER_OF_VIDEOS_RETURNED = 1;
    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final String CREDENTIALS_DIRECTORY = ".oauth-credentials";
 //   private static final String PROPERTIES_FILENAME = Config.YOUTUBE_API_KEY;


    public Map<String, String> manage(String queryTerm){
        Map<String,String> resultat = null;

        try {
            youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
                @Override
                public void initialize(HttpRequest httpRequest) throws IOException {

                }
            }).setApplicationName("ShareMusic").build();

            YouTube.Search.List search = youTube.search().list("id,snippet");

            search.setKey(Config.YOUTUBE_API_KEY);
            search.setQ(queryTerm);
            search.setType("video");

            search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            Log.d("YTManager", "appel");
            SearchListResponse searchResponse = null;
            searchResponse = search.execute();

            Log.d("YTManager", "fin appel");

            if (searchResponse == null) {
                return null;
            }

            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                resultat = getRes(searchResultList.iterator());
                return resultat;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Nullable
    private static Map<String, String> getRes(Iterator<SearchResult> iteratorSearchResults) {
        Map<String,String> res = new HashMap<>();
        if (!iteratorSearchResults.hasNext()) {
            Log.d("YTManager"," There aren't any results for your query.");
            return null;
        }

        SearchResult singleVideo = iteratorSearchResults.next();
        ResourceId rId = singleVideo.getId();

        //iteratorSearchResults.hasNext();

        res.put(rId.getVideoId(),singleVideo.getSnippet().getTitle());

        return res;
    }


}
