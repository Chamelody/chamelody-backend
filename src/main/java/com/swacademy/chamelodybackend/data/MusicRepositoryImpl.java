package com.swacademy.chamelodybackend.data;

import com.swacademy.chamelodybackend.data.repository.MusicJpaRepository;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;

import java.util.List;
import java.util.Optional;

public class MusicRepositoryImpl implements MusicRepository {

    private final MusicJpaRepository musicJpaRepository;

    public MusicRepositoryImpl(MusicJpaRepository musicJpaRepository) {
        this.musicJpaRepository = musicJpaRepository;
    }


    @Override
    public String insertMusic(Music music) {
        return null;
    }

    @Override
    public List<Music> selectAllMusic() {
        return null;
    }

    @Override
    public Optional<Music> selectMusicById(String musicId) {
        return Optional.empty();
    }

    @Override
    public Optional<Music> selectMusicById(String musicId, boolean getMusicEmotion) {
        return Optional.empty();
    }

    @Override
    public String updateMusic(Music updatedMusic) {
        return null;
    }

    @Override
    public boolean deleteMusicById(String musicId) {
        return false;
    }

    @Override
    public MusicEmotion selectMusicEmotionByMusicId(String musicId) {
        return null;
    }
}
