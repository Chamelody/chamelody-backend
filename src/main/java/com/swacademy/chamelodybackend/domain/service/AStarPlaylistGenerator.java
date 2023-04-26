package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;
import com.swacademy.chamelodybackend.domain.entity.Track;
import com.swacademy.chamelodybackend.domain.service.engine.PlaylistGenerator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;


@Component
public class AStarPlaylistGenerator implements PlaylistGenerator {
    private final List<Music> fullMusicList;
    private static final int minimumSize = 10;  // 최소로 반환하는 리스트 사이즈

    public AStarPlaylistGenerator(MusicService musicService) {
        this.fullMusicList = musicService.getAllMusicList();
    }


    /**
     * Playlist generate method.
     * @param playlist empty playlist object
     * @param size playlist maximum size
     * @return filled playlist object
     */
    @Override
    public Playlist generate(Playlist playlist, int size){
        playlist.setMaximumSize(size);
        Music startMusic = playlist.getStartRepMusic();
        Music targetMusic = playlist.getTargetRepMusic();

        double start2target = calculateDistance(startMusic, targetMusic);
        if (start2target < 0.3) playlist = makeUndirectedPlaylist(playlist);
        else playlist = makeDirectedPlaylist(playlist, start2target);

        return playlist;
    }


    /**
     * Get the Euclidean distance between Music attribute values.
     * @return distance value
     */
    private double calculateDistance(Music music1, Music music2) {
        double danceabilityDistance = Math.abs(music1.getDanceability() - music2.getDanceability());
        double energyDistance = Math.abs(music1.getEnergy() - music2.getEnergy());
        double valenceDistance = Math.abs(music1.getValence() - music2.getValence());
        return danceabilityDistance + energyDistance + valenceDistance;
    }


    /**
     * Get music nodes between two music feature values.
     * The search range = each feature value + alpha
     * @param music1 music node
     * @param music2 music node
     * @param alpha additional range value
     * @return Music list
     */
    public List<Music> getListOfMusicInARange(Music music1, Music music2, double alpha) {
        // TODO : 이 메소드는 MusicService로 구현해야 됨.
        double[] upperBoundaries = {music1.getDanceability(), music1.getEnergy(), music1.getValence()};
        double[] lowerBoundaries = {music2.getDanceability(), music2.getEnergy(), music2.getValence()};

        for (int i = 0; i < upperBoundaries.length; i++) {
            double upperBoundary = Math.max(upperBoundaries[i], lowerBoundaries[i]);
            double lowerBoundary = Math.min(upperBoundaries[i], lowerBoundaries[i]);
            upperBoundaries[i] = upperBoundary + alpha;
            lowerBoundaries[i] = lowerBoundary - alpha;
        }
        // 해당 범위 안에 있는 리스트 반환
        return fullMusicList.stream()
                .filter(m -> !m.getId().equals(music1.getId()) && !m.getId().equals(music2.getId())
                        && lowerBoundaries[0] <= m.getDanceability() && m.getDanceability() <= upperBoundaries[0]
                        && lowerBoundaries[1] <= m.getEnergy() && m.getEnergy() <= upperBoundaries[1]
                        && lowerBoundaries[2] <= m.getValence() && m.getValence() <= upperBoundaries[2])
                .collect(Collectors.toList());
    }


    /**
     * Get music connected nodes from the music node.
     * Nodes were connected when the distance was close enough.
     * connect == alpha
     * @param music music node
     * @param alpha connected range
     * @return connected Music nodes list
     */
    public List<Music> getConnectedMusic(Music music, double alpha) {
        // TODO : 이 메소드는 MusicService로 구현해야 됨.
        return fullMusicList.stream()
                .filter(m -> !m.getId().equals(music.getId())
                        && Math.abs(m.getDanceability() - music.getDanceability()) <= alpha
                        && Math.abs(m.getEnergy() - music.getEnergy()) <= alpha
                        && Math.abs(m.getValence() - music.getValence()) <= alpha
                        && m.getPopularity() >= 10)
                .collect(Collectors.toList());
    }


    /**
     * Fill the random music in the Playlist list
     * The features of start music and target music are similar
     * @param playlist Playlist object with empty music list
     * @return Playlist object with full of music list
     */
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

        // 플레이리스트 최대 크기보다 더 많이 뽑은 경우 -> 제한 크기만큼 랜덤 추출
        if (retlist.size() > playlistSize) {
            Collections.shuffle(retlist);
            retlist = new ArrayList<>(retlist.subList(0, playlistSize));
        }
        playlist.addAllMusic(retlist);
        playlist.addMusic(playlist.getTargetRepMusic());
        return playlist;
    }


    /**
     * Fill the ordered music in the Playlist list
     * The features of start music and target music are different.
     * [HOW TO] A* - to reduce Heuristic value
     * (1) insert (@start2target, start node) in priority queue.
     * (2) pop node with the lowest Heuristic value(node2target).
     * (3) insert the nodes connected with (2) in priority queue.
     * (4) repeat step(2) ~ (3)
     * (5) if popped node is target node, finish the search.
     * @param playlist Playlist object with empty music list
     * @param start2target distance from start music to target music
     * @return Playlist object with full of music list
     */
    public Playlist makeDirectedPlaylist(Playlist playlist, double start2target) {
        Music start = playlist.getStartRepMusic();
        Music target = playlist.getTargetRepMusic();
        PriorityQueue<Track> pQueue = new PriorityQueue<>();

        pQueue.add(new Track(start, start2target));     // (1) 초기화

        double old_alpha = start2target/playlist.getMaximumSize();
        double alpha = old_alpha;

        while(!pQueue.isEmpty()) {
            Track now = pQueue.poll();          // (2) 휴리스틱 값이 최소인 노드 pop
            if (now.getValue() == 0) break;     // (5) 종료 조건
            if (!playlist.addMusic(now.getMusic())) continue;

            List<Music> connectedMusicNodes = getConnectedMusic(now.getMusic(), alpha);
            do {
                alpha += old_alpha;
                connectedMusicNodes = getConnectedMusic(now.getMusic(), alpha);
            } while (connectedMusicNodes.size() < minimumSize);
            alpha = old_alpha;

            // 연결 노드 무작위로 선택 - just for fun
            Collections.shuffle(connectedMusicNodes);

            // (3) 연결 노드 큐에 추가
            connectedMusicNodes.stream()
                    .map(music -> new Track(music, calculateDistance(music, target)))
                    .limit(minimumSize)
                    .forEach(pQueue::add);
        }
        playlist.addMusic(target);
        return playlist;
    }
}
