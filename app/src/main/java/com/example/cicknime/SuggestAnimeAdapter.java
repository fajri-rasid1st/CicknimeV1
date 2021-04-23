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
    private final ArrayList<AnimeModel> animeList;
    private OnItemClickCallback onItemClickCallback;

    public SuggestAnimeAdapter(ArrayList<AnimeModel> animeList) {
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
        AnimeModel anime = animeList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(anime.getPoster())
                .apply(new RequestOptions().override(160, 230))
                .into(holder.ivSuggestPoster);

        holder.tvSuggestTitle.setText(anime.getTitle());
        holder.tvSuggestScore.setText(String.valueOf(anime.getScore()));
        holder.tvSuggestType.setText(anime.getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(animeList.get(holder.getBindingAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSuggestPoster;
        TextView tvSuggestTitle, tvSuggestScore, tvSuggestType;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            ivSuggestPoster = itemView.findViewById(R.id.iv_poster_suggest);
            tvSuggestTitle = itemView.findViewById(R.id.tv_title_suggest);
            tvSuggestScore = itemView.findViewById(R.id.tv_score_suggest);
            tvSuggestType = itemView.findViewById(R.id.tv_type_suggest);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(AnimeModel data);
    }
}

