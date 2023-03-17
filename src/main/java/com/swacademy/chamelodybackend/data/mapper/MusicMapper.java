package com.swacademy.chamelodybackend.data.mapper;

import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
import com.swacademy.chamelodybackend.domain.entity.Music;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MusicMapper {

    private final ModelMapper modelMapper;

    public MusicMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Music toDomainEntity(MusicDataEntity musicDataEntity) {
        return this.modelMapper.map(musicDataEntity, Music.class);
    }

    public MusicDataEntity toDataEntity(Music music) {
        return this.modelMapper.map(music, MusicDataEntity.class);
    }
}
