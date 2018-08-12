package com.example.mahmoudmostafa.myapplication.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mahmoudmostafa.myapplication.Adapters.MyAdapter;
import com.example.mahmoudmostafa.myapplication.Constants.Constant;
import com.example.mahmoudmostafa.myapplication.R;
import com.example.mahmoudmostafa.myapplication.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Home extends AppCompatActivity {

    RecyclerView recyclerView;
    MyAdapter adapter;
    ArrayList<News> newsesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newsesList = new ArrayList<>();

        //pulling data from server
        new Task().execute(Constant.LINK);

        adapter = new MyAdapter(this, newsesList);

        recyclerView = (RecyclerView) findViewById(R.id.recyler_id);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


    }


    class Task extends AsyncTask<String, String, ArrayList<News>> {

        @Override
        protected ArrayList<News> doInBackground(String... params) {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            String response = null;
            //String p=params[0];

            try {


                URL url = new URL(Constant.LINK);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();
                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }

                response = buffer.toString();
                JSONObject root = new JSONObject(response);
                JSONObject ResponseObject = root.getJSONObject("response");
                JSONArray results = ResponseObject.getJSONArray("results");

                for (int i = 0; i < results.length(); i++) {

                    JSONObject object = results.getJSONObject(i);

                    String id = object.getString("id");
                    String type = object.getString("type");
                    String sect_id = object.getString("sectionId");
                    String sect_name = object.getString("sectionName");
                    String Date = object.getString("webPublicationDate");
                    String title = object.getString("webTitle");
                    String urll = object.getString("webUrl");

                    String author = "";
                    try {
                        author = object.getString("author");

                    } catch (Exception e) {
                        author = null;

                    }

                    News news = new News(id, type, sect_id, sect_name, Date, title, urll, author);
                    news.setId(id);
                    news.setDate(Date);
                    news.setSectionName(sect_name);
                    news.setTitle(title);
                    news.setWebUrl(urll);
                    news.setType(type);
                    news.setSection_id(sect_id);
                    newsesList.add(news);
                    //  adapter.notifyDataSetChanged();
                }
            } catch (IOException e) {
                Log.e("there is io exception", "Error ", e);
                return null;
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e("mahmoud", "Error closing stream", e);
                    }
                }


            }

            return newsesList;
        }

        @Override
        protected void onPostExecute(ArrayList<News> s) {
            super.onPostExecute(s);
            adapter.notifyDataSetChanged();
        }
    }


}





