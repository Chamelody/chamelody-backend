package com.swacademy.chamelodybackend.domain.service.engine;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.Playlist;

public interface PlaylistGenerator {
    Playlist generate(Playlist playlist, int size);
}
