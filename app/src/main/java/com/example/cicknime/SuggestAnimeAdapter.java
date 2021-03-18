package com.example.cicknime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class SuggestAnimeAdapter extends RecyclerView.Adapter<SuggestAnimeAdapter.ListViewHolder> {
    private final ArrayList<Anime> animeList;
    private OnItemClickCallback onItemClickCallback;

    public SuggestAnimeAdapter(ArrayList<Anime> animeList) {
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_suggest_anime, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Anime anime = animeList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(anime.getPoster())
                .apply(new RequestOptions().override(160, 230))
                .into(holder.suggestPoster);

        holder.suggestTvTitle.setText(anime.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(animeList.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView suggestPoster;
        TextView suggestTvTitle;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            suggestPoster = itemView.findViewById(R.id.suggest_anime_poster);
            suggestTvTitle = itemView.findViewById(R.id.suggest_anime_title);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Anime data);
    }
}

