package com.example.cicknime;

import java.util.ArrayList;

public class AnimeDatabase {
    private static final String[] title = {
            "Boruto: Naruto Next Generations",
            "The Pet Girl of Sakurasou",
            "Your Name",
            "Horimiya",
            "The Last: Naruto the Movie",
            "One Piece",
            "TONIKAWA: Over the Moon For You",
            "Re:ZERO Starting Life in Another World",
            "Weathering With You",
            "A Silent Voice",
    };

    private static final String[] synopsis = {
            "Following the successful end of the Fourth Shinobi World War, Konohagakure has been enjoying a period of peace, prosperity, and extraordinary technological advancement. This is all due to the efforts of the Allied Shinobi Forces and the village's Seventh Hokage, Naruto Uzumaki. Now resembling a modern metropolis, Konohagakure has changed, particularly the life of a shinobi. Under the watchful eye of Naruto and his old comrades, a new generation of shinobi has stepped up to learn the ways of the ninja.",
            "When abandoned kittens and his good conscience force second year Sorata Kanda to move into Suimei High School’s infamous Sakura Hall, the satellite dorm and its eccentric, misfit residents turn his life upside down. The decidedly average Sorata finds it difficult to fit in with the bizarre collection of dorm residents like Misaki, an energetic animator; Jin, a playwright playboy; Ryuunosuke, a reclusive programmer; and Chihiro, the dorm manager, art teacher, and party girl.",
            "Mitsuha Miyamizu, a high school girl, yearns to live the life of a boy in the bustling city of Tokyo—a dream that stands in stark contrast to her present life in the countryside. Meanwhile in the city, Taki Tachibana lives a busy life as a high school student while juggling his part-time job and hopes for a future in architecture.",
            "On the surface, the thought of Kyouko Hori and Izumi Miyamura getting along would be the last thing in people's minds. After all, Hori has a perfect combination of beauty and brains, while Miyamura appears meek and distant to his fellow classmates. However, a fateful meeting between the two lays both of their hidden selves bare. Even though she is popular at school, Hori has little time to socialize with her friends due to housework. On the other hand, Miyamura lives under the noses of his peers, his body bearing secret tattoos and piercings that make him look like a gentle delinquent.",
            "Two years have passed since the end of the Fourth Great Ninja War. Konohagakure has remained in a state of peace and harmony—until Sixth Hokage Kakashi Hatake notices the moon is dangerously approaching the Earth, posing the threat of planetary ruin. Amidst the grave ordeal, the Konoha is invaded by a new evil, Toneri Oosutuski, who suddenly abducts Hinata Hyuuga's little sister Hanabi. Kakashi dispatches a skilled ninja team comprised of Naruto Uzumaki, Sakura Haruno, Shikamaru Nara, Sai, and Hinata in an effort to rescue Hanabi from the diabolical clutches of Toneri. However, during their mission, the team faces several obstacles that challenge them, foiling their efforts.",
            "Gol D. Roger was known as the \"Pirate King\", the strongest and most infamous being to have sailed the Grand Line. The capture and execution of Roger by the World Government brought a change throughout the world. His last words before his death revealed the existence of the greatest treasure in the world, One Piece. It was this revelation that brought about the Grand Age of Pirates, men who dreamed of finding One Piece—which promises an unlimited amount of riches and fame—and quite possibly the pinnacle of glory and the title of the Pirate King.",
            "Nasa Yuzaki is determined to leave his name in the history books. Ranking first in the national mock exam and aiming for a distinguished high school, he is certain that he has his whole life mapped out. However, fate is a fickle mistress. On his way home one snowy evening, Nasa's eyes fall upon a peerless beauty across the street. Bewitched, Nasa tries to approach her—only to get blindsided by an oncoming truck.",
            "When Subaru Natsuki leaves the convenience store, the last thing he expects is to be wrenched from his everyday life and dropped into a fantasy world. Things aren't looking good for the bewildered teenager; however, not long after his arrival, he is attacked by some thugs. Armed with only a bag of groceries and a now useless cell phone, he is quickly beaten to a pulp. Fortunately, a mysterious beauty named Satella, in hot pursuit after the one who stole her insignia, happens upon Subaru and saves him. In order to thank the honest and kindhearted girl, Subaru offers to help in her search, and later that night, he even finds the whereabouts of that which she seeks. But unbeknownst to them, a much darker force stalks the pair from the shadows, and just minutes after locating the insignia, Subaru and Satella are brutally murdered.",
            "Tokyo is currently experiencing rain showers that seem to disrupt the usual pace of everyone living there to no end. Amidst this seemingly eternal downpour arrives the runaway high school student Hodaka Morishima, who struggles to financially support himself—ending up with a job at a small-time publisher. At the same time, the orphaned Hina Amano also strives to find work to sustain herself and her younger brother.",
            "As a wild youth, elementary school student Shouya Ishida sought to beat boredom in the cruelest ways. When the deaf Shouko Nishimiya transfers into his class, Shouya and the rest of his class thoughtlessly bully her for fun. However, when her mother notifies the school, he is singled out and blamed for everything done to her. With Shouko transferring out of the school, Shouya is left at the mercy of his classmates. He is heartlessly ostracized all throughout elementary and middle school, while teachers turn a blind eye.",
    };

