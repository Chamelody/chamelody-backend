package com.swacademy.chamelodybackend.data;

import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.repository.MusicEmotionRepository;

import java.util.Optional;

public class MusicEmotionRepositoryImpl implements MusicEmotionRepository {

    private final MusicEmotionRepository musicEmotionRepository;

    public MusicEmotionRepositoryImpl(MusicEmotionRepository musicEmotionRepository) {
        this.musicEmotionRepository = musicEmotionRepository;
    }

    @Override
    public String insertMusicEmotion(MusicEmotion musicEmotion) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public MusicEmotion selectMusicEmotionById(String musicEmotionId) throws IllegalArgumentException {
        return null;
    }

    @Override
    public String updateMusicEmotion(MusicEmotion updatedMusic) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public boolean deleteMusicEmotionById(String musicEmotionId) throws IllegalArgumentException, InternalPersistenceException {
        return false;
    }
}
