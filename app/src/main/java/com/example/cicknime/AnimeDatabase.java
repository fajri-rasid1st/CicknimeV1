package com.example.cicknime;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;

import java.util.ArrayList;

public class AnimeDatabase {
    private final Context context;

    public AnimeDatabase(Context context) {
        this.context = context;
    }

    public ArrayList<AnimeModel> getListData() {
        ArrayList<AnimeModel> animeList = new ArrayList<>();


        String[] title = context.getResources().getStringArray(R.array.anime_title);
        String[] synopsis = context.getResources().getStringArray(R.array.anime_synopsis);
        String[] studios = context.getResources().getStringArray(R.array.anime_studios);
        String[] aired = context.getResources().getStringArray(R.array.anime_aired);
        String[] duration = context.getResources().getStringArray(R.array.anime_duration);
        String[] type = context.getResources().getStringArray(R.array.anime_type);
        String[] videoId = context.getResources().getStringArray(R.array.anime_video_id);
        String[] genres = context.getResources().getStringArray(R.array.anime_genres);
        String[] score = context.getResources().getStringArray(R.array.anime_score);

        @SuppressLint("Recycle")
        TypedArray poster = context.getResources().obtainTypedArray(R.array.anime_poster);

        for (int i = 0; i < title.length; i++) {
            AnimeModel anime = new AnimeModel();

            anime.setTitle(title[i]);
            anime.setSynopsis(synopsis[i]);
            anime.setStudios(studios[i]);
            anime.setAired(aired[i]);
            anime.setDuration(duration[i]);
            anime.setType(type[i]);
            anime.setVideoId(videoId[i]);
            anime.setGenres(genres[i]);
            anime.setPoster(poster.getResourceId(i, -1));
            anime.setScore(Float.parseFloat(score[i]));

            animeList.add(anime);
        }

        return animeList;
    }
}
