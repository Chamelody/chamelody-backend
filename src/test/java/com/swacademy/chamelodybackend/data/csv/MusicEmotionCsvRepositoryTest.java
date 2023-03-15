package com.swacademy.chamelodybackend.data.csv;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ChamelodyBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MusicEmotionCsvRepositoryTest {

    @Autowired
    MusicEmotionCsvRepository musicEmotionCsvRepository;

    @Test
    public void selectAll() {
        List<MusicEmotionDataEntity> emotionList = musicEmotionCsvRepository.selectAll();
        emotionList.forEach(musicEmotionDataEntity -> System.out.println(musicEmotionDataEntity.getHappy()));
        System.out.println(emotionList.size());
    }

    @Test
    public void select() {
        System.out.println(musicEmotionCsvRepository.select("6CV6j2xz54thzlrWML3kAW").getId());
    }

    @Test
    public void insert() throws IOException {
        MusicEmotionDataEntity entity = new MusicEmotionDataEntity();

        entity.setId("asdf");
        entity.setHappy(0d);
        entity.setSad(0d);
        entity.setFear(0d);
        entity.setAnger(0d);
        entity.setLove(0d);
        entity.setDefaultMood(0d);
        entity.setRelax(0d);
        entity.setNervous(0d);
        entity.setSurprise(0d);
        entity.setTouch(0d);
        entity.setShame(0d);
        entity.setLonely(0d);
        entity.setLonging(0d);
        entity.setTired(0d);
        entity.setVitality(0d);
        entity.setPride(0d);

        musicEmotionCsvRepository.insert(entity);
        List<MusicEmotionDataEntity> musicList = musicEmotionCsvRepository.selectAll();
        musicList.forEach(musicDataEntity -> System.out.println(musicDataEntity.getId()));
        System.out.println(musicList.size());
    }

}
