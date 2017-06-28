package com.example.diego.acesso_teste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Diego on 11/2/2015.
 */
public class ImageLoader extends AsyncTask<String, String, Bitmap>
{
    Bitmap bitmap;

    @Override
    protected Bitmap doInBackground(String... params)
    {
        try
        {
            URL url = new URL(params[0]);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }
}
