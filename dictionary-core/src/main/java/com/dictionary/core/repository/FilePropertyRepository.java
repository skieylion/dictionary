package com.dictionary.core.repository;

import com.dictionary.core.domain.FileProperty;

import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface FilePropertyRepository extends CrudRepository<FileProperty, UUID> {
    @Modifying
    @Query("delete from FileProperty ft where ft.id=:id")
    void removeById(@NotNull @Param("id") UUID id);
}
