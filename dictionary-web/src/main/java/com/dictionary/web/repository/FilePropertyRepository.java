package com.dictionary.web.repository;

import com.dictionary.web.domain.FileProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface FilePropertyRepository extends CrudRepository<FileProperty, UUID> {
    @Modifying
    @Query("delete from FileProperty ft where ft.id=:id")
    void removeById(@NotNull @Param("id") UUID id);
}
