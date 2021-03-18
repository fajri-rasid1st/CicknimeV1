package com.example.cicknime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private final ArrayList<Anime> animes = new ArrayList<>();
    private RecyclerView rvAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbarHome = findViewById(R.id.toolbar_home);
        setSupportActionBar(toolbarHome);

        rvAnime = findViewById(R.id.rv_anime);
        rvAnime.setHasFixedSize(true);

        animes.addAll(AnimeData.getListData());
        showRecyclerList();
    }

    private void showRecyclerList(){
        ListAnimeAdapter listAnimeAdapter = new ListAnimeAdapter(animes);

        rvAnime.setAdapter(listAnimeAdapter);
        rvAnime.setLayoutManager(new LinearLayoutManager(this));

        listAnimeAdapter.setOnItemClickCallback(this::onItemSelectedAnime);
    }

    private void onItemSelectedAnime(Anime anime) {
        Intent detailIntent = new Intent(HomeActivity.this, DetailActivity.class);

        detailIntent.putExtra("anime", anime);
        detailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(detailIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.about) {
            Intent aboutIntent = new Intent(HomeActivity.this, AboutActivity.class);
            startActivity(aboutIntent);
        }

        return true;
    }
}