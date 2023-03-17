package com.swacademy.chamelodybackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Music {
    private String id;
    private String name;
    private String artists;
    private Double danceability;
    private Double energy;
    private Integer musicKey;
    private Double loudness;
    private Integer mode;
    private Double speechiness;
    private Double acousticness;
    private Double instrumentalness;
    private Double liveness;
    private Double valence;
    private Double tempo;
    private Integer duration;
    private Integer timeSignature;

    private String imageUrl;
    private LocalDate releaseDate;
    private LocalDate cachedDate;

    private MusicEmotion musicEmotion;
}
