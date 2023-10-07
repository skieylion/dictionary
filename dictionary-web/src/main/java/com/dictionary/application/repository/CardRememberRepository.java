package com.dictionary.application.repository;

import com.dictionary.application.domain.Card;
import com.dictionary.application.domain.CardParams;
import com.dictionary.application.domain.CardRemember;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CardRememberRepository extends JpaRepository<CardRemember, Long> {

    @Query("select cr from CardRemember cr  where cr.card.id=:cardId")
    Optional<CardRemember> findByCardId(@Param("cardId") long cardId);
}
