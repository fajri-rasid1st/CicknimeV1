package com.example.cicknime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private final ArrayList<AnimeModel> suggestAnimeModel = new ArrayList<>();
    private AnimeModel anime = null;
    private RecyclerView rvSuggestAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbarDetail = findViewById(R.id.tb_detail);
        setSupportActionBar(toolbarDetail);

        Intent intent = getIntent();
        anime = (AnimeModel) intent.getSerializableExtra("anime");
        setDetailActivityContent();

        rvSuggestAnime = findViewById(R.id.rv_suggest_anime);
        rvSuggestAnime.setHasFixedSize(true);

        suggestAnimeModel.addAll(AnimeDatabase.getListData());

        showRecyclerList();
    }

    private void showRecyclerList() {
        SuggestAnimeAdapter suggestAnimeAdapter = new SuggestAnimeAdapter(suggestAnimeModel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvSuggestAnime.setAdapter(suggestAnimeAdapter);
        rvSuggestAnime.setLayoutManager(layoutManager);

        suggestAnimeAdapter.setOnItemClickCallback(this::onItemSelectedSuggestAnime);
    }

    private void onItemSelectedSuggestAnime(AnimeModel anime) {
        Intent detailIntent = new Intent(DetailActivity.this, DetailActivity.class);

        detailIntent.putExtra("anime", anime);
        detailIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(detailIntent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @SuppressLint("SetTextI18n")
    public void setDetailActivityContent() {
        TextView tvTitle = findViewById(R.id.tv_title_detail);
        TextView tvSubtitle = findViewById(R.id.tv_subtitle_detail);
        TextView tvFullTitle = findViewById(R.id.tv_full_title_detail);
        TextView tvStudios = findViewById(R.id.tv_studio_detail);
        TextView tvAired = findViewById(R.id.tv_aired_detail);
        TextView tvGenres = findViewById(R.id.tv_genres_detail);
        ExpandableTextView etvSynopsis = findViewById(R.id.etv_synopsis_detail);

        TextView tvScore = findViewById(R.id.tv_score_detail);
        RatingBar rbScore = findViewById(R.id.rb_star_detail);

        ImageView ivPoster = findViewById(R.id.iv_poster_detail);
        ImageView ivBanner = findViewById(R.id.iv_banner_detail);

        tvTitle.setText(anime.getTitle());
        tvSubtitle.setText(anime.getGenres()[0] + " • " + anime.getDuration());
        tvFullTitle.setText(anime.getTitle());
        tvStudios.setText(anime.getStudios());
        tvAired.setText(anime.getAired());
        tvGenres.setText(TextUtils.join(", ", anime.getGenres()));
        etvSynopsis.setText(anime.getSynopsis());

        tvScore.setText(String.valueOf(anime.getScore()));
        rbScore.setRating((float) (anime.getScore() / 2));

        ivPoster.setImageResource(anime.getPoster());
        ivBanner.setImageResource(anime.getPoster());

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        for (int i = 0; i < menu.size(); i++) {
            Drawable drawable = menu.getItem(i).getIcon();

            if (drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.primaryText), PorterDuff.Mode.SRC_ATOP);
            }
        }

        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        } else if (id == R.id.share) {
            Toast.makeText(this, "Link has been copied to clipboard", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}