package com.swacademy.chamelodybackend.data.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
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
public class MusicCsvRepository {

    private final static String MUSIC_FILE = "src/main/resources/music.csv";

    private final CsvObjectMapper csvObjectMapper;
    List<MusicDataEntity> cache = null;

    public MusicCsvRepository() throws IOException, CsvException {
        this.csvObjectMapper = new CsvObjectMapper();
        this.initializeCache();
    }

    private void initializeCache() throws IOException, CsvException {
        CSVReader csvReader = new CSVReaderBuilder(new FileReader(MUSIC_FILE)).withSkipLines(1).build();
        List<String[]> lines = csvReader.readAll();
        this.cache = new ArrayList<>();
        lines.forEach(line -> this.cache.add(this.csvObjectMapper.csvLineToMusicDataEntity(line)));
        csvReader.close();
    }

    public MusicDataEntity insert(MusicDataEntity musicDataEntity) throws IOException, EntityExistsException {
        // Check duplicated id.
        boolean duplicateCheck = this.cache.stream().anyMatch(music -> music.equals(musicDataEntity));
        if (duplicateCheck) throw new EntityExistsException();

        CSVWriter csvAppender = new CSVWriter(new FileWriter(MUSIC_FILE, true));
        csvAppender.writeNext(this.csvObjectMapper.musicDataEntityToCsvLine(musicDataEntity));
        csvAppender.close();
        this.cache.add(musicDataEntity);
        return musicDataEntity;
    }

    public List<MusicDataEntity> insertAll(List<MusicDataEntity> allMusicList) throws IOException {
        // Check duplicated id.
        boolean duplicateCheck = allMusicList.stream().distinct().count() != allMusicList.size();
        if (duplicateCheck) throw new EntityExistsException();

        CSVWriter csvWriter = new CSVWriter(new FileWriter(MUSIC_FILE));
        List<String[]> lines = new ArrayList<>();
        allMusicList.forEach(musicDataEntity ->
                lines.add(this.csvObjectMapper.musicDataEntityToCsvLine(musicDataEntity)));
        csvWriter.writeAll(lines);
        csvWriter.close();
        this.cache = allMusicList;
        return this.cache;
    }

    public MusicDataEntity update(MusicDataEntity updatedMusic) throws IOException, EntityNotFoundException {
        List<MusicDataEntity> updateList = this.cache;
        this.select(updatedMusic.getId());  // It is makeshift for EntityNotFoundException. @TODO It should be changed.
        updateList.remove(updatedMusic);  // It can be done because ArrayList remove method uses 'equals' to compare.
        updateList.add(updatedMusic);
        this.insertAll(updateList);
        return updatedMusic;
    }

    public void delete(String musicId) throws IOException, EntityNotFoundException {
        List<MusicDataEntity> updateList = this.cache;
        this.select(musicId);  // It is makeshift for EntityNotFoundException. @TODO It should be changed.
        updateList.remove(this.select(musicId));  // It can be done because ArrayList remove method uses 'equals' to compare.
        this.insertAll(updateList);
    }

    public MusicDataEntity select(String musicId) throws EntityNotFoundException {
        Optional<MusicDataEntity> musicObject = this.cache.stream()
                .filter(music -> music.getId().equals(musicId))
                .findFirst();
        if (musicObject.isEmpty()) throw new EntityNotFoundException();
        return musicObject.get();
    }

    public List<MusicDataEntity> selectAll() {
        return cache;
    }
}
