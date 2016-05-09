package com.movie.movieapp.network;

import com.movie.movieapp.model.Movie;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by Erick Renata on 07/05/2016.
 */
public interface ApiService {

    @GET("3/movie/popular")
    Observable<Movie> getMoviePopular(@Query("api_key") String api_key);


    @GET("3/movie/top_rated")
    Observable<Movie> getMovieTopRated(@Query("api_key") String api_key);
}
