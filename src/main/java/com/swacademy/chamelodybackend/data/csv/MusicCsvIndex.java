package com.swacademy.chamelodybackend.data.csv;

public enum MusicCsvIndex {
    ARTISTS(1),
    NAME(2),
    ID(3),
    POPULARITY(4),
    DANCEABILITY(6),
    ENERGY(7),
    MUSIC_KEY(8),
    LOUDNESS(9),
    MODE(10),
    SPEECHINESS(11),
    ACOUSTICNESS(12),
    INSTRUMENTALNESS(13),
    LIVENESS(14),
    VALENCE(15),
    TEMPO(16),
    DURATION(22),
    TIME_SIGNATURE(23);

    public final int index;

    MusicCsvIndex(int index) {
        this.index = index;
    }
}
