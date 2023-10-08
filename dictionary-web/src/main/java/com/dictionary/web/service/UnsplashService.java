package com.dictionary.web.service;

import com.dictionary.core.domain.PictureFile;
import com.dictionary.core.domain.UnsplashResponse;
import com.dictionary.core.repository.Unsplash;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UnsplashService {
    private static final int LIMIT = 5;
    private final Unsplash unsplash;

    public List<PictureFile> findPictures(String query) {
        return Optional.ofNullable(unsplash.findPictures(query)).stream()
                .map(UnsplashResponse::getResults)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .map(UnsplashResponse.Result::getUrls)
                .filter(Objects::nonNull)
                .map(UnsplashResponse.URL::getFull)
                .filter(Objects::nonNull)
                .limit(LIMIT)
                .map(this::getPictureByUrl)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private Optional<PictureFile> getPictureByUrl(String url) {
        try {
            var ext = extractExtensionFromUrl(url);
            var response = HttpClient.newBuilder().build()
                    .send(HttpRequest.newBuilder().uri(new URI(url)).GET().build(),
                            HttpResponse.BodyHandlers.ofByteArray());
            return Optional.of(new PictureFile(String.format("picture.%s", ext), response.body()));
        } catch (IOException | InterruptedException | URISyntaxException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    private String extractExtensionFromUrl(String url) {
        var matcher = Pattern.compile("(?<=fm=)[^&]*").matcher(url);
        if (matcher.find()) {
            return matcher.group();
        }
        throw new IllegalArgumentException(String.format("The url [%s] is not correct", url));
    }
}
