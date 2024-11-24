package com.java.urlShortener;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.java.urlShortener.service.url.UrlService;

import java.util.Map;

public class Main implements RequestHandler<Map<String, Object>, Map<String, Object>> {

    private final UrlService urlService = new UrlService();

    @Override
    public Map<String, Object> handleRequest(Map<String, Object> input, Context context) {
        return urlService.redirectUrl(input);
    }
}