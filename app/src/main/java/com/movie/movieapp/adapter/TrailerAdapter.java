package com.movie.movieapp.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.movie.movieapp.R;
import com.movie.movieapp.activities.DetailMovieActivity;
import com.movie.movieapp.model.Movie;
import com.movie.movieapp.model.MovieTrailer;
import com.movie.movieapp.utils.Constant;
import com.orhanobut.hawk.Hawk;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by asus on 09/05/2016.
 */
public class TrailerAdapter  extends RecyclerView.Adapter<TrailerAdapter.TrailerHolder> {

    LayoutInflater mInflater;
    Activity context;
    MovieTrailer movieTrailer;

    public TrailerAdapter(Activity context){
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        try{
            movieTrailer = Hawk.get("MovieTrailer");
        }catch (Exception e){}
    }

    @Override
    public TrailerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_trailer, parent, false);
        return new TrailerHolder(view);
    }

    public void refresh(){
        movieTrailer  = Hawk.get("MovieTrailer");
        this.notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(final TrailerHolder holder, int position) {
        try{
            holder.tvTrailer.setText("Trailer "+(position+1));
        }catch (Exception e){}
    }

    @Override
    public int getItemCount() {
        try{
            return movieTrailer.getResults().size();
        }catch(Exception e){
        }
        return 0;
    }

    class TrailerHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tvTrailer)
        TextView tvTrailer;

        public TrailerHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context.startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse(Constant.YOUTUBE_URL+movieTrailer.getResults().get(getAdapterPosition()).getKey())));
                }
            });
        }
    }
}
