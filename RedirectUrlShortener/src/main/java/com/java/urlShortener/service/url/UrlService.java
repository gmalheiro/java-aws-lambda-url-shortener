package com.java.urlShortener.service.url;

import com.java.urlShortener.service.s3.S3Service;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
public class UrlService {
    private final S3Service s3Service = new S3Service();

    public Map<String, Object> redirectUrl(Map<String, Object> input) {
        String pathParameters = (String) input.get("rawPath");
        String shortUrlCode = pathParameters.replace("/", "");
        if (shortUrlCode == null) {
            throw new IllegalArgumentException("Ivalid input: 'shortUrlCode' is required");
        }
        return s3Service.findObject(shortUrlCode);
    }
}
