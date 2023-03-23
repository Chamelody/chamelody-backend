package com.swacademy.chamelodybackend.data.jpa;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MusicRepositoryJpaImpl implements MusicRepository {

    private final MusicJpaRepository musicJpaRepository;

    public MusicRepositoryJpaImpl(MusicJpaRepository musicJpaRepository) {
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
    public void deleteMusicById(String musicId) throws IllegalArgumentException, InternalPersistenceException {
    }

    @Override
    public MusicEmotion selectMusicEmotionByMusicId(String musicId) throws IllegalArgumentException {
        return null;
    }
}
