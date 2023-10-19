package com.urlshortener.web.rest;

import com.urlshortener.data.entities.URLEntity;
import com.urlshortener.web.errors.ShortenedURLNotFoundException;
import com.urlshortener.web.errors.URLNotFoundException;
import com.urlshortener.data.repositories.IURLRepository;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static com.urlshortener.helper.URLEntityHelper.shortenURL;

@RestController
@RequestMapping("urlshortener")
public class URLShortnerController {
    public static final String MD_5 = "MD5";
    private final IURLRepository urlRepository;

    URLShortnerController(IURLRepository urlRepository) {

        this.urlRepository = urlRepository;
    }
    // Aggregate root
    // tag::get-aggregate-root[]
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/all")
    List<URLEntity> all() {

        return urlRepository.findAll();
    }
    // end::get-aggregate-root[]
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/new")
    URLEntity newEmployee(@RequestBody URLEntity urlEntity) {

        return urlRepository.save(urlEntity);
    }

    // Single item
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/urlshortener/{id}")
    URLEntity one(@PathVariable Long id) {

        return urlRepository.findById(id)
                .orElseThrow(() -> new URLNotFoundException(id));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/urlshortener/{id}")
    URLEntity replaceURL(@RequestBody URLEntity urlEntity, @PathVariable Long id) {

        return urlRepository.findById(id)
                .map(url -> {
                    url.setUrl(urlEntity.getUrl());
                    try {
                        url.setShortenedURL(shortenURL(urlEntity.getUrl()));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    return urlRepository.save(url);
                })
                .orElseGet(() -> {
                    return urlRepository.save(urlEntity);
                });
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/update/{url}")
    URLEntity replaceURL(@RequestBody String url) {

        return urlRepository.findByUrl(url)
                .map(urlEntity -> {
                    urlEntity.setUrl(url);
                    try {
                        urlEntity.setShortenedURL(shortenURL(url));
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    return urlRepository.save(urlEntity);
                })
                .orElseGet(() -> {
                    URLEntity urlEntity = null;
                    try {
                        urlEntity = new URLEntity(url);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    return urlRepository.save(urlEntity);
                });
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/decode/{encodedUrl}")
    URLEntity decodeURL(@RequestBody String shortenedURL) throws ShortenedURLNotFoundException {
        return urlRepository.findByShortenedURL(shortenedURL)
                .map(urlEntity -> {
                    urlEntity.setShortenedURL(shortenedURL);
                    return urlRepository.save(urlEntity);
                })
                .orElseThrow(() -> new ShortenedURLNotFoundException(shortenedURL));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/delete/{id}")
    void deleteURL(@PathVariable Long id) {

        urlRepository.deleteById(id);
    }
}
