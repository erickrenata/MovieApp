package com.movie.movieapp.presenter;

import com.movie.movieapp.model.Movie;
import com.movie.movieapp.model.MovieReviews;
import com.movie.movieapp.model.MovieTrailer;
import com.movie.movieapp.network.ApiService;
import com.movie.movieapp.network.NetworkModule;
import com.movie.movieapp.utils.Constant;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Erick Renata on 07/05/2016.
 */
public class UserPresenter {

    public Observable<Movie> moviePopular() {
        NetworkModule networkModule = new NetworkModule();
        ApiService apiService = networkModule.provideRetrofit().create(ApiService.class);
        return apiService.getMoviePopular(Constant.API_KEY)
                .subscribeOn(Schedulers.computation());
    }

    public Observable<Movie> movieTopRated() {
        NetworkModule networkModule = new NetworkModule();
        ApiService apiService = networkModule.provideRetrofit().create(ApiService.class);
        return apiService.getMovieTopRated(Constant.API_KEY)
                .subscribeOn(Schedulers.computation());
    }

    public Observable<MovieTrailer> movieTrailer(int id) {
        NetworkModule networkModule = new NetworkModule();
        ApiService apiService = networkModule.provideRetrofit().create(ApiService.class);
        return apiService.getMovieTrailer(id,Constant.API_KEY)
                .subscribeOn(Schedulers.computation());
    }

    public Observable<MovieReviews> movieReviews(int id) {
        NetworkModule networkModule = new NetworkModule();
        ApiService apiService = networkModule.provideRetrofit().create(ApiService.class);
        return apiService.getMovieReviews(id,Constant.API_KEY)
                .subscribeOn(Schedulers.computation());
    }
}
