package com.dictionary.core.repository;

import com.dictionary.core.domain.Slot;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Long> {
    @NotNull
    List<Slot> findAll();
}
