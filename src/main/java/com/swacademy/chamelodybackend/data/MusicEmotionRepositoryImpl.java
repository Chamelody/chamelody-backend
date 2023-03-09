package com.swacademy.chamelodybackend.data;

import com.swacademy.chamelodybackend.data.repository.MusicEmotionJpaRepository;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.repository.MusicEmotionRepository;

import java.util.Optional;

public class MusicEmotionRepositoryImpl implements MusicEmotionRepository {

    private final MusicEmotionJpaRepository musicEmotionJpaRepository;

    public MusicEmotionRepositoryImpl(MusicEmotionJpaRepository musicEmotionJpaRepository) {
        this.musicEmotionJpaRepository = musicEmotionJpaRepository;
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
