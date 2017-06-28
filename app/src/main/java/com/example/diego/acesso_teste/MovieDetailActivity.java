package com.example.diego.acesso_teste;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;
import java.util.List;


public class MovieDetailActivity extends ActionBarActivity
{
    private ImageView imgPoster;
    private TextView txtMovieName;
    private TextView txtMovieYear;
    private TextView txtMovieDuration;
    private TextView txtMovieGenre;
    private TextView txtMoviePlot;
    private Toolbar toolbar;

    private String idMovie;

    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        this.Init();
        this.Details();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }
        else
        {
            this.finish();
        }


        return super.onOptionsItemSelected(item);
    }

    private void Init()
    {
        this.imgPoster = (ImageView)findViewById(R.id.imgPoster);
        this.txtMovieName = (TextView)findViewById(R.id.txtMovieName);
        this.txtMovieYear  = (TextView)findViewById(R.id.txtMovieYear);
        this.txtMovieDuration = (TextView)findViewById(R.id.txtMovieDuration);
        this.txtMovieGenre = (TextView)findViewById(R.id.txtMovieGenre);
        this.txtMoviePlot = (TextView)findViewById(R.id.txtMoviePlot);

        this.toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void Details()
    {
        Bundle extras = getIntent().getExtras();

        if(extras != null)
        {
            idMovie = extras.getString("id");
            List<Movie> list = null;

            try
            {
                Parser jsonParser = new Parser(MovieDetailActivity.this);
                list = jsonParser.execute(idMovie, "1").get();

                movie = list.get(0);

                this.txtMovieName.setText(movie.Title);
                this.txtMovieYear.setText(movie.Year);
                this.txtMovieDuration.setText(movie.Runtime);
                this.txtMovieGenre.setText(movie.Genre);
                this.txtMoviePlot.setText(movie.Plot);

                ImageLoader il = new ImageLoader();
                Bitmap im = il.execute(movie.Poster).get();
                this.imgPoster.setImageBitmap(im);
            }
            catch(Exception e)
            {
            }
        }
    }
}
