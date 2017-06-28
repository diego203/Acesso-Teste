package com.example.diego.acesso_teste;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Diego on 11/1/2015.
 */
public class MoviesAdapter extends ArrayAdapter<Movie>
{
    private List<Movie> itemList;
    private Context context;

    public MoviesAdapter(Context context, int resource, List<Movie> objects)
    {
        super(context, resource, objects);

        this.itemList = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View view = convertView;

        if(view == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, null);
        }

        Movie movie = getItem(position);

        TextView txtName = (TextView) view.findViewById(R.id.lblName);
        txtName.setText(movie.Title);

        TextView txtYear = (TextView) view.findViewById(R.id.lblYear);
        txtYear.setText(movie.Year);

        view.setTag(movie.imdbID);

        return view;
    }

    @Override
    public int getPosition(Movie item)
    {
        return super.getPosition(item);
    }

    @Override
    public int getCount()
    {
        if(this.itemList != null)
            return itemList.size();
        return 0;
    }

    @Override
    public Movie getItem(int position)
    {
        if(this.itemList != null)
            return itemList.get(position);
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        if (itemList != null)
            return itemList.get(position).hashCode();
        return 0;

    }

    @Override
    public void notifyDataSetChanged()
    {
         super.notifyDataSetChanged();
    }
}
