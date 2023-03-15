package com.swacademy.chamelodybackend.data.csv;

import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;

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

    public String[] musicDataEntityToLine(MusicDataEntity musicDataEntity) {
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

}
