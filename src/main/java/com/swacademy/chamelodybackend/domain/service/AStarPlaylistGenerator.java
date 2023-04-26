package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import com.swacademy.chamelodybackend.domain.entity.Track;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;
import com.swacademy.chamelodybackend.domain.service.engine.PlaylistGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
*   PLAYLIST에 음악을 채우는 GENERATOR
*   1. 감정노드가 거리가 충분할 경우
*       - A-Star 알고리즘을 이용해서 곡을 뽑는다.
*   2. 감정노드의 거리가 가까울 경우
*       - 주변에 있는 값들을 랜덤하게 뽑는다.
* */
@Component
public class AStarPlaylistGenerator implements PlaylistGenerator {
    private final MusicService musicService;
    private final List<Music> fullMusicList;

    public AStarPlaylistGenerator(MusicService musicService) {
        this.musicService = musicService;
        this.fullMusicList = musicService.getAllMusicList();
    }

    @Override
    public Playlist generate(Playlist playlist, int size){
        Music startMusic = playlist.getStartRepMusic();
        Music targetMusic = playlist.getTargetRepMusic();
        playlist.setMaximumSize(size);

        double start2target = getDistanceOfMusicVector(startMusic, targetMusic);
        if (start2target < 0.3) playlist = makeUndirectedPlaylist(playlist);
        else playlist = makeDirectedPlaylist(playlist, start2target);

        return playlist;
    }

    // 음악 feature 사이의 유클리드 거리 구하기
    public double getDistanceOfMusicVector(Music music1, Music music2) {
        double ret = 0.;
        ret += Math.abs(music1.getDanceability() - music2.getDanceability());
        ret += Math.abs(music1.getEnergy() - music2.getEnergy());
        ret += Math.abs(music1.getValence() - music2.getValence());
        return ret;
    }

    // 범위 내 음악 노드 수 구하기
    // 노드를 양끝점으로 하는 원 안의 노드를 구하기
    public List<Music> getListOfMusicInARange(Music music1, Music music2, double alpha) {
        double upperDanceability = Math.max(music1.getDanceability(), music2.getDanceability());
        double lowerDanceability = Math.min(music1.getDanceability(), music2.getDanceability());
        double upperEnergy = Math.max(music1.getEnergy(), music2.getEnergy());
        double lowerEnergy = Math.min(music1.getEnergy(), music2.getEnergy());
        double upperValence = Math.max(music1.getValence(), music2.getValence());
        double lowerValence = Math.min(music1.getValence(), music2.getValence());

        List<Music> retMusicList = new ArrayList<>();
        for (Music m : fullMusicList) {
            if (lowerDanceability - alpha <= m.getDanceability() && m.getDanceability() <= upperDanceability + alpha
                && lowerEnergy - alpha <= m.getEnergy() && m.getEnergy() <= upperEnergy + alpha
                && lowerValence - alpha <= m.getValence() && m.getValence() <= upperValence + alpha
                    && (m != music1) && (m != music2)
            ) retMusicList.add(m);
        }
        // TODO : 리스트에서 입력 music일 경우를 제외해야 함.
        return retMusicList;
    }


    public List<Music> getConnectedMusic(Music music, double alpha) {
        List<Music> retMusicList = new ArrayList<>();
        // music과 범위가 alpha 내에 있는 음악을 모두 구한다.
        for (Music m : fullMusicList) {
            if (Math.abs(m.getDanceability() - music.getDanceability()) <= alpha
                    && Math.abs(m.getEnergy() - music.getEnergy()) <= alpha
                    && Math.abs(m.getValence() - music.getValence()) <= alpha
                    && m.getPopularity() >= 10
                    && m != music
            ) retMusicList.add(m);
        }
        // TODO : 리스트에서 입력 music일 경우를 제외해야 함.
        return retMusicList;
    }


    // 랜덤하게 그냥 뽑아서 넣어부러
    public Playlist makeUndirectedPlaylist(Playlist playlist) {
        double alpha = 0.;
        int playlistSize = playlist.getMaximumSize() - 2;

        playlist.addMusic(playlist.getStartRepMusic());

        // 범위 안의 리스트 반환 : playlist 사이즈까지 범위를 넓혀나가며 탐색한다.
        List<Music> retlist = getListOfMusicInARange(playlist.getStartRepMusic(), playlist.getTargetRepMusic(), alpha);
        do {
            alpha += 0.05;
            retlist = getListOfMusicInARange(playlist.getStartRepMusic(), playlist.getTargetRepMusic(), alpha);
        } while (retlist.size() < playlistSize);

        // playlist 리스트를 벗어날 경우 : 랜덤으로 뽑는다.
        if (retlist.size() > playlistSize) {
            Collections.shuffle(retlist);
            retlist = new ArrayList<>(retlist.subList(0, playlistSize));
        }
        playlist.addAllMusic(retlist);
        playlist.addMusic(playlist.getTargetRepMusic());
        return playlist;
    }

    // 길찾기 시작
    // 1. 시작노드 우선순위 큐에 넣기
    // 2. 제일 값이 작은 노드 우선순위 큐에서 꺼내기
    // 3. 2번 노드와 연결된 노드 리스트 구하기
    // 4. 3번 리스트에서 목표 노드와 제일 가까운 노드를 다음 노드로 선정(큐에 넣기)
    // 5. 플레이리스트를 다 만들 때까지 해당 프로세스를 반복한다.
    //     다음 노드가 목표 노드라면 길찾기 종료...

    public Playlist makeDirectedPlaylist(Playlist playlist, double start2target) {
        Music start = playlist.getStartRepMusic();
        Music target = playlist.getTargetRepMusic();
        PriorityQueue<Track> pQueue = new PriorityQueue<>();
        pQueue.add(new Track(start, start2target));

        double old_alpha = start2target/playlist.getMaximumSize();
        double alpha = old_alpha;

        while(!pQueue.isEmpty()) {
            Track now = pQueue.poll();
            if (now.getValue() == 0) break;
            if (playlist.getMusicList().size() > 30) break;
            if (!playlist.addMusic(now.getMusic())) continue;

            List<Music> connectedMusicNodes = getConnectedMusic(now.getMusic(), alpha);
            do {
                alpha += old_alpha;
                connectedMusicNodes = getConnectedMusic(now.getMusic(), alpha);
            } while (connectedMusicNodes.size() < 5);
            alpha = old_alpha;

            // 연결 노드 size 조절
            if (connectedMusicNodes.size() > 20) {
                Collections.shuffle(connectedMusicNodes);
                connectedMusicNodes = new ArrayList<>(connectedMusicNodes.subList(0, 10));
            }

            // 나머지 친구들 넣기
            for (Music music : connectedMusicNodes) {
                pQueue.add(new Track(music, getDistanceOfMusicVector(music, target)));
            }
        }
        playlist.addMusic(target);
        return playlist;
    }

}
