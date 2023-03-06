package com.swacademy.chamelodybackend.domain.repository;

import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MusicEmotionRepository {
    String insertMusicEmotion(MusicEmotion musicEmotion);
    Optional<MusicEmotion> selectMusicEmotionById(String musicEmotionId);
    String updateMusicEmotion(MusicEmotion updatedMusic);
    boolean deleteMusicEmotionById(String musicEmotionId);
}
