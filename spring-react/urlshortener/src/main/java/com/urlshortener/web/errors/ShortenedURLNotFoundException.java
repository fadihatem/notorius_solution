package com.urlshortener.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShortenedURLNotFoundException extends RuntimeException {
    public ShortenedURLNotFoundException(String shortenedURL) {
        super("Could not find shortened URL " + shortenedURL);
    }
}
