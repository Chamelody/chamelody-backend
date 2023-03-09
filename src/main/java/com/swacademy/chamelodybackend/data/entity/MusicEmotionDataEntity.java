package com.swacademy.chamelodybackend.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "music_emotion")
@Getter @Setter
public class MusicEmotionDataEntity {
    @Id
    @Column(name = "id", nullable = false, insertable = false, updatable = false)
    private String id;

    @Column(name = "happy", nullable = false)
    private Double happy;
    @Column(name = "sad", nullable = false)
    private Double sad;
    @Column(name = "fear", nullable = false)
    private Double fear;
    @Column(name = "anger", nullable = false)
    private Double anger;
    @Column(name = "love", nullable = false)
    private Double love;
    @Column(name = "default_mood", nullable = false)
    private Double defaultMood;
    @Column(name = "relax", nullable = false)
    private Double relax;
    @Column(name = "nervous", nullable = false)
    private Double nervous;
    @Column(name = "surprise", nullable = false)
    private Double surprise;
    @Column(name = "touch", nullable = false)
    private Double touch;
    @Column(name = "shame", nullable = false)
    private Double shame;
    @Column(name = "lonely", nullable = false)
    private Double lonely;
    @Column(name = "longing", nullable = false)
    private Double longing;
    @Column(name = "tired", nullable = false)
    private Double tired;
    @Column(name = "vitality", nullable = false)
    private Double vitality;
    @Column(name = "pride", nullable = false)
    private Double pride;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private MusicDataEntity music;

    public void setMusic(MusicDataEntity music) {
        this.music = music;
        music.setMusicEmotion(this, true);
    }

    public void setMusic(MusicDataEntity music, boolean isSetSelf) {
        if (isSetSelf) this.music = music;
        else this.setMusic(music);
    }

    @PrePersist
    private void prePersist() {
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
