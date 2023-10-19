package com.urlshortener.web.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class URLNotFoundException extends RuntimeException {
    public URLNotFoundException(Long id) {
        super("Could not find URL " + id);
    }

    public URLNotFoundException(String url) {
        super("Could not find URL " + url);
    }
}
