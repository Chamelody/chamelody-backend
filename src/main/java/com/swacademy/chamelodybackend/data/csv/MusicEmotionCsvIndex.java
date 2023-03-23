package com.swacademy.chamelodybackend.data.csv;

public enum MusicEmotionCsvIndex {
    ID(0),
    HAPPY(1),
    SAD(2),
    FEAR(3),
    ANGER(4),
    LOVE(5),
    DEFAULT_MOOD(6),
    RELAX(7),
    NERVOUS(8),
    SURPRISE(9),
    TOUCH(10),
    SHAME(11),
    LONELY(12),
    LONGING(13),
    TIRED(14),
    VITALITY(15),
    PRIDE(16);

    public final int index;

    MusicEmotionCsvIndex(int index) {
        this.index = index;
    }
}
