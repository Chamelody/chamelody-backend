package com.swacademy.chamelodybackend.data.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicEmotionCsvRepository {

    List<MusicEmotionDataEntity> cache = null;

    public MusicEmotionCsvRepository() {
    }

    public MusicEmotionDataEntity insert(MusicEmotionDataEntity musicEmotion) {
        return null;
    }

    public List<MusicEmotionDataEntity> insertAll(List<MusicEmotionDataEntity> allMusicEmotionList) {
        return null;
    }

    public MusicEmotionDataEntity update(MusicEmotionDataEntity updatedMusicEmotion) {
        return null;
    }

    public List<MusicEmotionDataEntity> updateAll(List<MusicEmotionDataEntity> updatedAllMusicEmotionList) {
        return null;
    }

    public boolean delete(String musicId) {
        return false;
    }

    public MusicEmotionDataEntity select(String musicId) {
        return null;
    }

    public List<MusicEmotionDataEntity> selectAll() {
        return null;
    }
}
