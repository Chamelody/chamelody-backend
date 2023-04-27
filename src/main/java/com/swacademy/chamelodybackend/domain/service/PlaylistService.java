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
    private final static int maximumSizeOfPlaylist = 20;        // 나중에는 유저가 설정하게 변경
    private final MusicService musicService;
    PlaylistGenerator generate;

    public PlaylistService(MusicService musicService, PlaylistGenerator generate) {
        this.musicService = musicService;
        this.generate = generate;
    }


    /**
     * Create Playlist - only emotion selection
     * @param startEmotion current mood
     * @param targetEmotion mood I want
     * @return playlist
     */
    public Playlist createPlaylist(Emotion startEmotion, Emotion targetEmotion) {
        Music randomStartMusic = randomSelectRepMusic(startEmotion);
        Music randomTargetMusic = randomSelectRepMusic(targetEmotion);
        return new Playlist(startEmotion, targetEmotion, randomStartMusic, randomTargetMusic)
                .fillMusicInPlaylist(generate, maximumSizeOfPlaylist);
    }


    /**
     * Create Playlist - emotion, music selection
     * @param startEmotion current mood
     * @param targetEmotion mood I want
     * @param startRepMusic from start emotion
     * @param targetRepMusic from target emotion
     * @return playlist
     */
    public Playlist createPlaylist(Emotion startEmotion, Emotion targetEmotion, Music startRepMusic, Music targetRepMusic) {
         return new Playlist(startEmotion, targetEmotion, startRepMusic, targetRepMusic)
                 .fillMusicInPlaylist(generate, maximumSizeOfPlaylist);
    }


    /**
     * Choose a representative music of emotion at random.
     * [Shortage] choose randomly from the whole.
     * @param emotion selected by user
     * @return music of emotion
     */
    public Music randomSelectRepMusic(Emotion emotion) {
        /*
         TODO : 감정 분석 후에 해당 곡에 대한 대표곡을 뽑는다.
         사실 이 메소드도 MusicService에서 처리해야 할 듯
         */
        Random random = new Random();
        List<Music> fullMusicList = musicService.getAllMusicList();
        return fullMusicList.get(random.nextInt(fullMusicList.size()));
    }
}
