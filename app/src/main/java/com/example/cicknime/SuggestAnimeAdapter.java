package com.example.cicknime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class SuggestAnimeAdapter extends RecyclerView.Adapter<SuggestAnimeAdapter.ListViewHolder> {
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
    public SuggestAnimeAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_suggest_anime, parent, false);
        return new SuggestAnimeAdapter.ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuggestAnimeAdapter.ListViewHolder holder, int position) {
        holder.onBind(animes.get(position));
    }

    @Override
    public int getItemCount() {
        return animes.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        AnimeModel anime;
        ImageView ivSuggestPoster;
        TextView tvSuggestTitle, tvSuggestScore, tvSuggestType;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            ivSuggestPoster = itemView.findViewById(R.id.iv_poster_suggest);
            tvSuggestTitle = itemView.findViewById(R.id.tv_title_suggest);
            tvSuggestScore = itemView.findViewById(R.id.tv_score_suggest);
            tvSuggestType = itemView.findViewById(R.id.tv_type_suggest);
        }

        public void onBind(AnimeModel anime) {
            this.anime = anime;

            tvSuggestTitle.setText(anime.getTitle());
            tvSuggestScore.setText(String.valueOf(anime.getScore()));
            tvSuggestType.setText(anime.getType());
            ivSuggestPoster.setImageResource(anime.getPoster());
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(anime);
        }
    }
}

