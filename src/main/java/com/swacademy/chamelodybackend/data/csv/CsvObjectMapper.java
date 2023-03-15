package com.swacademy.chamelodybackend.data.csv;

import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;

public class CsvObjectMapper {

    public MusicDataEntity csvLineToMusicDataEntity(String[] line) {
        MusicDataEntity musicDataEntity = new MusicDataEntity();

        musicDataEntity.setArtists(line[MusicCsvIndex.ARTISTS.index]);
        musicDataEntity.setName(line[MusicCsvIndex.NAME.index]);
        musicDataEntity.setId(line[MusicCsvIndex.ID.index]);
        musicDataEntity.setPopularity(Integer.parseInt(line[MusicCsvIndex.POPULARITY.index]));
        musicDataEntity.setDanceability(Double.parseDouble(line[MusicCsvIndex.DANCEABILITY.index]));
        musicDataEntity.setEnergy(Double.parseDouble(line[MusicCsvIndex.ENERGY.index]));
        musicDataEntity.setMusicKey(Integer.parseInt(line[MusicCsvIndex.MUSIC_KEY.index]));
        musicDataEntity.setLoudness(Double.parseDouble(line[MusicCsvIndex.LOUDNESS.index]));
        musicDataEntity.setMode(Integer.parseInt(line[MusicCsvIndex.MODE.index]));
        musicDataEntity.setSpeechiness(Double.parseDouble(line[MusicCsvIndex.SPEECHINESS.index]));
        musicDataEntity.setAcousticness(Double.parseDouble(line[MusicCsvIndex.ACOUSTICNESS.index]));
        musicDataEntity.setInstrumentalness(Double.parseDouble(line[MusicCsvIndex.INSTRUMENTALNESS.index]));
        musicDataEntity.setLiveness(Double.parseDouble(line[MusicCsvIndex.LIVENESS.index]));
        musicDataEntity.setValence(Double.parseDouble(line[MusicCsvIndex.VALENCE.index]));
        musicDataEntity.setTempo(Double.parseDouble(line[MusicCsvIndex.TEMPO.index]));
        musicDataEntity.setDuration(Integer.parseInt(line[MusicCsvIndex.DURATION.index]));
        musicDataEntity.setTimeSignature(Integer.parseInt(line[MusicCsvIndex.TIME_SIGNATURE.index]));

        return musicDataEntity;
    }

    public String[] musicDataEntityToCsvLine(MusicDataEntity musicDataEntity) {
        String[] line = new String[25];

        line[MusicCsvIndex.ARTISTS.index] = musicDataEntity.getArtists();
        line[MusicCsvIndex.NAME.index] = musicDataEntity.getName();
        line[MusicCsvIndex.ID.index] = musicDataEntity.getId();
        line[MusicCsvIndex.POPULARITY.index] = Integer.toString(musicDataEntity.getPopularity());
        line[MusicCsvIndex.DANCEABILITY.index] = Double.toString(musicDataEntity.getDanceability());
        line[MusicCsvIndex.ENERGY.index] = Double.toString(musicDataEntity.getEnergy());
        line[MusicCsvIndex.MUSIC_KEY.index] = Integer.toString(musicDataEntity.getMusicKey());
        line[MusicCsvIndex.LOUDNESS.index] = Double.toString(musicDataEntity.getLoudness());
        line[MusicCsvIndex.MODE.index] = Integer.toString(musicDataEntity.getMode());
        line[MusicCsvIndex.SPEECHINESS.index] = Double.toString(musicDataEntity.getSpeechiness());
        line[MusicCsvIndex.ACOUSTICNESS.index] = Double.toString(musicDataEntity.getAcousticness());
        line[MusicCsvIndex.INSTRUMENTALNESS.index] = Double.toString(musicDataEntity.getInstrumentalness());
        line[MusicCsvIndex.LIVENESS.index] = Double.toString(musicDataEntity.getLiveness());
        line[MusicCsvIndex.VALENCE.index] = Double.toString(musicDataEntity.getValence());
        line[MusicCsvIndex.TEMPO.index] = Double.toString(musicDataEntity.getTempo());
        line[MusicCsvIndex.DURATION.index] = Integer.toString(musicDataEntity.getDuration());
        line[MusicCsvIndex.TIME_SIGNATURE.index] = Integer.toString(musicDataEntity.getTimeSignature());

        return line;
    }

