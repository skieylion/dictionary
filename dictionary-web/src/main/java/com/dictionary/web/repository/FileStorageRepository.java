package com.dictionary.web.repository;


import com.dictionary.web.domain.MediaFile;
import com.dictionary.web.exception.FileRepositoryException;
import io.minio.*;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Repository
public class FileStorageRepository {

    private final MinioClient clientS3;
    private final String bucketName;

    public FileStorageRepository(
            @Value("${app.s3.accessKey}") String accessKey,
            @Value("${app.s3.secretKey}") String secretKey,
            @Value("${app.s3.endpoint}") String endpoint,
            @Value("${app.s3.bucketName}") String bucketName) {
        this.bucketName = bucketName;
        clientS3 = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(accessKey, secretKey)
                .build();
    }

    public void save(MediaFile file) {
        try (InputStream is = new ByteArrayInputStream(file.getBytes())) {
            clientS3.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(file.getUuidAndExt())
                    .contentType(file.getMimeType())
                    .stream(is, file.getBytes().length, -1)
                    .build());
        } catch (IOException | MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new FileRepositoryException("The file is not uploaded", e);
        }
    }

    public Optional<MediaFile> findByName(String fileName) {
        try (InputStream is = clientS3.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(fileName)
                .build())) {
            return Optional.of(new MediaFile(fileName, is.readAllBytes()));
        } catch (IOException | MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            return Optional.empty();
        }
    }

    public void deleteByName(String fileName) {
        try {
            clientS3.removeObject(RemoveObjectArgs.builder().bucket(bucketName).object(fileName).build());
        } catch (IOException | MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new FileRepositoryException(String.format("The file with id [%s] is not removed", fileName), e);
        }
    }
}