package com.dictionary.web.repository;

import com.dictionary.web.domain.PartOfSpeech;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartOfSpeechRepository extends CrudRepository<PartOfSpeech, Long> {
}
