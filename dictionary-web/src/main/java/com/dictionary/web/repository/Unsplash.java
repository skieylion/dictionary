package com.dictionary.web.repository;

import com.dictionary.web.configuration.UnsplashHttpInterceptor;
import com.dictionary.web.domain.UnsplashResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://api.unsplash.com", value = "searchPhotosRepository",
        configuration = UnsplashHttpInterceptor.class)
public interface Unsplash {
    @GetMapping("/search/photos")
    UnsplashResponse findPictures(@RequestParam("query") String query);
}
