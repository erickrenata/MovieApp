package com.movie.movieapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.movie.movieapp.R;
import com.movie.movieapp.adapter.MovieAdapter;
import com.movie.movieapp.presenter.UserPresenter;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MovieActivity extends AppCompatActivity {

    MovieAdapter movieAdapter;
    @Bind(R.id.rvMovieList)
    RecyclerView rvMovieList;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        getMoviePopular();
        getMovieTopRated();

        movieAdapter = new MovieAdapter(this);
//        movieAdapter.refresh(category);
        rvMovieList.setLayoutManager(new GridLayoutManager(this, 2));
        rvMovieList.setAdapter(movieAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_popular) {
            category = "Popular";
            movieAdapter.refresh(category);
            setTitle("Pop Movies");
            return true;
        }
        if (id == R.id.action_top_rated) {
            category = "Top Rated";
            movieAdapter.refresh(category);
            setTitle("Top Rated Movies");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getMoviePopular() {
        UserPresenter userPresenter = new UserPresenter();
        userPresenter.moviePopular()
                .subscribe(moviePopular -> {
                    Hawk.put("MoviePopular", moviePopular);
                }, throwable -> throwable.printStackTrace());
    }

    public void getMovieTopRated() {
        System.out.println("HALO PUKON");
        UserPresenter userPresenter = new UserPresenter();
        userPresenter.movieTopRated()
                .subscribe(movieTopRated -> {
                    Hawk.put("MovieTopRated", movieTopRated);
                }, throwable -> throwable.printStackTrace());
    }

}