    private static final String[] studios = {
            "Studio Pierrot",
            "J.C.Staff",
            "CoMix Wave Films",
            "CloverWorks",
            "Studio Pierrot",
            "Toei Animation",
            "Seven Arcs",
            "White Fox",
            "CoMix Wave Films",
            "Kyoto Animation",
    };

    private static final String[] aired = {
            "Apr 5, 2017 to ?",
            "Oct 9, 2012 to Mar 26, 2013",
            "Aug 26, 2016",
            "Jan 10, 2021 to Apr 4, 2021",
            "Dec 6, 2014",
            "Oct 20, 1999 to ?",
            "Oct 3, 2020 to Dec 19, 2020",
            "Apr 4, 2016 to Sep 19, 2016",
            "Jul 19, 2019",
            "Sep 17, 2016",
    };

    private static final String[] duration = {
            "23 mins",
            "23 mins",
            "106 mins",
            "23 mins",
            "112 mins",
            "24 mins",
            "23 mins",
            "25 mins",
            "114 mins",
            "130 mins",
    };

    private static final String[] type = {
            "TV",
            "TV",
            "Movie",
            "TV",
            "Movie",
            "TV",
            "TV",
            "TV",
            "Movie",
            "Movie",
    };

    private static final String[] videoId = {
            "p3acMxaM7-g",
            "7pQ-mhJR6S8",
            "NooIc3dMncc",
            "CNs2IZQZcyI",
            "tA3yE4_t6SY",
            "S8_YwFLCh4U",
            "q_xdOEPjnS0",
            "Slz_rahWp6Y",
            "nfK6UgLra7g",
            "Q6iK6DjV_iE",
    };

    private static final String[][] genres = {
            {"Action", "Adventure", "Super Power", "Martial Arts", "Shounen"},
            {"Slice of Life", "Comedy", "Drama", "Romance", "School"},
            {"Romance", "Supernatural", "School", "Drama"},
            {"Slice of Life", "Comedy", "Romance", "School", "Shounen"},
            {"Action", "Super Power", "Romance", "Martial Arts", "Shounen"},
            {"Action", "Adventure", "Comedy", "Super Power", "Drama", "Fantasy", "Shounen"},
            {"Comedy", "Romance", "Shounen"},
            {"Psychological", "Drama", "Thriller", "Fantasy"},
            {"Slice of Life", "Drama", "Romance", "Fantasy"},
            {"Drama", "School", "Shounen"},
    };

    private static final int[] poster = {
            R.drawable.poster_1,
            R.drawable.poster_2,
            R.drawable.poster_3,
            R.drawable.poster_4,
            R.drawable.poster_5,
            R.drawable.poster_6,
            R.drawable.poster_7,
            R.drawable.poster_8,
            R.drawable.poster_9,
            R.drawable.poster_10,
    };

    private static final double[] score = {5.80, 8.18, 8.95, 8.29, 7.75, 8.52, 7.95, 8.28, 8.39, 8.99};

    static ArrayList<AnimeModel> getListData() {
        ArrayList<AnimeModel> animeList = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            AnimeModel anime = new AnimeModel();

            anime.setTitle(title[i]);
            anime.setGenres(genres[i]);
            anime.setSynopsis(synopsis[i]);
            anime.setStudios(studios[i]);
            anime.setAired(aired[i]);
            anime.setDuration(duration[i]);
            anime.setPoster(poster[i]);
            anime.setScore(score[i]);
            anime.setType(type[i]);
            anime.setVideoId(videoId[i]);

            animeList.add(anime);
        }

        return animeList;
    }
}
