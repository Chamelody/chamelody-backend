package com.swacademy.chamelodybackend.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class MusicDto {
    private String id;
    private String name;
    private String artists;

    private String imageUrl;
    private LocalDate releaseDate;
    private LocalDate cachedDate;

}
