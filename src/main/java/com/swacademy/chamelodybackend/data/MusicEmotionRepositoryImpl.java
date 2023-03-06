package com.swacademy.chamelodybackend.data;

import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.repository.MusicEmotionRepository;

import java.util.Optional;

public class MusicEmotionRepositoryImpl implements MusicEmotionRepository {

    private final MusicEmotionRepository musicEmotionRepository;

    public MusicEmotionRepositoryImpl(MusicEmotionRepository musicEmotionRepository) {
        this.musicEmotionRepository = musicEmotionRepository;
    }


    @Override
    public String insertMusicEmotion(MusicEmotion musicEmotion) {
        return null;
    }

    @Override
    public Optional<MusicEmotion> selectMusicEmotionById(String musicEmotionId) {
        return Optional.empty();
    }

    @Override
    public String updateMusicEmotion(MusicEmotion updatedMusic) {
        return null;
    }

    @Override
    public boolean deleteMusicEmotionById(String musicEmotionId) {
        return false;
    }
}
