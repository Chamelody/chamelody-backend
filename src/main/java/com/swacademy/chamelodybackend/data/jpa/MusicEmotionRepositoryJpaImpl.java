package com.swacademy.chamelodybackend.data.jpa;

import com.swacademy.chamelodybackend.data.jpa.MusicEmotionJpaRepository;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.repository.MusicEmotionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class MusicEmotionRepositoryJpaImpl implements MusicEmotionRepository {

    private final MusicEmotionJpaRepository musicEmotionJpaRepository;

    public MusicEmotionRepositoryJpaImpl(MusicEmotionJpaRepository musicEmotionJpaRepository) {
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
