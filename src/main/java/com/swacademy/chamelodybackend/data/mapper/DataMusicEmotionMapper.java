package com.swacademy.chamelodybackend.data.mapper;

import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DataMusicEmotionMapper {

    private final ModelMapper modelMapper;

    public DataMusicEmotionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MusicEmotion toDomainEntity(MusicEmotionDataEntity musicEmotionDataEntity) {
        return this.modelMapper.map(musicEmotionDataEntity, MusicEmotion.class);
    }

    public MusicEmotionDataEntity toDataEntity(MusicEmotion musicEmotion) {
        return this.modelMapper.map(musicEmotion, MusicEmotionDataEntity.class);
    }

}
