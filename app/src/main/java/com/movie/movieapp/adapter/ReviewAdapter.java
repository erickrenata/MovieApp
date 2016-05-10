package com.movie.movieapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movie.movieapp.R;
import com.movie.movieapp.model.MovieReviews;
import com.movie.movieapp.model.MovieTrailer;
import com.movie.movieapp.utils.Constant;
import com.orhanobut.hawk.Hawk;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asus on 09/05/2016.
 */
public class ReviewAdapter  extends RecyclerView.Adapter<ReviewAdapter.ReviewHolder> {

    LayoutInflater mInflater;
    Activity context;
    MovieReviews movieReviews;

    public ReviewAdapter(Activity context){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        try{
            movieReviews = Hawk.get("MovieReviews");
        }catch (Exception e){}
    }

    @Override
    public ReviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_review, parent, false);
        return new ReviewHolder(view);
    }

    public void refresh(){
        movieReviews  = Hawk.get("MovieReviews");
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final ReviewHolder holder, int position) {
        try{
            holder.tvAuthor.setText(movieReviews.getResults().get(position).getAuthor()+", says: ");
            holder.tvContent.setText(movieReviews.getResults().get(position).getContent());
        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        try{
            return movieReviews.getResults().size();
        }catch(Exception e){
        }
        return 0;
    }

    class ReviewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvAuthor)
        TextView tvAuthor;
        @Bind(R.id.tvContent)
        TextView tvContent;

        public ReviewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
