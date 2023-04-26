package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ChamelodyBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class AStarPlaylistGeneratorTest {
    @Autowired
    AStarPlaylistGenerator playlistGenerator;

    @Autowired
    MusicService musicService;

    List<Music> fullMusicList;

    @BeforeAll
    public void beforeAll() {
        fullMusicList = musicService.getAllMusicList();
    }

    @Test
    @DisplayName("playlist 객체 생성 - 대표곡 초기화")
    public void generateTest() {
        Music start = fullMusicList.get(0);
        Music target = fullMusicList.get(1);

        Playlist playlist = new Playlist(Emotion.HAPPY, Emotion.ANGER, start, target);
        assertEquals(start, playlist.getStartRepMusic());
        assertEquals(target, playlist.getTargetRepMusic());
    }

    @Test
    @DisplayName("연결된 음악 노드 구하기")
    public void connectedMusicNodeTest() {
        Music a = fullMusicList.get(0);
        List<Music> connectMusicList = playlistGenerator.getConnectedMusic(a, 0.05);
//        connectMusicList.forEach(System.out::println);
    }

    @Test
    @DisplayName("[Error] 연결된 음악 노드 구하기")
    public void connectedMusicNodeErrorTest() {
        Music a = fullMusicList.get(0);
        List<Music> connectMusicList = playlistGenerator.getConnectedMusic(a, 0.0); // 연결거리가 0일 경우
//        connectMusicList.forEach(System.out::println);
        assertEquals(0, connectMusicList.size());
    }

    @Test
    @DisplayName("방향이 있는 플레이리스트 만들기")
    public void makeDirectedPlaylistTest() {
        Music start = fullMusicList.get(0);
        Music target = fullMusicList.get(1);

        Playlist playlist = new Playlist(Emotion.HAPPY, Emotion.ANGER, start, target)
                .fillMusicInPlaylist(playlistGenerator, 30);

        List<Music> myPlaylist = playlist.getMusicList();
        System.out.println(myPlaylist.size());
        myPlaylist.forEach(System.out::println);
    }

    @Test
    @DisplayName("방향이 없는 플레이리스트 만들기 - 같은 대표곡 두 곡을 넣었을 때")
    public void makeUndirectedPlaylistSameTest() {
        // TODO : 같은 곡을 두 개 넣었을 때 어떻게 하지?
        Music start = fullMusicList.get(12);

        System.out.println(start);

        Playlist playlist = new Playlist(Emotion.HAPPY, Emotion.ANGER, start, start)
                .fillMusicInPlaylist(playlistGenerator, 12);

        List<Music> myPlaylist = playlist.getMusicList();
        System.out.println(myPlaylist.size());
        myPlaylist.forEach(System.out::println);
    }

    @Test
    @DisplayName("방향이 없는 플레이리스트 만들기")
    public void makeUndirectedPlaylistTest() {
        Music start = fullMusicList.get(12);
        Music target = fullMusicList.get(13);   // distance : 0.285

        Playlist playlist = new Playlist(Emotion.HAPPY, Emotion.ANGER, start, target)
                .fillMusicInPlaylist(playlistGenerator, 12);

        List<Music> myPlaylist = playlist.getMusicList();
        System.out.println(myPlaylist.size());
        myPlaylist.forEach(System.out::println);
    }
}