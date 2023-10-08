package com.dictionary.core.repository;

import com.dictionary.core.domain.PartOfSpeech;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartOfSpeechRepository extends CrudRepository<PartOfSpeech, Long> {
}
