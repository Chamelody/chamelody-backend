package com.swacademy.chamelodybackend.domain.repository;

import com.swacademy.chamelodybackend.domain.entity.Music;

import java.util.List;

public interface MusicCachingRepository {
    List<Music> getMusicListFromApi();
}
