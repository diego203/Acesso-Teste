package com.example.diego.acesso_teste;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Diego on 11/1/2015.
 */
public class Parser extends AsyncTask<String, JSONObject, List<Movie>>
{
    static InputStream is = null;
    static JSONObject obj = null;
    static String json = "";

    private Context context;

    public Parser(Context context)
    {
        this.context = context;
    }

    @Override
    protected List<Movie> doInBackground(String... params)
    {
        List<Movie> list = new ArrayList<Movie>();

        JSONObject json = GetJson(ConcatUrl(params[0], params[1]));

        try
        {
            list.add(ConvertMovie(json));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public String ConcatUrl(String search, String b)
    {
        search = search.replace(" ", "+");

        return b.equals("0") ? "http://omdbapi.com/?t=" + search + "&y=&plot=short&r=json" : "http://omdbapi.com/?i=" + search + "&plot=short&r=json";
    }

    public JSONObject GetJson(String url) {
        try
        {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);

            HttpResponse response = client.execute(post);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
        }
        catch(Exception e)
        {
            Log.e("Error", "Error getting content" + e.toString());
        }

        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "n");
            }
            is.close();
            json = sb.toString();
        }
        catch (Exception e)
        {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }

        try
        {
            obj = new JSONObject(json);
        }
        catch (JSONException e)
        {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }

        return obj;
    }

    private Movie ConvertMovie(JSONObject obj) throws JSONException
    {
        return new Movie(obj.getString("Title"), obj.getString("Year"), obj.getString("Rated"), obj.getString("Runtime"), obj.getString("Released"),obj.getString("Genre"),obj.getString("Director"),obj.getString("Writer"),obj.getString("Plot"),obj.getString("Actors"),obj.getString("Language"),obj.getString("Awards"),obj.getString("Country"),obj.getString("Poster"),obj.getString("Metascore"),obj.getString("imdbRating"),obj.getString("imdbVotes"),obj.getString("imdbID"),obj.getString("Type"));
    }
}
