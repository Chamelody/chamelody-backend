package com.swacademy.chamelodybackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MusicEmotion {
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

    private Music music;

    public void setMusic(Music music) {
        this.music = music;
        this.music.setMusicEmotion(this, true);
    }

    public void setMusic(Music music, boolean isSetSelf) {
        if (isSetSelf) this.music = music;
        else this.setMusic(music);
    }

    public MusicEmotion() {
        this.happy = this.happy == null ? 0 : this.happy;
        this.sad = this.sad == null ? 0 : this.sad;
        this.fear = this.fear == null ? 0 : this.fear;
        this.anger = this.anger == null ? 0 : this.anger;
        this.love = this.love == null ? 0 : this.love;
        this.defaultMood = this.defaultMood == null ? 0 : this.defaultMood;
        this.relax = this.relax == null ? 0 : this.relax;
        this.nervous = this.nervous == null ? 0 : this.nervous;
        this.surprise = this.surprise == null ? 0 : this.surprise;
        this.touch = this.touch == null ? 0 : this.touch;
        this.shame = this.shame == null ? 0 : this.shame;
        this.lonely = this.lonely == null ? 0 : this.lonely;
        this.longing = this.longing == null ? 0 : this.longing;
        this.tired = this.tired == null ? 0 : this.tired;
        this.vitality = this.vitality == null ? 0 : this.vitality;
        this.pride = this.pride == null ? 0 : this.pride;

        // @TODO Add validation logic.
    }
}
