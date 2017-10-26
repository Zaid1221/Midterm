package com.example.zaid.midterm;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    String input;
    private Screen screen;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        weather = new Weather("","");
        ButtonHandler bh = new ButtonHandler();
        screen = new Screen(this, bh);
        setContentView(screen);
    }

    private class ButtonHandler implements View.OnClickListener
    {
        public void onClick(View v)
        {
            if(screen.isButton((Button)v))
            {
                executeURL(input);
            }
        }
    }

    private void executeURL(String execute)
    {
        execute = screen.UserInput(input);
        URL url = buildUri(execute);

        String x = url.toString();
        new WeatherAsync().execute(x);
    }

    public URL buildUri(String input)
    {
        Uri builtUri = Uri.parse("http://api.openweathermap.org/data/2.5/weather").buildUpon()
                .appendQueryParameter("appid", "1bf836128b99c7f5fc47cbf1cc46e073")
                .appendQueryParameter("zip", input)
                .appendQueryParameter("units", "Imperial")
                .build();
        URL url2 = null;
        try {
            url2 = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url2;
    }

    private class WeatherAsync extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... url) {

            String toreturn="Did not Work";
            try{

                toreturn= getResponseFromHttpUrl(url[0]);
            }
            catch (Exception e)
            {
                e.getMessage();
            }
            return toreturn;
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject sentiment= new JSONObject(s);
                JSONArray response= sentiment.getJSONArray("weather");
                for(int i=0; i<response.length(); i++)
                {
                    JSONObject index = response.getJSONObject(i);

                    String des = index.getString("description");

                    weather.setDescription(des);
                }
                JSONObject newJsonobject=sentiment.getJSONObject("main");
                String temperature = newJsonobject.getString("temp");
                weather.setTemp(temperature);
                screen.setTextView(weather.toString());
            }
            catch (Exception e){
                e.printStackTrace();
            }
            super.onPostExecute(s);
        }
    }


    public static String getResponseFromHttpUrl(String url) throws IOException
    {
        URL theURL= new URL(url);
        HttpURLConnection urlConnection = (HttpURLConnection) theURL.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
