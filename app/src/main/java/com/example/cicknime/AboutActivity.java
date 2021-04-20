package com.example.cicknime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        Toolbar toolbarAbout = findViewById(R.id.tb_about);
        setSupportActionBar(toolbarAbout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView github = findViewById(R.id.github);
        github.setOnClickListener(this);

        TextView instagram = findViewById(R.id.instagram);
        instagram.setOnClickListener(this);

        TextView linkedin = findViewById(R.id.linkedin);
        linkedin.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.github:
                redirectTo("https://github.com/fajri-rasid1st");
                break;
            case R.id.instagram:
                redirectTo("https://www.instagram.com/fajri_rasid1st");
                break;
            case R.id.linkedin:
                redirectTo("https://www.linkedin.com/in/fajri-rasid-26558114b");
                break;
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private void redirectTo(String url) {
        Uri uri = Uri.parse(url);

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);

        if (intent.resolveActivity(getPackageManager()) != null) startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}