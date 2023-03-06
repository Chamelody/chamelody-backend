package com.swacademy.chamelodybackend.data.repository;

import com.swacademy.chamelodybackend.data.entity.MusicEmotionDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicEmotionJpaRepository extends JpaRepository<MusicEmotionDataEntity, String> {

}
