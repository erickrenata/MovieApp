package com.movie.movieapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.movieapp.R;
import com.movie.movieapp.activities.DetailMovieActivity;
import com.movie.movieapp.model.Movie;
import com.movie.movieapp.utils.Constant;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import java.security.spec.ECField;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Erick Renata on 07/05/2016.
 */
public class MovieAdapter  extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {

    LayoutInflater mInflater;
    Activity context;
    Movie movie;

    public MovieAdapter(Activity context){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        try{
            movie = Hawk.get("MoviePopular");
        }catch (Exception e){}
    }

    public void refresh(String category){
        try{
            if(category.equals("Popular")){
                movie  = Hawk.get("MoviePopular");
            }else if(category.equals("Top Rated")){
                movie  = Hawk.get("MovieTopRated");
            }
            this.notifyDataSetChanged();
        }catch (Exception e){}
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {
        try{
            Picasso.with(context)
                    .load(Constant.IMAGE_URL+movie.getResults().get(position).getPoster_path())
                    .into(holder.moviePoster);
        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        try{
            return movie.getResults().size();
        }catch(Exception e){
        }
        return 0;
    }

    class MovieHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.moviePoster)
        ImageView moviePoster;

        public MovieHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(itemView.getContext(), DetailMovieActivity.class)
                            .putExtra("position",getAdapterPosition())
                            .putExtra(Movie.class.getSimpleName(), movie));
                }
            });
        }
    }
}
