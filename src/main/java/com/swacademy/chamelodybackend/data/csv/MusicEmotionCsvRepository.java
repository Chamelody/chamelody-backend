package com.swacademy.chamelodybackend.data.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MusicEmotionCsvRepository {

    private final static String MUSIC_EMOTION_FILE = "src/main/resources/music_emotion.csv";

    private final CsvObjectMapper csvObjectMapper;
    List<MusicEmotionDataEntity> cache = null;

    public MusicEmotionCsvRepository() throws IOException, CsvException {
        this.csvObjectMapper = new CsvObjectMapper();
        this.initializeCache();
    }

    private void initializeCache() throws IOException, CsvException {
        CSVReader csvReader = new CSVReader(new FileReader(MUSIC_EMOTION_FILE));
        List<String[]> lines = csvReader.readAll();
        this.cache = new ArrayList<>();
        lines.forEach(line -> this.cache.add(this.csvObjectMapper.csvLineToMusicEmotionDataEntity(line)));
        csvReader.close();
    }

    public MusicEmotionDataEntity insert(MusicEmotionDataEntity musicEmotionDataEntity) throws IOException, EntityExistsException {
        // Check duplicated id.
        boolean duplicateCheck = this.cache.stream().anyMatch(music -> music.equals(musicEmotionDataEntity));
        if (duplicateCheck) throw new EntityExistsException();

        CSVWriter csvAppender = new CSVWriter(new FileWriter(MUSIC_EMOTION_FILE, true));
        csvAppender.writeNext(this.csvObjectMapper.musicEmotionDataEntityToCsvLine(musicEmotionDataEntity));
        csvAppender.close();
        this.cache.add(musicEmotionDataEntity);
        return musicEmotionDataEntity;
    }

    public List<MusicEmotionDataEntity> insertAll(List<MusicEmotionDataEntity> allMusicEmotionList) throws IOException {
        // Check duplicated id.
        boolean duplicateCheck = allMusicEmotionList.stream().distinct().count() != allMusicEmotionList.size();
        if (duplicateCheck) throw new EntityExistsException();

        CSVWriter csvWriter = new CSVWriter(new FileWriter(MUSIC_EMOTION_FILE));
        List<String[]> lines = new ArrayList<>();
        allMusicEmotionList.forEach(musicEmotionDataEntity ->
                lines.add(this.csvObjectMapper.musicEmotionDataEntityToCsvLine(musicEmotionDataEntity)));
        csvWriter.writeAll(lines);
        csvWriter.close();
        this.cache = allMusicEmotionList;
        return this.cache;
    }

    public MusicEmotionDataEntity update(MusicEmotionDataEntity updatedMusicEmotion) throws IOException, EntityNotFoundException {
        List<MusicEmotionDataEntity> updateList = this.cache;
        this.select(updatedMusicEmotion.getId());  // It is makeshift for EntityNotFoundException. @TODO It should be changed.
        updateList.remove(updatedMusicEmotion);  // It can be done because ArrayList remove method uses 'equals' to compare.
        updateList.add(updatedMusicEmotion);
        this.insertAll(updateList);
        return updatedMusicEmotion;
    }

    public void delete(String musicId) throws IOException, EntityNotFoundException {
        List<MusicEmotionDataEntity> updateList = this.cache;
        this.select(musicId);  // It is makeshift for EntityNotFoundException. @TODO It should be changed.
        updateList.remove(this.select(musicId));  // It can be done because ArrayList remove method uses 'equals' to compare.
        this.insertAll(updateList);
    }

    public MusicEmotionDataEntity select(String musicId) throws EntityNotFoundException {
        Optional<MusicEmotionDataEntity> musicObject = this.cache.stream()
                .filter(music -> music.getId().equals(musicId))
                .findFirst();
        if (musicObject.isEmpty()) throw new EntityNotFoundException();
        return musicObject.get();
    }

    public List<MusicEmotionDataEntity> selectAll() {
        return this.cache;
    }
}
