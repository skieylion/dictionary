package com.dictionary.core.repository;

import com.dictionary.core.domain.CardRemember;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRememberRepository extends JpaRepository<CardRemember, Long> {

    @Query("select cr from CardRemember cr  where cr.card.id=:cardId")
    Optional<CardRemember> findByCardId(@Param("cardId") long cardId);
}
