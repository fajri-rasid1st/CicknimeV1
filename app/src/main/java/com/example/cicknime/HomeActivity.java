package com.example.cicknime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

public class HomeActivity extends AppCompatActivity implements OnItemClickListener<AnimeModel> {
    private RecyclerView rvAnime;
    private AnimeDatabase animeDb;
    private ListAnimeAdapter listAnimeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        rvAnime = findViewById(R.id.rv_anime);
        animeDb = new AnimeDatabase(this);
        listAnimeAdapter = new ListAnimeAdapter();
    }

    @Override
    protected void onStart() {
        super.onStart();

        listAnimeAdapter.setClickListener(this);
        listAnimeAdapter.setAnimes(animeDb.getListData());

        rvAnime.setLayoutManager(new LinearLayoutManager(this));
        rvAnime.setHasFixedSize(true);
        rvAnime.setAdapter(listAnimeAdapter);
    }

    @Override
    public void onClick(AnimeModel animeModel) {
        Intent detailActivity = new Intent(HomeActivity.this, DetailActivity.class);

        detailActivity.putExtra("ANIME_DETAIL", (Parcelable) animeModel);
        detailActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(detailActivity);
    }
}