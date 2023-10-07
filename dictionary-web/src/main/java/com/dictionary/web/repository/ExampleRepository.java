package com.dictionary.web.repository;

import com.dictionary.web.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    @Modifying
    @Query("delete from Example e where e.cardId = ?1")
    void deleteByCardId(Long cardId);
}
