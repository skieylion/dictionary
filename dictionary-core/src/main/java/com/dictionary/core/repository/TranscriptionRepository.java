package com.dictionary.core.repository;

import com.dictionary.core.domain.Transcription;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TranscriptionRepository extends CrudRepository<Transcription, Long> {
    Optional<Transcription> findByValue(String value);

    @Modifying
    @Query("delete from Transcription t where t.card.id = :cardId")
    void deleteByCardId(@Param("cardId") long cardId);
}
