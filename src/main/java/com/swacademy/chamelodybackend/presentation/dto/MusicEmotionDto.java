package com.swacademy.chamelodybackend.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MusicEmotionDto {
    private String id;

    private Double happy;
    private Double sad;
    private Double fear;
    private Double anger;
    private Double love;
    private Double defaultMood;
    private Double relax;
    private Double nervous;
    private Double surprise;
    private Double touch;
    private Double shame;
    private Double lonely;
    private Double longing;
    private Double tired;
    private Double vitality;
    private Double pride;

//    private MusicDto music;
}
