package com.swacademy.chamelodybackend.data;

import com.swacademy.chamelodybackend.data.repository.MusicJpaRepository;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;

import java.util.List;
import java.util.Optional;

public class MusicRepositoryImpl implements MusicRepository {

    private final MusicJpaRepository musicJpaRepository;

    public MusicRepositoryImpl(MusicJpaRepository musicJpaRepository) {
        this.musicJpaRepository = musicJpaRepository;
    }


    @Override
    public String insertMusic(Music music) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public List<Music> selectAllMusic(boolean getMusicEmotion) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Music selectMusicById(String musicId) throws IllegalArgumentException {
        return null;
    }

    @Override
    public Music selectMusicById(String musicId, boolean getMusicEmotion) {
        return null;
    }

    @Override
    public String updateMusic(Music updatedMusic) throws IllegalArgumentException, InternalPersistenceException {
        return null;
    }

    @Override
    public boolean deleteMusicById(String musicId) throws IllegalArgumentException, InternalPersistenceException {
        return false;
    }

    @Override
    public MusicEmotion selectMusicEmotionByMusicId(String musicId) throws IllegalArgumentException {
        return null;
    }
}
