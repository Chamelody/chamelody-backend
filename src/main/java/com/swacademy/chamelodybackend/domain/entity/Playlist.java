package com.swacademy.chamelodybackend.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
public class Playlist {
    private ArrayList<Music> musicList;
    private final Emotion startEmotion;
    private final Emotion targetEmotion;
    private int maximumSize;

    // 유저가 대표곡을 선택 했을 경우
    private Music startRepMusic;
    private Music targetRepMusic;

    public Playlist(Emotion emotion, Emotion targetEmotion) {
        this.musicList = new ArrayList<>();
        this.startEmotion = emotion;
        this.targetEmotion = targetEmotion;
    }

    public Playlist(Emotion emotion, Emotion targetEmotion, Music startRepMusic, Music targetRepMusic) {
        this.musicList = new ArrayList<>();
        this.startEmotion = emotion;
        this.targetEmotion = targetEmotion;
        this.startRepMusic = startRepMusic;
        this.targetRepMusic = targetRepMusic;
    }

    // 지정했던 사이즈까지 추가한다.
    public boolean addMusic(Music music) {
        if (musicList.size() < maximumSize) {
            musicList.add(music);
            return true;
        }
        return false;
    }
}
