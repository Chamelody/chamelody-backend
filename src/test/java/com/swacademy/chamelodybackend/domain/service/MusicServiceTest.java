package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Music;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest(classes = ChamelodyBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MusicServiceTest {

    @Autowired
    private MusicService musicService;

    @Test
    public void addTest() {
        Music music1 = new Music();

        music1.setArtists("asdf");
        music1.setName("asdf");
        music1.setId("asdf");
        music1.setPopularity(42);
        music1.setDanceability(42d);
        music1.setEnergy(42d);
        music1.setMusicKey(42);
        music1.setLoudness(42d);
        music1.setMode(42);
        music1.setSpeechiness(42d);
        music1.setAcousticness(42d);
        music1.setInstrumentalness(42d);
        music1.setLiveness(42d);
        music1.setValence(42d);
        music1.setTempo(42d);
        music1.setDuration(42);
        music1.setTimeSignature(42);

        Music music2 = new Music();

        music2.setArtists("asdf");
        music2.setName("asdf");
        music2.setId("helloworld");
        music2.setPopularity(42);
        music2.setDanceability(42d);
        music2.setEnergy(42d);
        music2.setMusicKey(42);
        music2.setLoudness(42d);
        music2.setMode(42);
        music2.setSpeechiness(42d);
        music2.setAcousticness(42d);
        music2.setInstrumentalness(42d);
        music2.setLiveness(42d);
        music2.setValence(42d);
        music2.setTempo(42d);
        music2.setDuration(42);
        music2.setTimeSignature(42);


        List<Music> musicList = new ArrayList<>();

        musicList.add(music1);
        musicList.add(music2);

//        this.musicService.addMusic(music1);
        this.musicService.addMusicList(musicList);

    }

    @Test
    public void deleteTest() {
        this.musicService.deleteMusicById("helloworld");
    }

    @Test
    public void updateTest() {
        Music music = this.musicService.getMusicById("asdf");

        music.setArtists("artists");
        music.getMusicEmotion().setFear(10d);

        this.musicService.updateMusic(music);
        this.musicService.updateMusicEmotion(music.getMusicEmotion());
    }

    @Test
    public void getFromFeatureRangeTest() {
        List<Music> musicList = this.musicService.getMusicListFromEmotionRange(Emotion.FEAR, fear -> fear >= 5);
        musicList.forEach(music -> System.out.println(music.getName()));
    }

    @Test
    public void musicListBuilderTest() {
        List<Music> musicList = this.musicService.getMusicListBuilder()
                .setEmotionRange(Emotion.FEAR, fear -> fear >= 5)
                .build();
        musicList.forEach(music -> System.out.println(music.getName()));
    }
}
