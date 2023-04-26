package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import com.swacademy.chamelodybackend.domain.service.engine.PlaylistGenerator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class PlaylistService {
    private final MusicService musicService; //??? 이거 어떤 방식으로 가져왔을까?
    PlaylistGenerator generate;

    public PlaylistService(MusicService musicService, PlaylistGenerator generate) {
        this.musicService = musicService;
        this.generate = generate;
    }

    /*
     * Playlist 생성 1. 사용자가 감정만 선택하는 경우
     * 대표곡을 랜덤으로 선택한다.
     */
    public Playlist createPlaylist(Emotion startEmotion, Emotion targetEmotion) {
        Music randomStartMusic = randomSelectRepMusic(startEmotion);
        Music randomTargetMusic = randomSelectRepMusic(targetEmotion);
        return new Playlist(startEmotion, targetEmotion, randomStartMusic, randomTargetMusic)
                .fillMusicInPlaylist(generate, 50);
    }

    /*
     * Playlist 생성 2. 사용자가 감정 + 대표곡을 선정하는 경우
     */
    public Playlist createPlaylist(Emotion startEmotion, Emotion targetEmotion, Music startRepMusic, Music targetRepMusic) {
         return new Playlist(startEmotion, targetEmotion, startRepMusic, targetRepMusic)
                 .fillMusicInPlaylist(generate, 50);
    }

    // 걍 랜덤으로 뽑자.. 지금은 급하니까..
    public Music randomSelectRepMusic(Emotion emotion) {
        Random random = new Random();
        List<Music> fullMusicList = musicService.getAllMusicList();
        return fullMusicList.get(random.nextInt(fullMusicList.size()));
    }
}
