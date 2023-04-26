package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;

public class PlaylistSeriveTester {
    // TODO : 레포지토리에서 전체 음악을 가져오는 것을 해야됨. 그래야지 다음 단계를 진행할 수 있음. 에러나요.
    private final MusicRepository musicRepository;

    public PlaylistSeriveTester(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

    public void main(String[] args) {


        // PlaylistService playlistService = new PlaylistService();
        // Playlist playlist = playlistService.createPlaylist(Emotion.ANGER, Emotion.HAPPY);
        // System.out.println(playlist);

        System.out.println(this.musicRepository.selectAllMusic(true).size());

    }
}
