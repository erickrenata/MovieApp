package com.movie.movieapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.movieapp.R;
import com.movie.movieapp.model.Movie;
import com.movie.movieapp.utils.Constant;
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
    //endregion
    Movie movie;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);
        ButterKnife.bind(this);
        movie =(Movie) getIntent().getSerializableExtra(Movie.class.getSimpleName());
        position = getIntent().getIntExtra("position", 0);
        setData();
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
//        duration.setText(movie.getResults().get(position).);
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
}
