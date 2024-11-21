package com.java.urlShortener.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.urlShortener.entity.UrlData;
import com.java.urlShortener.utils.ApplicationPropertyUtil;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3Service {

    public S3Service() {
        super();
    }

    private final S3Client s3Client = S3Client.builder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void insertIntoS3(UrlData urlData, String urlCode) throws JsonProcessingException {
        String urlDataJson = objectMapper.writeValueAsString(urlData);
        try {
            PutObjectRequest request
                    = PutObjectRequest
                    .builder()
                    .bucket(ApplicationPropertyUtil.getProperty("bucket.name"))
                    .key(urlCode + ".json")
                    .build();
            s3Client.putObject(request, RequestBody.fromString(urlDataJson));
        } catch (Exception e) {
            throw new RuntimeException("Error saving url data to S3: " + e.getMessage(), e);
        }

    }

}
