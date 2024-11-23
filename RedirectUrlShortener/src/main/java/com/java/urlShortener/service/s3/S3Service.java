package com.java.urlShortener.service.s3;

import software.amazon.awssdk.services.s3.model.GetObjectRequest;

public class S3Service {

    public void findObject(String shortUrlCode) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder().build();
    }

}
