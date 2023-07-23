package com.dictionary.application.repository;

import com.dictionary.application.domain.PartOfSpeech;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartOfSpeechRepository extends CrudRepository<PartOfSpeech, Long> {
}
