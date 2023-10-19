package com.urlshortener.helper;

import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static com.urlshortener.helper.URLEntityHelper.shortenURL;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class URLEntityHelperTest {

    @Test
    void testShortenUrlNoChange() throws NoSuchAlgorithmException {
        assertEquals("https://fadisoft.com/", shortenURL("https://fadisoft.com/"));
    }

    @Test
    void testShortenUrlWithChange() throws NoSuchAlgorithmException {
        assertEquals("https://fadisoft.com/RpBQNRGyT2GXJg8wMYFZKg==", shortenURL("https://fadisoft.com/mobile/apps/"));
    }
}
