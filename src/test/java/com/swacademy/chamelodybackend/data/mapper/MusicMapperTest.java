package com.swacademy.chamelodybackend.data.mapper;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import com.swacademy.chamelodybackend.domain.entity.Music;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = ChamelodyBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MusicMapperTest {

    @Autowired
    private DataMusicMapper musicMapper;

    @Test
    public void toDataEntity() {
        MusicDataEntity dataMusicEntity = new MusicDataEntity();

        dataMusicEntity.setArtists("asdf");
        dataMusicEntity.setName("asdf");
        dataMusicEntity.setId("asdf");
        dataMusicEntity.setPopularity(42);
        dataMusicEntity.setDanceability(42d);
        dataMusicEntity.setEnergy(42d);
        dataMusicEntity.setMusicKey(42);
        dataMusicEntity.setLoudness(42d);
        dataMusicEntity.setMode(42);
        dataMusicEntity.setSpeechiness(42d);
        dataMusicEntity.setAcousticness(42d);
        dataMusicEntity.setInstrumentalness(42d);
        dataMusicEntity.setLiveness(42d);
        dataMusicEntity.setValence(42d);
        dataMusicEntity.setTempo(42d);
        dataMusicEntity.setDuration(42);
        dataMusicEntity.setTimeSignature(42);

        MusicEmotionDataEntity dataMusicEmotionEntity = new MusicEmotionDataEntity();

        dataMusicEmotionEntity.setId("asdf");
        dataMusicEmotionEntity.setHappy(0d);
        dataMusicEmotionEntity.setSad(0d);
        dataMusicEmotionEntity.setFear(0d);
        dataMusicEmotionEntity.setAnger(0d);
        dataMusicEmotionEntity.setLove(0d);
        dataMusicEmotionEntity.setDefaultMood(0d);
        dataMusicEmotionEntity.setRelax(0d);
        dataMusicEmotionEntity.setNervous(0d);
        dataMusicEmotionEntity.setSurprise(0d);
        dataMusicEmotionEntity.setTouch(0d);
        dataMusicEmotionEntity.setShame(0d);
        dataMusicEmotionEntity.setLonely(0d);
        dataMusicEmotionEntity.setLonging(0d);
        dataMusicEmotionEntity.setTired(0d);
        dataMusicEmotionEntity.setVitality(0d);
        dataMusicEmotionEntity.setPride(0d);

        dataMusicEntity.setMusicEmotion(dataMusicEmotionEntity);

        Music music = musicMapper.toDomainEntity(dataMusicEntity);
        System.out.println(music.getMusicEmotion().getAnger());

        MusicDataEntity convertedMusicDataEntity = musicMapper.toDataEntity(music);

        System.out.println(convertedMusicDataEntity.getId());
        System.out.println(convertedMusicDataEntity.getMusicEmotion().getAnger());

    }


}
