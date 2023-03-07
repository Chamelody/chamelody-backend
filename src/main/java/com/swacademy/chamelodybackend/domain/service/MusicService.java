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

    public Music getMusicById(String musicId) {
        return musicRepository.selectMusicById(musicId, true);
    }

    public MusicEmotion getMusicEmotionById(String musicEmotionId) {
        return musicRepository.selectMusicEmotionByMusicId(musicEmotionId);
    }

    public Predicate<Music> convertPredicateDoubleToMusic(Emotion targetEmotion, Predicate<Double> condition) {
        return switch (targetEmotion) {
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
    }

    public List<Music> getMusicListFromFeatureRange(Emotion targetEmotion, Predicate<Double> condition) {
        Predicate<Music> musicEntityCondition = this.convertPredicateDoubleToMusic(targetEmotion, condition);
        return this.getAllMusicList().stream().filter(musicEntityCondition).toList();
    }

    @Deprecated
    public List<Music> getMusicListFromFeatureRange(Predicate<Music> condition) {
        return this.getAllMusicList().stream().filter(condition).collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public List<Music> getMusicListFromFeatureRange(Object...targetEmotionAndCondition) {
        // arguments validation
        if (targetEmotionAndCondition.length % 2 != 0)
            throw new IllegalArgumentException("Arguments counts must be even.");
        for (int i = 0; i < targetEmotionAndCondition.length; i += 2) {
            if (!(targetEmotionAndCondition[i] instanceof Emotion))
                throw new IllegalArgumentException("Odd arguments should be instance of Emotion.");
            if (!(targetEmotionAndCondition[i + 1] instanceof Predicate))
                throw new IllegalArgumentException("Even arguments should be instance of Predicate<Double>");
        }
        // filtering
        List<Music> musicList = this.getAllMusicList();
        Emotion targetEmotion;
        Predicate<Double> condition;
        for (int i = 0; i < targetEmotionAndCondition.length; i += 2) {
            targetEmotion = (Emotion) targetEmotionAndCondition[i];
            condition = (Predicate<Double>) targetEmotionAndCondition[i + 1];
            Predicate<Music> musicEntityCondition = this.convertPredicateDoubleToMusic(targetEmotion, condition);
            musicList = musicList.stream().filter(musicEntityCondition).toList();
        }
        return musicList;
    }

    public List<Music> getMusicListByMainEmotion(Emotion targetEmotion) {
        // @TODO Implement this method.
        return null;
    }

    public List<Music> getMusicListFromReleaseDateRange(Predicate<LocalDate> condition) {
        Predicate<Music> musicEntityCondition = music -> condition.test(music.getReleaseDate());
        return this.getAllMusicList().stream().filter(musicEntityCondition).toList();
    }

    public List<Music> getMusicListFromCachedDateRange(Predicate<LocalDate> condition) {
        Predicate<Music> musicEntityCondition = music -> condition.test(music.getCachedDate());
        return this.getAllMusicList().stream().filter(musicEntityCondition).toList();
    }

    public MusicListBuilder getMusicListBuilder(List<Music> musicList) {
        return new MusicListBuilder(musicList);
    }

    public MusicListBuilder getMusicListBuilder() {
        return new MusicListBuilder();
    }

    class MusicListBuilder {

        private List<Music> musicList;

        public MusicListBuilder() {
            this.musicList = MusicService.this.getAllMusicList();
        }

        public MusicListBuilder(List<Music> musicList) {
            this.musicList = musicList;
        }

        MusicListBuilder setFeatureRange(Emotion targetEmotion, Predicate<Double> condition) {
            Predicate<Music> musicEntityCondition =
                    MusicService.this.convertPredicateDoubleToMusic(targetEmotion, condition);
            this.musicList = this.musicList.stream().filter(musicEntityCondition).toList();
            return this;
        }

        @Deprecated
        MusicListBuilder setFeatureRange(Predicate<Music> condition) {
            this.musicList = this.musicList.stream().filter(condition).toList();
            return this;
        }

        @SuppressWarnings("unchecked")
        MusicListBuilder setFeatureRange(Object...targetEmotionAndCondition) {
            // arguments validation
            if (targetEmotionAndCondition.length % 2 != 0)
                throw new IllegalArgumentException("Arguments counts must be even.");
            for (int i = 0; i < targetEmotionAndCondition.length; i += 2) {
                if (!(targetEmotionAndCondition[i] instanceof Emotion))
                    throw new IllegalArgumentException("Odd arguments should be instance of Emotion.");
                if (!(targetEmotionAndCondition[i + 1] instanceof Predicate))
                    throw new IllegalArgumentException("Even arguments should be instance of Predicate<Double>");
            }
            // filtering
            Emotion targetEmotion;
            Predicate<Double> condition;
            for (int i = 0; i < targetEmotionAndCondition.length; i += 2) {
                targetEmotion = (Emotion) targetEmotionAndCondition[i];
                condition = (Predicate<Double>) targetEmotionAndCondition[i + 1];
                Predicate<Music> musicEntityCondition =
                        MusicService.this.convertPredicateDoubleToMusic(targetEmotion, condition);
                this.musicList = this.musicList.stream().filter(musicEntityCondition).toList();
            }
            return this;
        }

        MusicListBuilder setMainEmotionFilter(Emotion targetEmotion) {
            // @TODO Implement this method.
            return this;
        }

        MusicListBuilder setReleaseDateRangeFilter(Predicate<LocalDate> condition) {
            Predicate<Music> musicEntityCondition = music -> condition.test(music.getReleaseDate());
            this.musicList = this.musicList.stream().filter(musicEntityCondition).toList();
            return this;
        }

        MusicListBuilder setCachedDateRangeFilter(Predicate<LocalDate> condition) {
            Predicate<Music> musicEntityCondition = music -> condition.test(music.getCachedDate());
            this.musicList = this.musicList.stream().filter(musicEntityCondition).toList();
            return this;
        }

        List<Music> build() {
            return this.getCurrentMusicList();
        }

        List<Music> getCurrentMusicList() {
            return this.musicList;
        }
    }

}
