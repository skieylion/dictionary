package com.dictionary.application.utils;

import com.dictionary.application.domain.MediaFile;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;
import java.util.regex.Pattern;

public final class MediaBase64ToMediaFileConverter {
    private static final Pattern PATTERN_EXTENSION = Pattern.compile("\\/([^;]+);base64");
    private static final Pattern PATTERN_DATA = Pattern.compile("base64,(.*?)(?=,|$)");

    private MediaBase64ToMediaFileConverter() {

    }

    public static MediaFile convertToMediaFile(String dataSource) {
        var matcher = PATTERN_EXTENSION.matcher(dataSource);
        if (matcher.find()) {
            String ext = matcher.group(1);
            matcher = PATTERN_DATA.matcher(dataSource);
            if (matcher.find()) {
                String data = matcher.group(1);
                return new MediaFile(UUID.randomUUID().toString(),
                        ext, Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8)));
            }
        }
        throw new IllegalArgumentException("The data source is not valid");
    }

}
