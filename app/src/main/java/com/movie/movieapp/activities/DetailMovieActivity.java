package com.movie.movieapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movie.movieapp.R;
import com.movie.movieapp.adapter.MovieAdapter;
import com.movie.movieapp.adapter.ReviewAdapter;
import com.movie.movieapp.adapter.TrailerAdapter;
import com.movie.movieapp.model.Movie;
import com.movie.movieapp.presenter.UserPresenter;
import com.movie.movieapp.utils.Constant;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Erick Renata on 07/05/2016.
 */
public class DetailMovieActivity extends AppCompatActivity {

    //region BindingView
    @Bind(R.id.titleMovie)
    TextView titleMovie;
    @Bind(R.id.poster)
    ImageView poster;
    @Bind(R.id.year)
    TextView year;
    @Bind(R.id.duration)
    TextView duration;
    @Bind(R.id.rating)
    TextView rating;
    @Bind(R.id.review)
    TextView review;
    @Bind(R.id.favorite)
    Button favorite;
    @Bind(R.id.rvTrailer)
    RecyclerView rvTrailer;
    @Bind(R.id.rvReview)
    RecyclerView rvReview;
    //endregion
    Movie movie;
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        movie =(Movie) getIntent().getSerializableExtra(Movie.class.getSimpleName());
        position = getIntent().getIntExtra("position", 0);
        setData();
        getMovieTrailer(movie.getResults().get(position).getId());
        getMovieReviews(movie.getResults().get(position).getId());
        //RecyclerView Trailers
        trailerAdapter = new TrailerAdapter(this);
        rvTrailer.setLayoutManager(new LinearLayoutManager(this));
        rvTrailer.setAdapter(trailerAdapter);
        //RecyclerView Reviews
        reviewAdapter = new ReviewAdapter(this);
        rvReview.setLayoutManager(new LinearLayoutManager(this));
        rvReview.setAdapter(reviewAdapter);
    }

    public void setData(){
        try{
            System.out.println(movie.getResults().get(position).isFavorited()+"");
            if (movie.getResults().get(position).isFavorited()){
                favorite.setText("Favorited");
            }else{
                favorite.setText("Mark As Favorite");
            }
        }catch (Exception e){}
        titleMovie.setText(movie.getResults().get(position).getTitle());
        Picasso.with(this).load(Constant.IMAGE_URL+movie.getResults().get(position).getPoster_path()).into(poster);
        year.setText(movie.getResults().get(position).getRelease_date().substring(0,4));
//durasi value?        duration.setText(movie.getResults().get(position).);
        System.out.println(movie.getResults().get(position).getRelease_date()+"");
        rating.setText(movie.getResults().get(position).getVote_average()+"/10");
        review.setText(movie.getResults().get(position).getOverview());
    }

    @OnClick(R.id.favorite)
    void favorite(){
        try{
            if (movie.getResults().get(position).isFavorited()){
                favorite.setText("Favorited");
            }else{
                favorite.setText("Mark As Favorite");
            }
            movie.getResults().get(position).setFavorited(
                    !movie.getResults().get(position).isFavorited());
        }catch (Exception e){}
    }

    public void getMovieTrailer(int id) {
        UserPresenter userPresenter = new UserPresenter();
        userPresenter.movieTrailer(id)
                .subscribe(movieTrailer -> {
                    Hawk.put("MovieTrailer", movieTrailer);
                    trailerAdapter.refresh();
                }, throwable -> throwable.printStackTrace());
    }

    public void getMovieReviews(int id) {
        UserPresenter userPresenter = new UserPresenter();
        userPresenter.movieReviews(id)
                .subscribe(movieReviews -> {
                    Hawk.put("MovieReviews", movieReviews);
                    reviewAdapter.refresh();
                }, throwable -> throwable.printStackTrace());
    }
}
