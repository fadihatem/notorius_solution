package com.urlshortener.data.repositories;

import com.urlshortener.data.entities.URLEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IURLRepository extends JpaRepository<URLEntity, Long> {

    Optional<URLEntity> findByUrl(String url);

    Optional<URLEntity> findByShortenedURL(String shortenedURL);
}
