package com.java.urlShortener.factory;

import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.urlShortener.entity.UrlData;
import com.java.urlShortener.service.S3Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ResponseFactory {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final S3Service s3Service = new S3Service();
    public static Map<String, String> createResponseFactory(Map<String, Object> input, Context context) {
        String body = input.get("body").toString();

        Map<String, String> bodyMap;

        try {
            bodyMap = objectMapper.readValue(body, Map.class);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Error parsing JSON body: " + exception.getMessage(), exception);
        }

        String originalUrl = bodyMap.get("originalUrl");
        String expirationTime = bodyMap.get("expirationTime");
        long   expirationTimeInSeconds = Long.parseLong(expirationTime);

        String shortUrlCode = UUID.randomUUID().toString().substring(0,8);

        UrlData urlData = new UrlData(originalUrl,expirationTimeInSeconds);

        try {
            s3Service.insertIntoS3(urlData,shortUrlCode);
        } catch(Exception e) {
            throw new RuntimeException("An error happened: " + e.getMessage(), e);
        }

        Map<String,String> response = new HashMap<>();
        response.put("code",shortUrlCode);

        return response;
    }
}
