package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import com.swacademy.chamelodybackend.domain.service.engine.PlaylistGenerator;
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

    List<Music> musicList;

    @BeforeAll
    public void beforeAll() {
        musicList = musicService.getAllMusicList();
    }

    @Test
    @DisplayName("generate 메소드 테스트")
    public void generateTest() {
        Music start = musicList.get(0);
        Music target = musicList.get(1);

        Playlist playlist = new Playlist(Emotion.HAPPY, Emotion.ANGER, start, target);
        System.out.println(playlist.getStartRepMusic());
        System.out.println(playlist.getTargetRepMusic());
    }

    @Test
    @DisplayName("두 음악의 유클리드 거리 구하기")
    public void calVectorBetweenMusicTest() {
        Music a = musicList.get(0);
        Music b = musicList.get(1);
        double value = playlistGenerator.getDistanceOfMusicVector(a, b);

        double calValue = 0.;
        calValue += Math.abs(a.getDanceability() - b.getDanceability());
        calValue += Math.abs(a.getValence() - b.getValence());
        calValue += Math.abs(a.getEnergy() - b.getEnergy());

        assertEquals(value, calValue);
    }

    @Test
    @DisplayName("연결된 음악 노드 리스트 구하기")
    public void connectedMusicNodeTest() {
        Music a = musicList.get(0);
        List<Music> connectMusicList = playlistGenerator.getConnectedMusic(a, 0.05);
        // 한번 출력해보자.
        connectMusicList.forEach(System.out::println);
    }

    @Test
    @DisplayName("방향이 있는 플레이리스트 만들기")
    public void makeDirectedPlaylistTest() {
        Music start = musicList.get(0);
        Music target = musicList.get(1);

        System.out.println(playlistGenerator.getDistanceOfMusicVector(start, target));

        Playlist playlist = new Playlist(Emotion.HAPPY, Emotion.ANGER, start, target)
                .fillMusicInPlaylist(playlistGenerator, 30);

        List<Music> myPlaylist = playlist.getMusicList();
        System.out.println(myPlaylist.size());
        myPlaylist.forEach(System.out::println);
    }

    @Test
    @DisplayName("방향이 없는 플레이리스트 만들기")
    public void makeUndirectedPlaylistTest() {
        Music start = musicList.get(12);
        Music target = musicList.get(13);   // 0.285

        Playlist playlist = new Playlist(Emotion.HAPPY, Emotion.ANGER, start, target)
                .fillMusicInPlaylist(playlistGenerator, 12);

        List<Music> myPlaylist = playlist.getMusicList();
        System.out.println(myPlaylist.size());
        myPlaylist.forEach(System.out::println);
    }

}