    public MusicEmotionDataEntity csvLineToMusicEmotionDataEntity(String[] line) {
        MusicEmotionDataEntity musicEmotionDataEntity = new MusicEmotionDataEntity();

        musicEmotionDataEntity.setId(line[MusicEmotionCsvIndex.ID.index]);
        musicEmotionDataEntity.setHappy(Double.parseDouble(line[MusicEmotionCsvIndex.HAPPY.index]));
        musicEmotionDataEntity.setSad(Double.parseDouble(line[MusicEmotionCsvIndex.SAD.index]));
        musicEmotionDataEntity.setFear(Double.parseDouble(line[MusicEmotionCsvIndex.FEAR.index]));
        musicEmotionDataEntity.setAnger(Double.parseDouble(line[MusicEmotionCsvIndex.ANGER.index]));
        musicEmotionDataEntity.setLove(Double.parseDouble(line[MusicEmotionCsvIndex.LOVE.index]));
        musicEmotionDataEntity.setDefaultMood(Double.parseDouble(line[MusicEmotionCsvIndex.DEFAULT_MOOD.index]));
        musicEmotionDataEntity.setRelax(Double.parseDouble(line[MusicEmotionCsvIndex.RELAX.index]));
        musicEmotionDataEntity.setNervous(Double.parseDouble(line[MusicEmotionCsvIndex.NERVOUS.index]));
        musicEmotionDataEntity.setSurprise(Double.parseDouble(line[MusicEmotionCsvIndex.SURPRISE.index]));
        musicEmotionDataEntity.setTouch(Double.parseDouble(line[MusicEmotionCsvIndex.TOUCH.index]));
        musicEmotionDataEntity.setShame(Double.parseDouble(line[MusicEmotionCsvIndex.SHAME.index]));
        musicEmotionDataEntity.setLonely(Double.parseDouble(line[MusicEmotionCsvIndex.LONELY.index]));
        musicEmotionDataEntity.setLonging(Double.parseDouble(line[MusicEmotionCsvIndex.LONGING.index]));
        musicEmotionDataEntity.setTired(Double.parseDouble(line[MusicEmotionCsvIndex.TIRED.index]));
        musicEmotionDataEntity.setVitality(Double.parseDouble(line[MusicEmotionCsvIndex.VITALITY.index]));
        musicEmotionDataEntity.setPride(Double.parseDouble(line[MusicEmotionCsvIndex.PRIDE.index]));

        return musicEmotionDataEntity;
    }

    public String[] musicEmotionDataEntityToCsvLine(MusicEmotionDataEntity musicEmotionDataEntity) {
        String[] line = new String[17];

        line[MusicEmotionCsvIndex.ID.index] = musicEmotionDataEntity.getId();
        line[MusicEmotionCsvIndex.HAPPY.index] = Double.toString(musicEmotionDataEntity.getHappy());
        line[MusicEmotionCsvIndex.SAD.index] = Double.toString(musicEmotionDataEntity.getSad());
        line[MusicEmotionCsvIndex.FEAR.index] = Double.toString(musicEmotionDataEntity.getFear());
        line[MusicEmotionCsvIndex.ANGER.index] = Double.toString(musicEmotionDataEntity.getAnger());
        line[MusicEmotionCsvIndex.LOVE.index] = Double.toString(musicEmotionDataEntity.getLove());
        line[MusicEmotionCsvIndex.DEFAULT_MOOD.index] = Double.toString(musicEmotionDataEntity.getDefaultMood());
        line[MusicEmotionCsvIndex.RELAX.index] = Double.toString(musicEmotionDataEntity.getRelax());
        line[MusicEmotionCsvIndex.NERVOUS.index] = Double.toString(musicEmotionDataEntity.getNervous());
        line[MusicEmotionCsvIndex.SURPRISE.index] = Double.toString(musicEmotionDataEntity.getSurprise());
        line[MusicEmotionCsvIndex.TOUCH.index] = Double.toString(musicEmotionDataEntity.getTouch());
        line[MusicEmotionCsvIndex.SHAME.index] = Double.toString(musicEmotionDataEntity.getShame());
        line[MusicEmotionCsvIndex.LONELY.index] = Double.toString(musicEmotionDataEntity.getLonely());
        line[MusicEmotionCsvIndex.LONGING.index] = Double.toString(musicEmotionDataEntity.getLonging());
        line[MusicEmotionCsvIndex.TIRED.index] = Double.toString(musicEmotionDataEntity.getTired());
        line[MusicEmotionCsvIndex.VITALITY.index] = Double.toString(musicEmotionDataEntity.getVitality());
        line[MusicEmotionCsvIndex.PRIDE.index] = Double.toString(musicEmotionDataEntity.getPride());

        return line;
    }
}
