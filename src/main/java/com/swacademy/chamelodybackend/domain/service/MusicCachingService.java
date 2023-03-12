package com.swacademy.chamelodybackend.domain.service;

import org.springframework.stereotype.Service;

@Service
public class MusicCachingService {

    private final MusicService musicService;

    public MusicCachingService(MusicService musicService) {
        this.musicService = musicService;
    }



}
