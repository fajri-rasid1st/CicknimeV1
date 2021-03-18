package com.example.cicknime;

import java.util.ArrayList;

public class AnimeData {
    private static final String[] title = {
            "Boruto: Naruto Next Generations",
            "The Pet Girl of Sakurasou",
            "Your Name",
            "The Quintessential Quintuplets",
            "KonoSuba: God's Blessing on This Wonderful World!",
            "One Piece",
            "TONIKAWA: Over the Moon For You",
            "The Irregular at Magic High School",
            "Weathering With You",
            "A Silent Voice",
    };

    private static final String[][] genres = {
            {"Action", "Adventure", "Super Power", "Martial Arts", "Shounen"},
            {"Slice of Life", "Comedy", "Drama", "Romance", "School"},
            {"Romance", "Supernatural", "School", "Drama"},
            {"Harem", "Comedy", "Romance", "School", "Shounen"},
            {"Adventure", "Comedy", "Fantasy", "Magic", "Parody", "Supernatural"},
            {"Action", "Adventure", "Comedy", "Super Power", "Drama", "Fantasy", "Shounen"},
            {"Comedy", "Romance", "Shounen"},
            {"Action", "Magic", "Romance", "School", "Sci-Fi", "Supernatural"},
            {"Slice of Life", "Drama", "Romance", "Fantasy"},
            {"Drama", "School", "Shounen"},
    };

    private static final String[] synopsis = {
            "Following the successful end of the Fourth Shinobi World War, Konohagakure has been enjoying a period of peace, prosperity, and extraordinary technological advancement. This is all due to the efforts of the Allied Shinobi Forces and the village's Seventh Hokage, Naruto Uzumaki. Now resembling a modern metropolis, Konohagakure has changed, particularly the life of a shinobi. Under the watchful eye of Naruto and his old comrades, a new generation of shinobi has stepped up to learn the ways of the ninja.",
            "When abandoned kittens and his good conscience force second year Sorata Kanda to move into Suimei High School’s infamous Sakura Hall, the satellite dorm and its eccentric, misfit residents turn his life upside down. The decidedly average Sorata finds it difficult to fit in with the bizarre collection of dorm residents like Misaki, an energetic animator; Jin, a playwright playboy; Ryuunosuke, a reclusive programmer; and Chihiro, the dorm manager, art teacher, and party girl.",
            "Mitsuha Miyamizu, a high school girl, yearns to live the life of a boy in the bustling city of Tokyo—a dream that stands in stark contrast to her present life in the countryside. Meanwhile in the city, Taki Tachibana lives a busy life as a high school student while juggling his part-time job and hopes for a future in architecture.",
            "One day during his lunch break, Uesugi argues with a female transfer student who has claimed \"his seat,\" leading both of them to dislike each other. That same day, he is presented with a golden opportunity to clear his family's debt: a private tutoring gig for a wealthy family's daughter, with a wage of five times the market price. He accepts the proposal, but is horrified to discover that the client, Itsuki Nakano, is the girl he confronted earlier!",
            "After dying a laughable and pathetic death on his way back from buying a game, high school student and recluse Kazuma Satou finds himself sitting before a beautiful but obnoxious goddess named Aqua. She provides the NEET with two options: continue on to heaven or reincarnate in every gamer's dream—a real fantasy world! Choosing to start a new life, Kazuma is quickly tasked with defeating a Demon King who is terrorizing villages. But before he goes, he can choose one item of any kind to aid him in his quest, and the future hero selects Aqua.",
            "Gol D. Roger was known as the \"Pirate King\", the strongest and most infamous being to have sailed the Grand Line. The capture and execution of Roger by the World Government brought a change throughout the world. His last words before his death revealed the existence of the greatest treasure in the world, One Piece. It was this revelation that brought about the Grand Age of Pirates, men who dreamed of finding One Piece—which promises an unlimited amount of riches and fame—and quite possibly the pinnacle of glory and the title of the Pirate King.",
            "Nasa Yuzaki is determined to leave his name in the history books. Ranking first in the national mock exam and aiming for a distinguished high school, he is certain that he has his whole life mapped out. However, fate is a fickle mistress. On his way home one snowy evening, Nasa's eyes fall upon a peerless beauty across the street. Bewitched, Nasa tries to approach her—only to get blindsided by an oncoming truck.",
            "In the dawn of the 21st century, magic, long thought to be folklore and fairy tales, has become a systematized technology and is taught as a technical skill. In First High School, the institution for magicians, students are segregated into two groups based on their entrance exam scores: \"Blooms,\" those who receive high scores, are assigned to the First Course, while \"Weeds\" are reserve students assigned to the Second Course.",
            "Tokyo is currently experiencing rain showers that seem to disrupt the usual pace of everyone living there to no end. Amidst this seemingly eternal downpour arrives the runaway high school student Hodaka Morishima, who struggles to financially support himself—ending up with a job at a small-time publisher. At the same time, the orphaned Hina Amano also strives to find work to sustain herself and her younger brother.",
            "As a wild youth, elementary school student Shouya Ishida sought to beat boredom in the cruelest ways. When the deaf Shouko Nishimiya transfers into his class, Shouya and the rest of his class thoughtlessly bully her for fun. However, when her mother notifies the school, he is singled out and blamed for everything done to her. With Shouko transferring out of the school, Shouya is left at the mercy of his classmates. He is heartlessly ostracized all throughout elementary and middle school, while teachers turn a blind eye.",
    };

    private static final String[] studios = {
            "Studio Pierrot",
            "J.C.Staff",
            "CoMix Wave Films",
            "Tezuka Productions",
            "Studio Deen",
            "Toei Animation",
            "Seven Arcs",
            "Madhouse",
            "CoMix Wave Films",
            "Kyoto Animation",
    };

    private static final String[] status = {
            "Currently Airing",
            "Finished Airing",
            "Finished Airing",
            "Finished Airing",
            "Finished Airing",
            "Currently Airing",
            "Finished Airing",
            "Finished Airing",
            "Finished Airing",
            "Finished Airing",
    };

    private static final String[] duration = {
            "23 min. per ep.",
            "23 min. per ep.",
            "1 hr. 46 min.",
            "24 min. per ep.",
            "23 min. per ep.",
            "24 min. per ep.",
            "23 min. per ep.",
            "23 min. per ep.",
            "1 hr. 54 min.",
            "2 hr. 10 min.",
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

    private static final double[] score = {5.80, 8.18, 8.95, 7.61, 8.15, 8.52, 7.95, 7.51, 8.39, 8.99};

    static ArrayList<Anime> getListData() {
        ArrayList<Anime> animeList = new ArrayList<>();

        for (int i = 0; i < title.length; i++) {
            Anime anime = new Anime();

            anime.setTitle(title[i]);
            anime.setGenres(genres[i]);
            anime.setSynopsis(synopsis[i]);
            anime.setStudios(studios[i]);
            anime.setStatus(status[i]);
            anime.setDuration(duration[i]);
            anime.setPoster(poster[i]);
            anime.setScore(score[i]);

            animeList.add(anime);
        }

        return animeList;
    }
}
