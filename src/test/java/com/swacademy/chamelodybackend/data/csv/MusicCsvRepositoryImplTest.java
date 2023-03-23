package com.swacademy.chamelodybackend.data.csv;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
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
class MusicCsvRepositoryImplTest {

    @Autowired
    MusicCsvRepositoryImpl musicCsvRepository;

    @Test
    public void test() {
        Music music = new Music();

        music.setArtists("asdfasdf");
        music.setName("asdf");
        music.setId("helloworld");
        music.setPopularity(42);
        music.setDanceability(42d);
        music.setEnergy(42d);
        music.setMusicKey(42);
        music.setLoudness(42d);
        music.setMode(42);
        music.setSpeechiness(42d);
        music.setAcousticness(42d);
        music.setInstrumentalness(42d);
        music.setLiveness(42d);
        music.setValence(42d);
        music.setTempo(42d);
        music.setDuration(42);
        music.setTimeSignature(42);

        MusicEmotion musicEmotion = new MusicEmotion();

        musicEmotion.setId(music.getId());
        musicEmotion.setHappy(0d);
        musicEmotion.setSad(0d);
        musicEmotion.setFear(0d);
        musicEmotion.setAnger(0d);
        musicEmotion.setLove(0d);
        musicEmotion.setDefaultMood(0d);
        musicEmotion.setRelax(0d);
        musicEmotion.setNervous(0d);
        musicEmotion.setSurprise(0d);
        musicEmotion.setTouch(0d);
        musicEmotion.setShame(0d);
        musicEmotion.setLonely(0d);
        musicEmotion.setLonging(0d);
        musicEmotion.setTired(0d);
        musicEmotion.setVitality(0d);
        musicEmotion.setPride(0d);

        music.setMusicEmotion(musicEmotion);

//        this.musicCsvRepository.insertMusic(music);

        List<Music> musicList = this.musicCsvRepository.selectAllMusic(true);

        musicList.forEach(music1 -> System.out.println(music1.getId() + music1.getMusicEmotion().getAnger()));

        music.setArtists("hello");

//        this.musicCsvRepository.updateMusic(music);

//        this.musicCsvRepository.deleteMusicById("asdfasdf");
    }

}
