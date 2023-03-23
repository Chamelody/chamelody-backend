package com.swacademy.chamelodybackend.data.csv;

import com.swacademy.chamelodybackend.aop.PersistenceExceptionHandler;
import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import com.swacademy.chamelodybackend.data.mapper.MusicEmotionMapper;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.repository.MusicEmotionRepository;
import jakarta.persistence.PersistenceException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
@Primary
public class MusicEmotionCsvRepositoryImpl implements MusicEmotionRepository {

    private final MusicEmotionCsvRepository musicEmotionCsvRepository;
    private final MusicEmotionMapper musicEmotionMapper;

    public MusicEmotionCsvRepositoryImpl(
            MusicEmotionCsvRepository musicEmotionCsvRepository,
            MusicEmotionMapper musicEmotionMapper
    ) {
        this.musicEmotionCsvRepository = musicEmotionCsvRepository;
        this.musicEmotionMapper = musicEmotionMapper;
    }

    @Override
    @PersistenceExceptionHandler
    public String insertMusicEmotion(MusicEmotion musicEmotion) throws IllegalArgumentException, InternalPersistenceException {
        MusicEmotionDataEntity musicEmotionDataEntity = this.musicEmotionMapper.toDataEntity(musicEmotion);
        try {
            this.musicEmotionCsvRepository.insert(musicEmotionDataEntity);
            return musicEmotion.getId();
        } catch (IOException ioException) {
            throw new InternalPersistenceException(ioException.getMessage());
        }
    }

    @Override
    @PersistenceExceptionHandler
    public MusicEmotion selectMusicEmotionById(String musicEmotionId) throws IllegalArgumentException {
        MusicEmotionDataEntity musicEmotionDataEntity = this.musicEmotionCsvRepository.select(musicEmotionId);
        return this.musicEmotionMapper.toDomainEntity(musicEmotionDataEntity);
    }

    @Override
    @PersistenceExceptionHandler
    public String updateMusicEmotion(MusicEmotion updatedMusic) throws IllegalArgumentException, InternalPersistenceException {
        try {
            MusicEmotionDataEntity musicEmotionDataEntity = this.musicEmotionMapper.toDataEntity(updatedMusic);
            return this.musicEmotionCsvRepository.update(musicEmotionDataEntity).getId();
        } catch (IOException ioException) {
            throw new InternalPersistenceException(ioException.getMessage());
        }
    }

    @Override
    public void deleteMusicEmotionById(String musicEmotionId) throws IllegalArgumentException, InternalPersistenceException {
        try {
            this.musicEmotionCsvRepository.delete(musicEmotionId);
        } catch (IOException ioException) {
            throw new PersistenceException(ioException.getMessage());
        }
    }
    
}
