package com.dictionary.core.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class UnsplashHttpInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.query("client_id", "qgN0T6-J_ZwvJA_NxiMzdDvJPMSY4_MvndQmKBFAhls");
    }
}
