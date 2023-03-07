package com.swacademy.chamelodybackend.domain.repository;

import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicEmotionRepository {
    /**
     * Insert music emotion entity. Data layer exception should be handled
     * and converted Java standard exception and the custom exception that defined in the domain layer.
     * @param musicEmotion music emotion entity
     * @return Return inserted music emotion id.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    String insertMusicEmotion(MusicEmotion musicEmotion) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Select music emotion entity by id. Data layer exception should be handled
     * and converted Java standard exception and the custom exception that defined in the domain layer.
     * @param musicEmotionId music emotion id
     * @return Return music emotion entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    MusicEmotion selectMusicEmotionById(String musicEmotionId) throws IllegalArgumentException;

    /**
     * Update music emotion to given music emotion entity.
     * Data layer exception should be handled and converted Java standard exception
     * and the custom exception that defined in the domain layer.
     * @param updatedMusic updated music emotion entity
     * @return Return updated music emotion id.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    String updateMusicEmotion(MusicEmotion updatedMusic) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Delete music emotion by id. Data layer exception should be handled
     * and converted Java standard exception and the custom exception that defined in the domain layer.
     * @param musicEmotionId music emotion id
     * @return Return the status of the deletion.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    boolean deleteMusicEmotionById(String musicEmotionId) throws IllegalArgumentException, InternalPersistenceException;
}
