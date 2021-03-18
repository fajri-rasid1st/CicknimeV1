package com.example.cicknime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private final ArrayList<Anime> suggestAnimes = new ArrayList<>();
    private Anime anime = null;
    private RecyclerView rvSuggestAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbarDetail = findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbarDetail);

        Intent intent = getIntent();
        anime = (Anime) intent.getSerializableExtra("anime");

        setDetailActivityContent();

        rvSuggestAnime = findViewById(R.id.rv_suggest_anime);
        rvSuggestAnime.setHasFixedSize(true);

        suggestAnimes.addAll(AnimeData.getListData());

        showRecyclerList();
    }

    private void showRecyclerList(){
        SuggestAnimeAdapter suggestAnimeAdapter = new SuggestAnimeAdapter(suggestAnimes);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvSuggestAnime.setAdapter(suggestAnimeAdapter);
        rvSuggestAnime.setLayoutManager(layoutManager);

        suggestAnimeAdapter.setOnItemClickCallback(this::onItemSelectedSuggestAnime);
    }

    private void onItemSelectedSuggestAnime(Anime anime) {
        Intent detailIntent = new Intent(DetailActivity.this, DetailActivity.class);

        detailIntent.putExtra("anime", anime);
        detailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(detailIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void setDetailActivityContent() {
        TextView tvTitle = findViewById(R.id.tv_title);
        TextView tvSynopsis = findViewById(R.id.tv_synopsis);
        TextView tvStudios = findViewById(R.id.tv_studio);
        TextView tvStatus = findViewById(R.id.tv_status);
        TextView tvDuration = findViewById(R.id.tv_duration);
        TextView tvGenres = findViewById(R.id.tv_genres);
        TextView tvScore = findViewById(R.id.tv_score);
        ImageView ivPoster = findViewById(R.id.anime_poster_detail);
        RatingBar rbStarScore = findViewById(R.id.score_star_detail);

        tvTitle.setText(anime.getTitle());
        tvSynopsis.setText(anime.getSynopsis());
        tvStudios.setText(anime.getStudios());
        tvStatus.setText(anime.getStatus());
        tvDuration.setText(anime.getDuration());
        tvGenres.setText(TextUtils.join(", ", anime.getGenres()));
        tvScore.setText(String.valueOf(anime.getScore()));
        ivPoster.setImageResource(anime.getPoster());
        rbStarScore.setRating((float) (anime.getScore() / 2));

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(anime.getTitle());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.about:
                Intent aboutIntent = new Intent(DetailActivity.this, AboutActivity.class);
                startActivity(aboutIntent);
                break;
            case android.R.id.home:
                Intent homeIntent = new Intent(DetailActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                break;
        }

        return true;
    }
}