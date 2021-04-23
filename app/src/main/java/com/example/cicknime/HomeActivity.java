package com.example.cicknime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private final ArrayList<AnimeModel> animeModels = new ArrayList<>();
    private RecyclerView rvAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        rvAnime = findViewById(R.id.rv_anime);
        rvAnime.setHasFixedSize(true);

        animeModels.addAll(AnimeDatabase.getListData());
        showRecyclerList();
    }

    private void showRecyclerList() {
        ListAnimeAdapter listAnimeAdapter = new ListAnimeAdapter(animeModels);

        rvAnime.setAdapter(listAnimeAdapter);
        rvAnime.setLayoutManager(new LinearLayoutManager(this));

        listAnimeAdapter.setOnItemClickCallback(this::onItemSelectedAnime);
    }

    private void onItemSelectedAnime(AnimeModel anime) {
        Intent detailIntent = new Intent(HomeActivity.this, DetailActivity.class);

        detailIntent.putExtra("anime", anime);
        detailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(detailIntent);
    }
}