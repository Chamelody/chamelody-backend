package com.swacademy.chamelodybackend.presentation.mapper;

import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import org.modelmapper.ModelMapper;
import com.swacademy.chamelodybackend.presentation.dto.MusicEmotionDto;
import org.springframework.stereotype.Component;

@Component
public class PresentationMusicEmotionMapper {

    private final ModelMapper modelMapper;

    public PresentationMusicEmotionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public MusicEmotion toDomainEntity(MusicEmotionDto musicEmotionDto) {
        return this.modelMapper.map(musicEmotionDto, MusicEmotion.class);
    }

    public MusicEmotionDto toDto(MusicEmotion musicEmotion) {
        return this.modelMapper.map(musicEmotion, MusicEmotionDto.class);
    }
}
