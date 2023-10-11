package com.dictionary.core.repository;

import com.dictionary.core.domain.Slot;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    @NotNull
    List<Slot> findAll();
}
