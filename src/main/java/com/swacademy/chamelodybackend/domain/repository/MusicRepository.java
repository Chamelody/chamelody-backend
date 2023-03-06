package com.swacademy.chamelodybackend.domain.repository;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MusicRepository {
    String insertMusic(Music music);
    List<Music> selectAllMusic();
    List<Music> selectAllMusic(boolean getMusicEmotion);
    Optional<Music> selectMusicById(String musicId);
    Optional<Music> selectMusicById(String musicId, boolean getMusicEmotion);
    String updateMusic(Music updatedMusic);
    boolean deleteMusicById(String musicId);
    MusicEmotion selectMusicEmotionByMusicId(String musicId);
}
