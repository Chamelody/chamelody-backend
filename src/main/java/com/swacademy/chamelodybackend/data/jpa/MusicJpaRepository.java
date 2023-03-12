package com.swacademy.chamelodybackend.data.jpa;

import com.swacademy.chamelodybackend.data.entity.MusicDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicJpaRepository extends JpaRepository<MusicDataEntity, String> {

}
