package com.example.cicknime;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.ms.square.android.expandabletextview.ExpandableTextView;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;


public class DetailActivity extends AppCompatActivity implements View.OnClickListener, OnItemClickListener<AnimeModel> {
    private Toolbar toolbarDetail;
    private MaterialButton btnFavorite;
    private MaterialButton btnWatch;
    private YouTubePlayerView youTubePlayerView;
    private ScrollView svDetail;
    private AnimeModel anime = null;
    private boolean isFavorite = false;
    private AnimeDatabase animeDb;
    private SuggestAnimeAdapter suggestAnimeAdapter;
    private RecyclerView rvSuggestAnime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbarDetail = findViewById(R.id.tb_detail);
        btnFavorite = findViewById(R.id.btn_favorite);
        btnFavorite.setOnClickListener(this);
        btnWatch = findViewById(R.id.btn_watch);
        btnWatch.setOnClickListener(this);
        youTubePlayerView = findViewById(R.id.youtube_player_view);
        svDetail = findViewById(R.id.sv_detail);
        animeDb = new AnimeDatabase(this);
        suggestAnimeAdapter = new SuggestAnimeAdapter();
        rvSuggestAnime = findViewById(R.id.rv_suggest_anime);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setSupportActionBar(toolbarDetail);

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        anime = getIntent().getParcelableExtra("ANIME_DETAIL");
        setDetailActivityContent();
        getLifecycle().addObserver(youTubePlayerView);

        suggestAnimeAdapter.setClickListener(this);
        suggestAnimeAdapter.setAnimes(animeDb.getListData());

        showRecyclerList();
    }

    @Override
    public void onClick(AnimeModel animeModel) {
        Intent detailActivity = new Intent(DetailActivity.this, DetailActivity.class);

        detailActivity.putExtra("ANIME_DETAIL", (Parcelable) animeModel);
        detailActivity.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(detailActivity);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    private void showRecyclerList() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        rvSuggestAnime.setLayoutManager(layoutManager);
        rvSuggestAnime.setHasFixedSize(true);
        rvSuggestAnime.setAdapter(suggestAnimeAdapter);
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
        tvSubtitle.setText(anime.getGenres().split(", ")[0] + " • " + anime.getDuration());
        tvFullTitle.setText(anime.getTitle());
        tvStudios.setText(anime.getStudios());
        tvAired.setText(anime.getAired());
        tvGenres.setText(anime.getGenres());
        etvSynopsis.setText(anime.getSynopsis());

        tvScore.setText(String.valueOf(anime.getScore()));
        rbScore.setRating(anime.getScore() / 2);

        ivPoster.setImageResource(anime.getPoster());
        ivBanner.setImageResource(anime.getPoster());

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                youTubePlayer.cueVideo(anime.getVideoId(), 0);
            }
        });
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

    private void btnWatchHandler() {
        scrollToView(svDetail, youTubePlayerView);
    }

    /**
     * Used to scroll to the given view.
     *
     * @param scrollViewParent Parent ScrollView
     * @param view             View to which we need to scroll.
     */
    private void scrollToView(final ScrollView scrollViewParent, final View view) {
        // Get deepChild Offset
        Point childOffset = new Point();
        getDeepChildOffset(scrollViewParent, view.getParent(), view, childOffset);
        // Scroll to child.
        scrollViewParent.smoothScrollTo(0, childOffset.y);
    }

    /**
     * Used to get deep child offset.
     * <p/>
     * 1. We need to scroll to child in scrollview, but the child may not the direct child to scrollview.
     * 2. So to get correct child position to scroll, we need to iterate through all of its parent views till the main parent.
     *
     * @param mainParent        Main Top parent.
     * @param parent            Parent.
     * @param child             Child.
     * @param accumulatedOffset Accumulated Offset.
     */
    private void getDeepChildOffset(final ViewGroup mainParent, final ViewParent parent, final View child, final Point accumulatedOffset) {
        ViewGroup parentGroup = (ViewGroup) parent;

        accumulatedOffset.x += child.getLeft();
        accumulatedOffset.y += child.getTop();

        if (parentGroup.equals(mainParent)) return;

        getDeepChildOffset(mainParent, parentGroup.getParent(), parentGroup, accumulatedOffset);
    }
}