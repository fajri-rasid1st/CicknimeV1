package com.example.cicknime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListAnimeAdapter extends RecyclerView.Adapter<ListAnimeAdapter.ListViewHolder> {
    private final ArrayList<Anime> animeList;
    private OnItemClickCallback onItemClickCallback;

    public ListAnimeAdapter(ArrayList<Anime> animeList) {
        this.animeList = animeList;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_anime, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Anime anime = animeList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(anime.getPoster())
                .apply(new RequestOptions().override(110, 110))
                .into(holder.poster);

        holder.starScore.setRating((float) (anime.getScore() / 2));
        holder.tvTitle.setText(anime.getTitle());
        holder.tvScore.setText(String.valueOf(anime.getScore()));
        holder.tvSynopsis.setText(anime.getSynopsis());

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
        RatingBar starScore;
        ImageView poster;
        TextView tvTitle, tvScore, tvSynopsis;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);

            poster = itemView.findViewById(R.id.item_poster);
            starScore = itemView.findViewById(R.id.score_star);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvScore = itemView.findViewById(R.id.tv_score);
            tvSynopsis = itemView.findViewById(R.id.tv_synopsis);
        }
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Anime data);
    }
}
