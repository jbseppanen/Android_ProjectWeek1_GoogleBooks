package com.example.jacob.android_projectweek1_googlebooks;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class BookGoogleApiDao {

    private static final String BASE_URL = "https://www.googleapis.com/books/v1/volumes/";
    private static final String SEARCH_SUFFIX = "?q=";
    private static final String SEARCH_ENDING= "&maxResults=40";

    private static final String SEARCH_URL = BASE_URL + SEARCH_SUFFIX + "%s" + SEARCH_ENDING ;


    public static ArrayList<Book> searchBooks(String searchString) {
        Book book = null;
        ArrayList<Book> books = new ArrayList<>();
        String fullUrl = String.format(SEARCH_URL,searchString);
        final String result = NetworkAdapter.httpRequest(fullUrl, NetworkAdapter.GET);
        Log.i("GoogleAPI DAO", result);
        try {
            JSONObject topLevel = new JSONObject(result);
            JSONArray jsonArray = topLevel.getJSONArray("items");

            for (int i = 0; i < jsonArray.length(); ++i) {
                book = new Book(jsonArray.getJSONObject(i));
                books.add(book);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return books;
    }

    static void getImageFile(String url, Context context) {
        File file = null;

        if (url != null) {
            String[] urlParts = url.substring(url.indexOf("id=") + 3).split("&");
            String searchText = urlParts[0];
            File[] items = context.getCacheDir().listFiles();
            Boolean fileFound = false;
            for (File item : items) {
                if (item.getName().contains(searchText)) {
                    fileFound = true;
                    break;
                }
            }
            if (!fileFound) {
                Bitmap bitmap = NetworkAdapter.httpImageRequest(url);
                FileOutputStream fileOutputStream = null;
                try {
                    file = File.createTempFile(searchText, null, context.getCacheDir());
                    fileOutputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
