package com.swacademy.chamelodybackend.domain.repository;

import com.swacademy.chamelodybackend.domain.entity.Music;
import com.swacademy.chamelodybackend.domain.entity.MusicEmotion;
import com.swacademy.chamelodybackend.domain.exception.InternalPersistenceException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicRepository {
    /**
     * Insert music entity. Data layer exception should be handled
     * and converted Java standard exception and the custom exception that defined in the domain layer.
     * @param music music entity
     * @return Return inserted music id.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    String insertMusic(Music music) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Select all music entities. Data layer exception should be handled and converted
     * Java standard exception and the custom exception that defined in the domain layer.
     * @param getMusicEmotion allow EAGER loading of music emotion.
     * @return Return list of music entities.
     */
    List<Music> selectAllMusic(boolean getMusicEmotion);

    /**
     * Select music by id. If there is no music entity matching
     * given music id, throw illegal argument exception.
     * Data layer exception should be handled and converted Java standard exception
     * and the custom exception that defined in the domain layer.
     * @param musicId music id
     * @return Return music entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    Music selectMusicById(String musicId) throws IllegalArgumentException;

    /**
     * Select music with music emotion entity by id.
     * If there is no music entity matching given music id, throw exception.
     * Data layer exception should be handled and converted Java standard exception
     * and the custom exception that defined in the domain layer.
     * @param musicId music id
     * @param getMusicEmotion allow EAGER loading of music emotion.
     * @return Return music entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    Music selectMusicById(String musicId, boolean getMusicEmotion);

    /**
     * Update music to given music entity. Data layer exception should be handled
     * and converted Java standard exception and the custom exception that defined in the domain layer.
     * @param updatedMusic updated music entity
     * @return Return updated music id.
     * @throws IllegalArgumentException When there is something wrong with given entity.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    String updateMusic(Music updatedMusic) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Delete music by id. Data layer exception should be handled
     * and converted Java standard exception and the custom exception that defined in the domain layer.
     * @param musicId music id
     * @return Return the status of the deletion.
     * @throws IllegalArgumentException When given id is wrong.
     * @throws InternalPersistenceException When an exception is thrown in the data layer.
     */
    boolean deleteMusicById(String musicId) throws IllegalArgumentException, InternalPersistenceException;

    /**
     * Select music emotion entity by music id. It can be replaced Music.getMusicEmotion method.
     * Data layer exception should be handled and converted Java standard exception
     * and the custom exception that defined in the domain layer.
     * @param musicId music id
     * @return Return music emotion entity. Not null.
     * @throws IllegalArgumentException When given id is wrong.
     * Include {@link org.springframework.dao.DataRetrievalFailureException DataRetrievalFailureException}.
     */
    MusicEmotion selectMusicEmotionByMusicId(String musicId) throws IllegalArgumentException;
}
