package com.example.diego.acesso_teste;

/**
 * Created by Diego on 11/1/2015.
 */
public class Movie
{
    public Movie() {
    }

    public Movie(String title, String year, String rated, String runtime, String released, String genre, String director, String writer, String plot, String actors, String language, String awards, String country, String poster, String metascore, String imdbRating, String imdbVotes, String imdbID, String type) {
        Title = title;
        Year = year;
        Rated = rated;
        Runtime = runtime;
        Released = released;
        Genre = genre;
        Director = director;
        Writer = writer;
        Plot = plot;
        Actors = actors;
        Language = language;
        Awards = awards;
        Country = country;
        Poster = poster;
        Metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        Type = type;
    }

    public String Title;
    public String Year;
    public String Rated;
    public String Released;
    public String Runtime;
    public String Genre;
    public String Director;
    public String Writer;
    public String Actors;
    public String Plot;
    public String Language;
    public String Country;
    public String Awards;
    public String Poster;
    public String Metascore;
    public String imdbRating;
    public String imdbVotes;
    public String imdbID;
    public String Type;
}
