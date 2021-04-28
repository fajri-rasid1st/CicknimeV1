package com.example.cicknime;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class AnimeModel implements Serializable, Parcelable {
    private String title, synopsis, studios, aired, duration, type, videoId, genres;
    // private String[] genres;
    private int poster;
    private float score;

    public AnimeModel() {
    }

    protected AnimeModel(Parcel in) {
        title = in.readString();
        synopsis = in.readString();
        studios = in.readString();
        aired = in.readString();
        duration = in.readString();
        type = in.readString();
        videoId = in.readString();
        genres = in.readString();
        poster = in.readInt();
        score = in.readFloat();
        // genres = in.createStringArray();
    }

    public static final Creator<AnimeModel> CREATOR = new Creator<AnimeModel>() {
        @Override
        public AnimeModel createFromParcel(Parcel in) {
            return new AnimeModel(in);
        }

        @Override
        public AnimeModel[] newArray(int size) {
            return new AnimeModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getStudios() {
        return studios;
    }

    public void setStudios(String studios) {
        this.studios = studios;
    }

    public String getAired() {
        return aired;
    }

    public void setAired(String aired) {
        this.aired = aired;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(synopsis);
        dest.writeString(studios);
        dest.writeString(aired);
        dest.writeString(duration);
        dest.writeString(type);
        dest.writeString(videoId);
        dest.writeString(genres);
        dest.writeInt(poster);
        dest.writeFloat(score);
        // dest.writeStringArray(genres);
    }
}
