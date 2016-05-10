package com.example.brsc2909.simplewebbutton;

import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

/**
 * Created by brsc2909 on 5/9/16.
 * reads your googlechrome bookmarks and extracts the links fom the dropshipping folder
 */
public class Post {
    public void postData(String server, String page, Map<String, String> data) throws IOException {
        String url = server + "/" + page;
        Connection.Response loginForm = Jsoup.connect(url)
                .data(data)
                .method(Connection.Method.POST).execute();
    }

}
