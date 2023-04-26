package com.swacademy.chamelodybackend.presentation.dto;

import com.swacademy.chamelodybackend.domain.entity.Emotion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class PlaylistDto {
    private List<MusicDto> musicList;
    private Emotion startEmotion;
    private Emotion targetEmotion;
    private MusicDto startRepMusic;
    private MusicDto targetRepMusic;
}