package com.swacademy.chamelodybackend.data.csv;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ChamelodyBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class MusicCsvRepositoryTest {

    @Autowired
    MusicCsvRepository musicCsvRepository;

    @Test
    public void selectAll() {
        List<MusicDataEntity> musicList = musicCsvRepository.selectAll();
        musicList.forEach(musicDataEntity -> System.out.println(musicDataEntity.getId()));
        System.out.println(musicList.size());
    }

    @Test
    public void insert() throws IOException {
        MusicDataEntity entity = new MusicDataEntity();

        entity.setArtists("asdf");
        entity.setName("asdf");
        entity.setId("asdf");
        entity.setPopularity(42);
        entity.setDanceability(42d);
        entity.setEnergy(42d);
        entity.setMusicKey(42);
        entity.setLoudness(42d);
        entity.setMode(42);
        entity.setSpeechiness(42d);
        entity.setAcousticness(42d);
        entity.setInstrumentalness(42d);
        entity.setLiveness(42d);
        entity.setValence(42d);
        entity.setTempo(42d);
        entity.setDuration(42);
        entity.setTimeSignature(42);
        musicCsvRepository.insert(entity);
        List<MusicDataEntity> musicList = musicCsvRepository.selectAll();
        musicList.forEach(musicDataEntity -> System.out.println(musicDataEntity.getId()));
        System.out.println(musicList.size());
    }

    @Test
    public void insertAll() throws IOException {
        MusicDataEntity entity = new MusicDataEntity();

        entity.setArtists("asdf");
        entity.setName("asdf");
        entity.setId("asdf");
        entity.setPopularity(42);
        entity.setDanceability(42d);
        entity.setEnergy(42d);
        entity.setMusicKey(42);
        entity.setLoudness(42d);
        entity.setMode(42);
        entity.setSpeechiness(42d);
        entity.setAcousticness(42d);
        entity.setInstrumentalness(42d);
        entity.setLiveness(42d);
        entity.setValence(42d);
        entity.setTempo(42d);
        entity.setDuration(42);
        entity.setTimeSignature(42);

        List<MusicDataEntity> musicDataEntityList = new ArrayList<>();
        musicDataEntityList.add(entity);
        musicCsvRepository.insertAll(musicDataEntityList);
        List<MusicDataEntity> musicList = musicCsvRepository.selectAll();
        musicList.forEach(musicDataEntity -> System.out.println(musicDataEntity.getId()));
        System.out.println(musicList.size());
    }

    @Test
    public void update() throws IOException {
        MusicDataEntity entity = new MusicDataEntity();

        entity.setArtists("asdf");
        entity.setName("asdf");
        entity.setId("5WjiDiNBOoLCqPz8qzK1xS");
        entity.setPopularity(42);
        entity.setDanceability(42d);
        entity.setEnergy(42d);
        entity.setMusicKey(42);
        entity.setLoudness(42d);
        entity.setMode(42);
        entity.setSpeechiness(42d);
        entity.setAcousticness(42d);
        entity.setInstrumentalness(42d);
        entity.setLiveness(42d);
        entity.setValence(42d);
        entity.setTempo(42d);
        entity.setDuration(42);
        entity.setTimeSignature(42);

        musicCsvRepository.update(entity);
    }

    @Test
    public void delete() throws IOException {
        MusicDataEntity entity = new MusicDataEntity();

        entity.setArtists("asdf");
        entity.setName("asdf");
        entity.setId("5WjiDiNBOoLCqPz8qzK1xS");
        entity.setPopularity(42);
        entity.setDanceability(42d);
        entity.setEnergy(42d);
        entity.setMusicKey(42);
        entity.setLoudness(42d);
        entity.setMode(42);
        entity.setSpeechiness(42d);
        entity.setAcousticness(42d);
        entity.setInstrumentalness(42d);
        entity.setLiveness(42d);
        entity.setValence(42d);
        entity.setTempo(42d);
        entity.setDuration(42);
        entity.setTimeSignature(42);

        musicCsvRepository.delete(entity.getId());
    }

}
