package com.dictionary.application.service;


import com.dictionary.application.domain.FileProperty;
import com.dictionary.application.domain.MediaFile;
import com.dictionary.application.repository.FileStorageRepository;
import com.dictionary.application.repository.FilePropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class FilePropertyService {

    private final FilePropertyRepository filePropertyRepository;
    private final FileStorageRepository fileStorageRepository;

    @Transactional
    public FileProperty saveMediaFile(MediaFile file) {
        var fileProperty = filePropertyRepository.save(FileProperty.fromMediaFile(file));
        fileStorageRepository.save(file);
        return fileProperty;
    }

    @Transactional(readOnly = true)
    public Optional<MediaFile> findByFilePropertyId(String filePropertyId) {
        return Optional.ofNullable(filePropertyId).stream()
                .map(UUID::fromString)
                .map(filePropertyRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(this::findByFileProperty)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    @Transactional(readOnly = true)
    public Optional<MediaFile> findByFileProperty(FileProperty fileProperty) {
        return Optional.ofNullable(fileProperty).stream()
                .map(FileProperty::getExternalId)
                .map(fileStorageRepository::findByName)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }

    @Transactional(readOnly = true)
    public Optional<FileProperty> findById(String fileId) {
        return filePropertyRepository
                .findById(UUID.fromString(fileId))
                .stream()
                .peek(fileWrapper -> fileWrapper.setMediaFile(fileStorageRepository
                        .findByName(fileWrapper.getExternalId()).orElseThrow(EntityNotFoundException::new)))
                .findFirst();
    }

    @Transactional
    public void deleteById(String fileId) {
        Optional.ofNullable(fileId).stream()
                .peek(id -> filePropertyRepository.removeById(UUID.fromString(id)))
                .forEach(fileStorageRepository::deleteByName);
    }

    @Transactional
    public void deleteByFileProperty(FileProperty fileProperty) {
        deleteById(fileProperty.getId());
    }

}