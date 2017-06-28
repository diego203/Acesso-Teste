package com.example.diego.acesso_teste;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class SearchActivity extends ActionBarActivity
{
    private EditText txtSearch;
    private ListView lstMovies;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.Init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
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

    public void Init()
    {
        this.txtSearch = (EditText)findViewById(R.id.txtSearch);
        this.lstMovies = (ListView)findViewById(R.id.lstResult);
        this.toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.txtSearch.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER)
                {
                    List<Movie> list = null;
                    MoviesAdapter adapter;
                    try
                    {
                        Parser jsonParser = new Parser(SearchActivity.this);
                        list = jsonParser.execute(txtSearch.getText().toString(), "0").get();

                        adapter = new MoviesAdapter(SearchActivity.this, R.id.list_item,list);

                        lstMovies.setAdapter(adapter);
                    }
                    catch(Exception e)
                    {
                        //Toast.makeText(this, "Error", Toast.LENGTH_LONG);
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        });

        this.lstMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(SearchActivity.this,MovieDetailActivity.class);
                intent.putExtra("id", view.getTag().toString());
                startActivity(intent);
            }
        });
    }
}
