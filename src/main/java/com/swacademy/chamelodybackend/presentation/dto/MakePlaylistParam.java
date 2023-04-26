package com.swacademy.chamelodybackend.presentation.dto;

import com.swacademy.chamelodybackend.domain.entity.Emotion;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MakePlaylistParam {
    private Emotion fromEmotion;
    private Emotion toEmotion;
}
