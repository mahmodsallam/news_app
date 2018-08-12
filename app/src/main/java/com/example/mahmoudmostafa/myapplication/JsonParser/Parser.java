package com.example.mahmoudmostafa.myapplication.JsonParser;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mahmoudmostafa.myapplication.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mahmoud Mostafa on 9/3/2017.
 */

public class Parser {
    public static ArrayList<News> newsDetails;
    Context c;

    public Parser(ArrayList<News> newsDetails, Context c) {
        this.c = c;
        this.newsDetails = newsDetails;
    }

    public void show(String link) {
        StringRequest stringRequest = new StringRequest(link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {


                    JSONObject root = new JSONObject(response);
                    JSONObject ResponseObject = root.getJSONObject("response");
                    JSONArray results = ResponseObject.getJSONArray("results");

                    Toast.makeText(c, "the response " + response, Toast.LENGTH_LONG).show();

                    for (int i = 0; i < results.length(); i++) {

                        JSONObject object = results.getJSONObject(i);

                        String id = object.getString("id");
                        String type = object.getString("type");
                        String sect_id = object.getString("sectionId");
                        String sect_name = object.getString("sectionName");
                        String Date = object.getString("webPublicationDate");
                        String title = object.getString("webTitle");
                        String url = object.getString("webUrl");

                        News news = new News(id, type, sect_id, sect_name, Date, title, url, null);
                        news.setId(id);
                        news.setDate(Date);
                        news.setSectionName(sect_name);
                        news.setTitle(title);
                        news.setWebUrl(url);
                        news.setType(type);
                        news.setSection_id(sect_id);

                        newsDetails.add(news);
                    }
                    Toast.makeText(c, "the size of the array " + newsDetails.size(), Toast.LENGTH_LONG).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(stringRequest);

    }


}
