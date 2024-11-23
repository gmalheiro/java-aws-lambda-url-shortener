package com.java.urlShortener.service.url;

import java.util.Map;

public class UrlService {
    public Map<String,String> redirectUrl (Map<String, Object> input) {
        String pathParameters = (String) input.get("rawPath");
        String shortUrlCode = pathParameters.replace( "/", "");

        if (shortUrlCode == null) {
            throw  new IllegalArgumentException("Ivalid input: 'shortUrlCode' is required");
        }


        return  Map.of();
    }
}
