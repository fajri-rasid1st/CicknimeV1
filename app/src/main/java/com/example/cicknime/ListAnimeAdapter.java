package com.example.cicknime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAnimeAdapter extends RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder> {
    private ArrayList<AnimeModel> animes;
    private OnItemClickListener<AnimeModel> clickListener;

    public void setAnimes(ArrayList<AnimeModel> animes) {
        this.animes = animes;
    }

    public void setClickListener(OnItemClickListener<AnimeModel> clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_anime, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.onBind(animes.get(position));
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AnimeModel anime;
        TextView tvTitle, tvSynopsis, tvScore;
        ImageView ivPoster;
        RatingBar rbStarScore;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            tvTitle = itemView.findViewById(R.id.tv_title_list);
            tvSynopsis = itemView.findViewById(R.id.tv_synopsis_list);
            tvScore = itemView.findViewById(R.id.tv_score_list);
            ivPoster = itemView.findViewById(R.id.iv_poster_list);
            rbStarScore = itemView.findViewById(R.id.rb_score_list);
        }

        public void onBind(AnimeModel anime) {
            this.anime = anime;

            tvTitle.setText(anime.getTitle());
            tvSynopsis.setText(anime.getSynopsis());
            tvScore.setText(String.valueOf(anime.getScore()));
            ivPoster.setImageResource(anime.getPoster());
            rbStarScore.setRating(anime.getScore() / 2);

        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(anime);
        }
    }
}
