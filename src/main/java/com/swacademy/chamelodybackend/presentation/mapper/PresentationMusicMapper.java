package com.swacademy.chamelodybackend.presentation.mapper;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.presentation.dto.MusicDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PresentationMusicMapper {

    private final ModelMapper modelMapper;

    public PresentationMusicMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Music toDomainEntity(MusicDto musicDto) {
        return this.modelMapper.map(musicDto, Music.class);
    }

    public MusicDto toDto(Music music) {
        return this.modelMapper.map(music, MusicDto.class);
    }
}
