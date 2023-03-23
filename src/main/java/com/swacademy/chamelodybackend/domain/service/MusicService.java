package com.swacademy.chamelodybackend.domain.service;

import com.swacademy.chamelodybackend.domain.entity.Emotion;
import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.repository.MusicEmotionRepository;
import com.swacademy.chamelodybackend.domain.repository.MusicRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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


    private MusicEmotion createDefaultMusicEmotion(String musicId) {
        MusicEmotion musicEmotion = new MusicEmotion();
        musicEmotion.setId(musicId);
        musicEmotion.setHappy(0d);
        musicEmotion.setSad(0d);
        musicEmotion.setFear(0d);
        musicEmotion.setAnger(0d);
        musicEmotion.setLove(0d);
        musicEmotion.setDefaultMood(0d);
        musicEmotion.setRelax(0d);
        musicEmotion.setNervous(0d);
        musicEmotion.setSurprise(0d);
        musicEmotion.setTouch(0d);
        musicEmotion.setShame(0d);
        musicEmotion.setLonely(0d);
        musicEmotion.setLonging(0d);
        musicEmotion.setTired(0d);
        musicEmotion.setVitality(0d);
        musicEmotion.setPride(0d);
        return musicEmotion;
    }

    /**
     * Add music.
     * @param music music entity that has music emotion entity.
     * @return saved music id
     */
    public String addMusic(Music music) {
        MusicEmotion musicEmotion = this.createDefaultMusicEmotion(music.getId());
        music.setMusicEmotion(musicEmotion);
        this.addMusicEmotion(musicEmotion);
        return musicRepository.insertMusic(music);
    }

    /**
     * Add music.
     * @param music music entity
     * @param musicEmotion music emotion entity
     * @return saved music id
     */
    public String addMusic(Music music, MusicEmotion musicEmotion) {
        music.setMusicEmotion(musicEmotion);
        this.addMusicEmotion(musicEmotion);
        return musicRepository.insertMusic(music);
    }

    /**
     * Add music list.
     * @param musicList music entity list that have music emotion entities.
     * @return saved music id list
     */
    public List<String> addMusicList(List<Music> musicList) {
        List<String> musicIdList = new ArrayList<>();
        musicList.forEach(music -> {
            MusicEmotion musicEmotion = this.createDefaultMusicEmotion(music.getId());
            music.setMusicEmotion(musicEmotion);
            this.addMusicEmotion(musicEmotion);
            String savedMusicId = this.musicRepository.insertMusic(music);
            musicIdList.add(savedMusicId);
        });
        return musicIdList;
    }

    /**
     * Add music emotion. It cannot be called outer class to ensure the safe relation of Music-MusicEmotion data.
     * @param musicEmotion music emotion entity
     * @return saved music emotion id
     */
    private String addMusicEmotion(MusicEmotion musicEmotion) {
        return musicEmotionRepository.insertMusicEmotion(musicEmotion);
    }

    /**
     * Delete music.
     * @param music music entity
     * @return the status of deletion
     */
    public void deleteMusic(Music music) {
        this.deleteMusicEmotion(music.getMusicEmotion());
        musicRepository.deleteMusicById(music.getId());
    }

    /**
     * Delete music by id.
     * @param musicId music id
     * @return the status of deletion
     */
    public void deleteMusicById(String musicId) {
        this.deleteMusicEmotionById(musicId);
        musicRepository.deleteMusicById(musicId);
    }

    /**
     * Delete music emotion. It cannot be called outer class to ensure the safe relation of Music-MusicEmotion data.
     * @param musicEmotion music emotion entity
     * @return the status of deletion
     */
    private void deleteMusicEmotion(MusicEmotion musicEmotion) {
        musicEmotionRepository.deleteMusicEmotionById(musicEmotion.getId());
    }

    /**
     * Delete music emotion by id.
     * It cannot be called outer class to ensure the safe relation of Music-MusicEmotion data.
     * @param musicEmotionId music emotion id
     * @return the status of deletion
     */
    private void deleteMusicEmotionById(String musicEmotionId) {
        musicEmotionRepository.deleteMusicEmotionById(musicEmotionId);
    }

    /**
     * Update music.
     * @param updatedMusic updated music entity
     * @return updated music id
     */
    public String updateMusic(Music updatedMusic) {
        return musicRepository.updateMusic(updatedMusic);
    }

    /**
     * Update music emotion.
     * @param updatedMusicEmotion updated music emotion entity
     * @return updated music emotion id
     */
    public String updateMusicEmotion(MusicEmotion updatedMusicEmotion) {
        return musicEmotionRepository.updateMusicEmotion(updatedMusicEmotion);
    }

    /**
     * Get all music list. It uses EAGER loading for music emotion by default,
     * as it is likely that music emotion data will be frequently accessed.
     * @return list of all music
     */
    public List<Music> getAllMusicList() {
        return musicRepository.selectAllMusic(true);
    }

    /**
     * Get music by id. It uses EAGER loading for music emotion by default,
     * as it is likely that music emotion data will be frequently accessed.
     * @param musicId music id
     * @return music entity
     */
    public Music getMusicById(String musicId) {
        return musicRepository.selectMusicById(musicId, true);
    }

    /**
     * Get music emotion.
     * @param musicEmotionId music emotion id
     * @return music emotion entity
     */
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

    public List<Music> getMusicListFromEmotionRange(Emotion targetEmotion, Predicate<Double> condition) {
        Predicate<Music> musicEntityCondition = this.convertPredicateDoubleToMusic(targetEmotion, condition);
        return this.getAllMusicList().stream().filter(musicEntityCondition).toList();
    }

    @Deprecated
    public List<Music> getMusicListFromEmotionRange(Predicate<Music> condition) {
        return this.getAllMusicList().stream().filter(condition).collect(Collectors.toList());
    }

    @Deprecated
    @SuppressWarnings("unchecked")
    public List<Music> getMusicListFromEmotionRange(Object...targetEmotionAndCondition) {
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

    public List<Music> getMusicListFromFeatureRange() {
        // @TODO Implement this method.
        return null;
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

        MusicListBuilder setEmotionRange(Emotion targetEmotion, Predicate<Double> condition) {
            Predicate<Music> musicEntityCondition =
                    MusicService.this.convertPredicateDoubleToMusic(targetEmotion, condition);
            this.musicList = this.musicList.stream().filter(musicEntityCondition).toList();
            return this;
        }

        @Deprecated
        MusicListBuilder setEmotionRange(Predicate<Music> condition) {
            this.musicList = this.musicList.stream().filter(condition).toList();
            return this;
        }

        @Deprecated
        @SuppressWarnings("unchecked")
        MusicListBuilder setEmotionRange(Object...targetEmotionAndCondition) {
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

        MusicListBuilder setFeatureRange() {
            // @TODO Implement this method.
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
