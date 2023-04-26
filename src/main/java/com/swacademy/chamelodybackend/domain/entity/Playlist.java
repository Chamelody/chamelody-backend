package com.swacademy.chamelodybackend.domain.entity;

import com.swacademy.chamelodybackend.domain.service.engine.PlaylistGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
public class Playlist {
    private ArrayList<Music> musicList;     // 플레이리스트에 담긴 음악 리스트
    private final Emotion startEmotion;     // 시작 감정
    private final Emotion targetEmotion;    // 목표 감정
    private int maximumSize;                // 최대 곡 수
    private Music startRepMusic;            // 시작 대표곡
    private Music targetRepMusic;           // 목표 대표곡

    public Playlist(Emotion startEmotion, Emotion targetEmotion, Music startRepMusic, Music targetRepMusic) {
        this.musicList = new ArrayList<>();
        this.startEmotion = startEmotion;
        this.targetEmotion = targetEmotion;
        this.startRepMusic = startRepMusic;
        this.targetRepMusic = targetRepMusic;
    }

    public boolean addMusic(Music music) {
        // 겹치는 곡은 넣지 않는다.
        if (musicList.stream().anyMatch(m -> m.getId().equals(music.getId()))) {
            return false;
        }
        if (musicList.size() < maximumSize) {   // 사용자가 지정한 사이즈까지 추가한다.
            musicList.add(music);
            return true;
        }
        return false;
    }

    public boolean addMusic(int index, Music music) {
        if (index < 0) return false;                    // index error(1) : 음수
        if (index > musicList.size()) return false;     // index error(2) : over size
        if (musicList.stream().anyMatch(m -> m.getName().equals(music.getName()))) {
            return false;
        }
        if (musicList.size() < maximumSize) {
            musicList.add(index, music);
        }
        return false;
    }

    // 음악 리스트를 한 번에 추가한다.
    public boolean addAllMusic(List<Music> music) {
        musicList.addAll(music);
        return true;
    }

    // 플레이리스트에 곡을 채우는 메소드
    public Playlist fillMusicInPlaylist(PlaylistGenerator playlistGenerator, int size) {
        return playlistGenerator.generate(this, size);
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "musicList=" + musicList +
                '}';
    }
}
