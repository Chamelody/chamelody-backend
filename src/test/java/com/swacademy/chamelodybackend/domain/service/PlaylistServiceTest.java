package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ChamelodyBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class PlaylistServiceTest {
    @Autowired
    PlaylistService playlistService;

    @Autowired
    MusicService musicService;

    @Test
    @DisplayName("랜덤하게 음악 뽑기")
    public void randomSelectMusicTest() {
        Music music = playlistService.randomSelectRepMusic(Emotion.LOVE);
        System.out.println("야호 " + music);
    }

    @Test
    @DisplayName("랜덤 플레이리스트 생성")
    public void createRandomPlaylistTest() {
        Playlist myPlaylist = playlistService.createPlaylist(Emotion.HAPPY, Emotion.ANGER);
        myPlaylist.getMusicList().forEach(System.out::println);
    }

    @Test
    @DisplayName("대표곡 플레이리스트 생성")
    public void createPlaylistTest() {
        List<Music> fullMusicList = musicService.getAllMusicList();
        Music start = fullMusicList.get(10);
        Music target = fullMusicList.get(24);
        Playlist myPlaylist = playlistService.createPlaylist(Emotion.HAPPY, Emotion.ANGER, start, target);
        myPlaylist.getMusicList().forEach(System.out::println);
    }




}