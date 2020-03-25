package com.example.newsapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*
        The Google API key has been fetched from NewsAPI website!
 */
public class FragmentEnglish extends Fragment {
    View view;
    TextView newstext;
    //String str = "";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.activity_english,container,false);
        newstext = (TextView)view.findViewById(R.id.newstext);


        String news_url;
        news_url = "http://newsapi.org/v2/top-headlines?sources=google-news&apiKey=YOUR-API-KEY";


        new FragmentEnglish.AsyncHttpTask().execute(news_url);

        return view;
    }
    public class AsyncHttpTask extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                String response = streamToString(urlConnection.getInputStream());

                parseResult(response);

                return  result;

            }catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    String streamToString(InputStream stream)throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String data;
        String result = "";

        while((data=bufferedReader.readLine())!= null)
        {
            result +=data;

        }
        if(null != stream)
        {
            stream.close();
        }

        return result;
    }

    private void parseResult(String result)
    {
        JSONObject response = null;
        try {
            response = new JSONObject(result);
            JSONArray articles = response.optJSONArray("articles");

            for(int i=0;i<articles.length();i++)
            {
                JSONObject article = articles.optJSONObject(i);
                String title = article.optString("title");
                String description = article.optString("description");
                //Log.i("titles",title);

                newstext.setText("Title : "+title+"\nDescription : "+description);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
