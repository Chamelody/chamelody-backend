package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.repository.MusicEmotionRepository;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class MusicService {

    private final MusicRepository musicRepository;
    private final MusicEmotionRepository musicEmotionRepository;

    public MusicService(MusicRepository musicRepository, MusicEmotionRepository musicEmotionRepository) {
        this.musicRepository = musicRepository;
        this.musicEmotionRepository = musicEmotionRepository;
    }


    public String addMusic(Music music) {
        return musicRepository.insertMusic(music);
    }

    public List<Music> addMusicList(List<Music> musicList) {
        for (Music music: musicList) musicRepository.insertMusic(music);
        return musicList;
    }

    public String addMusicEmotion(MusicEmotion musicEmotion) {
        return musicEmotionRepository.insertMusicEmotion(musicEmotion);
    }

    public boolean deleteMusic(Music music) {
        return musicRepository.deleteMusicById(music.getId());
    }

    public boolean deleteMusicById(String musicId) {
        return musicRepository.deleteMusicById(musicId);
    }

    public boolean deleteMusicEmotion(MusicEmotion musicEmotion) {
        return musicEmotionRepository.deleteMusicEmotionById(musicEmotion.getId());
    }

    public boolean deleteMusicEmotionById(String musicEmotionId) {
        return musicEmotionRepository.deleteMusicEmotionById(musicEmotionId);
    }

    public String updateMusic(Music updatedMusic) {
        return musicRepository.updateMusic(updatedMusic);
    }

    public String updateMusicEmotion(MusicEmotion updatedMusicEmotion) {
        return musicEmotionRepository.updateMusicEmotion(updatedMusicEmotion);
    }

    public List<Music> getAllMusicList() {
        return musicRepository.selectAllMusic(true);
    }

    public Optional<Music> getMusicById(String musicId) {
        return musicRepository.selectMusicById(musicId, true);
    }

    public Optional<MusicEmotion> getMusicEmotionById(String musicEmotionId) {
        return musicEmotionRepository.selectMusicEmotionById(musicEmotionId);
    }

    public List<Music> getMusicListFromFeatureRange(Emotion targetEmotion, Predicate<Double> condition) {
        Predicate<Music> musicEntityCondition = switch (targetEmotion) {
            case HAPPY -> music -> condition.test(music.getMusicEmotion().getHappy());
            case SAD -> music -> condition.test(music.getMusicEmotion().getSad());
            case FEAR -> music -> condition.test(music.getMusicEmotion().getFear());
            case ANGER -> music -> condition.test(music.getMusicEmotion().getAnger());
            case LOVE -> music -> condition.test(music.getMusicEmotion().getLove());
            case DEFAULT_MOOD -> music -> condition.test(music.getMusicEmotion().getDefaultMood());
            case RELAX -> music -> condition.test(music.getMusicEmotion().getRelax());
            case NERVOUS -> music -> condition.test(music.getMusicEmotion().getNervous());
            case SURPRISE -> music -> condition.test(music.getMusicEmotion().getSurprise());
            case TOUCH -> music -> condition.test(music.getMusicEmotion().getTouch());
            case SHAME -> music -> condition.test(music.getMusicEmotion().getShame());
            case LONELY -> music -> condition.test(music.getMusicEmotion().getLonely());
            case LONGING -> music -> condition.test(music.getMusicEmotion().getLonging());
            case TIRED -> music -> condition.test(music.getMusicEmotion().getTired());
            case VITALITY -> music -> condition.test(music.getMusicEmotion().getVitality());
            case PRIDE -> music -> condition.test(music.getMusicEmotion().getPride());
        };

        return this.getAllMusicList().stream().filter(musicEntityCondition).toList();
    }

    @Deprecated
    public List<Music> getMusicListFromFeatureRange(Predicate<Music> condition) {
        return this.getAllMusicList().stream().filter(condition).collect(Collectors.toList());
    }

    public List<Music> getMusicListFromFeatureRange(Object...targetEmotionAndCondition) {
        return null;
    }

    public List<Music> getMusicListFromGaussianFeatureRange(Emotion targetEmotion, double centerValue, int sampleCount) {
        return null;
    }

    public List<Music> getMusicListFromGaussianFeatureRange(Object...targetEmotionAndCenterValue) {
        return null;
    }

    public List<Music> getMusicListByMainEmotion(Emotion targetEmotion) {
        return null;
    }

    public List<Music> getMusicListFromReleaseDateRange(Predicate<LocalDate> condition) {
        return null;
    }

    public List<Music> getMusicListFromCachedDateRange(Predicate<LocalDate> condition) {
        return null;
    }

    public MusicListBuilder getMusicListBuilder(List<Music> musicList) {
        return new MusicListBuilder(musicList);
    }

    public MusicListBuilder getMusicListBuilder() {
        return new MusicListBuilder(this.getAllMusicList());
    }

    static class MusicListBuilder {

        private final List<Music> musicList;

        public MusicListBuilder(List<Music> musicList) {
            this.musicList = musicList;
        }

        MusicListBuilder setFeatureRange(String targetEmotion, Predicate<Integer> condition) {
            return null;
        }

        MusicListBuilder setFeatureRange(Predicate<Music> condition) {
            return null;
        }

        MusicListBuilder setFeatureRange(Object...targetEmotionAndCondition) {
            return null;
        }

        MusicListBuilder setGaussianFeatureRangeFilter(String targetEmotion, double centerValue) {
            return null;
        }

        MusicListBuilder setGaussianFeatureRangeFilter(Object...targetEmotionAndCenterValue) {
            return null;
        }

        MusicListBuilder setMainEmotionFilter(Emotion targetEmotion) {
            return null;
        }

        MusicListBuilder setReleaseDateRangeFilter(Predicate<LocalDate> condition) {
            return null;
        }

        MusicListBuilder setCachedDateRangeFilter(Predicate<LocalDate> condition) {
            return null;
        }

        List<Music> build() {
            return this.getCurrentMusicList();
        }

        List<Music> getCurrentMusicList() {
            return this.musicList;
        }
    }

}
