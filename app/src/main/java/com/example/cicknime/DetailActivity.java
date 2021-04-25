package com.example.cicknime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    private final ArrayList<AnimeModel> suggestAnimeModel = new ArrayList<>();
    private AnimeModel anime = null;
    private RecyclerView rvSuggestAnime;
    private MaterialButton btnFavorite;
    private MaterialButton btnWatch;
    private YouTubePlayerView youTubePlayerView;
    private ConstraintLayout clDetail;
    private ConstraintLayout clBannerAnime;
    private boolean isFavorite = false;
    private boolean isWatch = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbarDetail = findViewById(R.id.tb_detail);
        setSupportActionBar(toolbarDetail);

        btnFavorite = findViewById(R.id.btn_favorite);
        btnFavorite.setOnClickListener(this);
        btnWatch = findViewById(R.id.btn_watch);
        btnWatch.setOnClickListener(this);

        clDetail = findViewById(R.id.cl_anime_detail_container);
        clBannerAnime = findViewById(R.id.cl_anime_banner);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        youTubePlayerView.setVisibility(View.GONE);

        getLifecycle().addObserver(youTubePlayerView);

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
    private void setDetailActivityContent() {
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

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(anime.getVideoId(), 0);
            }
        });

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

        if (id == R.id.share) {
            Toast.makeText(this, "Link has been copied to clipboard", Toast.LENGTH_SHORT).show();
        } else if (id == android.R.id.home) {
            finish();
        }

        return true;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.btn_favorite) {
            btnFavoriteHandler();
        } else if (id == R.id.btn_watch) {
            btnWatchHandler();
        }
    }

    private void btnFavoriteHandler() {
        if (!isFavorite) {
            btnFavorite.setIconResource(R.drawable.ic_baseline_favorite_24);
            Toast.makeText(this, "Anime has been added to favorite", Toast.LENGTH_SHORT).show();
        } else {
            btnFavorite.setIconResource(R.drawable.ic_baseline_favorite_border_24);
            Toast.makeText(this, "Anime has been removed from favorite", Toast.LENGTH_SHORT).show();
        }

        isFavorite = !isFavorite;
    }

    @SuppressLint("SetTextI18n")
    private void btnWatchHandler() {
        Animation slideUpFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_fade_out);
        Animation slideDownFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_fade_in);
        Animation slideUpFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_fade_in);
        Animation slideDownFadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down_fade_out);

        if (isWatch) {
            btnWatch.setText("watch pv");

            youTubePlayerView.setVisibility(View.GONE);
            youTubePlayerView.startAnimation(slideDownFadeOut);

            clDetail.setVisibility(View.VISIBLE);
            clDetail.startAnimation(slideDownFadeIn);

            clBannerAnime.setVisibility(View.VISIBLE);
            clBannerAnime.startAnimation(slideDownFadeIn);
        } else {
            btnWatch.setText("stop pv");

            clDetail.setVisibility(View.INVISIBLE);
            clDetail.startAnimation(slideUpFadeOut);

            clBannerAnime.setVisibility(View.GONE);
            clBannerAnime.startAnimation(slideUpFadeOut);

            youTubePlayerView.setVisibility(View.VISIBLE);
            youTubePlayerView.startAnimation(slideUpFadeIn);
        }

        isWatch = !isWatch;
    }
}