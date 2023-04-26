package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.ChamelodyBackendApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = ChamelodyBackendApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
class PlaylistSeriveTesterTest {

    @Autowired
    MusicService musicService;

    @Test
    public void test() {
        System.out.println(musicService.getAllMusicList());
    }

}