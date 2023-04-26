package com.swacademy.chamelodybackend.domain.entity;

import com.swacademy.chamelodybackend.domain.service.engine.PlaylistGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class Playlist {
    private List<Music> musicList;
    private final Emotion startEmotion;
    private final Emotion targetEmotion;
    private int maximumSize;

    // 유저가 대표곡을 선택 했을 경우
    private Music startRepMusic;
    private Music targetRepMusic;

    public Playlist(Emotion startEmotion, Emotion targetEmotion, Music startRepMusic, Music targetRepMusic) {
        this.musicList = new ArrayList<>();
        this.startEmotion = startEmotion;
        this.targetEmotion = targetEmotion;
        this.startRepMusic = startRepMusic;
        this.targetRepMusic = targetRepMusic;
    }

    // 지정했던 사이즈까지 추가한다.
    public boolean addMusic(Music music) {
        if (musicList.stream().anyMatch(m -> m.getName().equals(music.getName()))) {
            return false;
        }
        if (musicList.size() < maximumSize) {
            musicList.add(music);
            return true;
        }
        return false;
    }

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
