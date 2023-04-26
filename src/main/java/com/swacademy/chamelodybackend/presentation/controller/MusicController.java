package com.swacademy.chamelodybackend.presentation.controller;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import com.swacademy.chamelodybackend.domain.service.MusicService;
import com.swacademy.chamelodybackend.domain.service.PlaylistService;
import com.swacademy.chamelodybackend.presentation.dto.MakePlaylistParam;
import com.swacademy.chamelodybackend.presentation.dto.MusicDto;
import com.swacademy.chamelodybackend.presentation.dto.PlaylistDto;
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

    public MusicController(
            PresentationMusicMapper musicMapper,
            PresentationMusicEmotionMapper musicEmotionMapper,
            PlaylistService playlistService
    ) {
        this.musicMapper = musicMapper;
        this.musicEmotionMapper = musicEmotionMapper;
        this.playlistService = playlistService;
    }


    @PostMapping(value = "/playlist")
    public PlaylistDto makePlaylistRequest(@RequestBody MakePlaylistParam makePlaylistParam) {
        Playlist playlist =  playlistService.createPlaylist(makePlaylistParam.getFromEmotion(), makePlaylistParam.getToEmotion());
        playlist.getMusicList().forEach(System.out::println);
        PlaylistDto playlistDto = new PlaylistDto();
        List<MusicDto> musicDtoList = new ArrayList<>();
        playlist.getMusicList().forEach(music -> musicDtoList.add(this.musicMapper.toDto(music)));
        playlistDto.setMusicList(musicDtoList);
        playlistDto.setStartEmotion(playlist.getStartEmotion());
        playlistDto.setTargetEmotion(playlist.getTargetEmotion());
        playlistDto.setStartRepMusic(this.musicMapper.toDto(playlist.getStartRepMusic()));
        playlistDto.setTargetRepMusic(this.musicMapper.toDto(playlist.getTargetRepMusic()));
        return playlistDto;
    }

}
