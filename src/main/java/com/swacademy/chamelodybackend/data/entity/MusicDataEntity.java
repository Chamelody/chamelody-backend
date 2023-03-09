package com.swacademy.chamelodybackend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "music")
@Getter @Setter
public class MusicDataEntity {
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "artists", nullable = false)
    private String artists;
    @Column(name = "popularity", nullable = false)
    private Integer popularity;
    @Column(name = "danceability", nullable = false, updatable = false)
    private Double danceability;
    @Column(name = "energy", nullable = false, updatable = false)
    private Double energy;
    @Column(name = "music_key", nullable = false, updatable = false)
    private Integer musicKey;
    @Column(name = "loudness", nullable = false, updatable = false)
    private Double loudness;
    @Column(name = "mode", nullable = false, updatable = false)
    private Integer mode;
    @Column(name = "speechiness", nullable = false, updatable = false)
    private Double speechiness;
    @Column(name = "acousticness", nullable = false, updatable = false)
    private Double acousticness;
    @Column(name = "instrumentalness", nullable = false, updatable = false)
    private Double instrumentalness;
    @Column(name = "liveness", nullable = false, updatable = false)
    private Double liveness;
    @Column(name = "valence", nullable = false, updatable = false)
    private Double valence;
    @Column(name = "tempo", nullable = false, updatable = false)
    private Double tempo;
    @Column(name = "duration", nullable = false, updatable = false)
    private Integer duration;
    @Column(name = "time_signature", nullable = false, updatable = false)
    private Integer timeSignature;

    @Column(name = "image_url", nullable = false, length = 512)
    private String imageUrl;
    @Column(name = "release_date", columnDefinition = "DATE")
    private LocalDate releaseDate;
    @Column(name = "cached_date", columnDefinition = "DATE")
    private LocalDate cachedDate;

    @OneToOne(mappedBy = "music", fetch = FetchType.LAZY)
    private MusicEmotionDataEntity musicEmotion;

    public void setMusicEmotion(MusicEmotionDataEntity musicEmotion) {
        this.musicEmotion = musicEmotion;
        musicEmotion.setMusic(this, true);
    }

    public void setMusicEmotion(MusicEmotionDataEntity musicEmotion, boolean isSetSelf) {
        if (isSetSelf) this.musicEmotion = musicEmotion;
        else this.setMusicEmotion(musicEmotion);
    }
}
