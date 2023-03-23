package com.swacademy.chamelodybackend.data.csv;

import com.swacademy.chamelodybackend.aop.PersistenceExceptionHandler;
import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import com.swacademy.chamelodybackend.data.mapper.MusicEmotionMapper;
import com.swacademy.chamelodybackend.data.mapper.MusicMapper;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Repository
@Primary
public class MusicCsvRepositoryImpl implements MusicRepository {

    private final MusicCsvRepository musicCsvRepository;
    private final MusicEmotionCsvRepository musicEmotionCsvRepository;
    private final MusicMapper musicMapper;
    private final MusicEmotionMapper musicEmotionMapper;

    public MusicCsvRepositoryImpl(
            MusicCsvRepository musicCsvRepository,
            MusicEmotionCsvRepository musicEmotionCsvRepository,
            MusicMapper musicMapper,
            MusicEmotionMapper musicEmotionMapper
    ) {
        this.musicCsvRepository = musicCsvRepository;
        this.musicEmotionCsvRepository = musicEmotionCsvRepository;
        this.musicMapper = musicMapper;
        this.musicEmotionMapper = musicEmotionMapper;
    }

    @Override
//    @PersistenceExceptionHandler
    public String insertMusic(Music music) throws IllegalArgumentException, InternalPersistenceException {
        MusicDataEntity musicDataEntity = this.musicMapper.toDataEntity(music);
        try {
            this.musicCsvRepository.insert(musicDataEntity);
            return music.getId();
        } catch (IOException ioException) {
            throw new PersistenceException(ioException.getMessage());
        }
    }

    @Override
    @PersistenceExceptionHandler
    public List<Music> selectAllMusic(boolean getMusicEmotion) throws IllegalArgumentException {
        List<MusicDataEntity> musicDataEntityList = this.musicCsvRepository.selectAll();
        if (getMusicEmotion) {
            return musicDataEntityList.stream().map(musicDataEntity -> {
                MusicEmotionDataEntity musicEmotionDataEntity = this.musicEmotionCsvRepository.select(musicDataEntity.getId());
                musicDataEntity.setMusicEmotion(musicEmotionDataEntity);
                return this.musicMapper.toDomainEntity(musicDataEntity);
            }).toList();
        } else return musicDataEntityList.stream().map(this.musicMapper::toDomainEntity).toList();
    }

    @Override
    @PersistenceExceptionHandler
    public Music selectMusicById(String musicId) throws IllegalArgumentException {
        MusicDataEntity musicDataEntity = this.musicCsvRepository.select(musicId);
        return this.musicMapper.toDomainEntity(musicDataEntity);
    }

    @Override
    @PersistenceExceptionHandler
    public Music selectMusicById(String musicId, boolean getMusicEmotion) throws IllegalArgumentException {
        MusicDataEntity musicDataEntity = this.musicCsvRepository.select(musicId);
        if (getMusicEmotion) {
            MusicEmotionDataEntity musicEmotionDataEntity = this.musicEmotionCsvRepository.select(musicId);
            musicDataEntity.setMusicEmotion(musicEmotionDataEntity);
        }
        return this.musicMapper.toDomainEntity(musicDataEntity);
    }

    @Override
    @PersistenceExceptionHandler
    public String updateMusic(Music updatedMusic) throws IllegalArgumentException, InternalPersistenceException {
        try {
            this.musicCsvRepository.update(this.musicMapper.toDataEntity(updatedMusic));
            return updatedMusic.getId();
        } catch (IOException ioException) {
            throw new PersistenceException(ioException.getMessage());
        }
    }

    @Override
    @PersistenceExceptionHandler
    public boolean deleteMusicById(String musicId) throws IllegalArgumentException, InternalPersistenceException {
        try {
            this.musicCsvRepository.delete(musicId);
            return true;
        } catch (IOException ioException) {
            throw new PersistenceException(ioException.getMessage());
        }
    }

    @Override
    @PersistenceExceptionHandler
    public MusicEmotion selectMusicEmotionByMusicId(String musicId) throws IllegalArgumentException {
        MusicEmotionDataEntity musicEmotionDataEntity = this.musicEmotionCsvRepository.select(musicId);
        return this.musicEmotionMapper.toDomainEntity(musicEmotionDataEntity);
    }

}
