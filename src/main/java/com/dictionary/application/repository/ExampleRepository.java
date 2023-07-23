package com.dictionary.application.repository;

import com.dictionary.application.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExampleRepository extends JpaRepository<Example, Long> {
    @Modifying
    @Query("delete from Example e where e.card.id = :cardId")
    void deleteByCardId(@Param("cardId") long cardId);
}
