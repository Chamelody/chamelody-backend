package com.swacademy.chamelodybackend.presentation.controller;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.service.MusicService;
import com.swacademy.chamelodybackend.domain.service.PlaylistService;
import com.swacademy.chamelodybackend.presentation.dto.MakePlaylistParam;
import com.swacademy.chamelodybackend.presentation.dto.MusicDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.swacademy.chamelodybackend.presentation.mapper.PresentationMusicEmotionMapper;
import com.swacademy.chamelodybackend.presentation.mapper.PresentationMusicMapper;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MusicController {

    private final PresentationMusicMapper musicMapper;
    private final PresentationMusicEmotionMapper musicEmotionMapper;
    private final PlaylistService playlistService;

    @Autowired
    private MusicService musicService;

    public MusicController(PresentationMusicMapper musicMapper, PresentationMusicEmotionMapper musicEmotionMapper, PlaylistService playlistService) {
        this.musicMapper = musicMapper;
        this.musicEmotionMapper = musicEmotionMapper;
        this.playlistService = playlistService;
    }


    @PostMapping(value = "/playlist")
    public List<MusicDto> makePlaylistRequest(@RequestBody MakePlaylistParam makePlaylistParam) {
        return null;
    }

    @GetMapping(value = "/musiclist")
    public List<MusicDto> getAllMusicListRequest() {
        List<Music> musicList = this.musicService.getAllMusicList();
        List<MusicDto> musicDtoList = new ArrayList<>();
        musicList.forEach(music -> musicDtoList.add(this.musicMapper.toDto(music)));
        return musicDtoList;
    }

    @GetMapping(value = "/test")
    public String testRequest() {
        return "Hello, world!";
    }

}
