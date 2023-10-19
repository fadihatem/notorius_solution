package com.urlshortener.data.entities;

import com.urlshortener.helper.URLEntityHelper;
import jakarta.persistence.*;
import lombok.*;

import java.security.NoSuchAlgorithmException;

@Entity
@Table(name = "URL")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class URLEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long urlId;

    private String url;

    private String shortenedURL;

    @SneakyThrows
    public URLEntity(String url) throws NoSuchAlgorithmException {
        this.url=url;
        this.shortenedURL= URLEntityHelper.shortenURL(url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setShortenedURL(String shortenedURL) {
        this.shortenedURL = shortenedURL;
    }

    public String getUrl() {
        return url;
    }

    public String getShortenedURL() {
        return shortenedURL;
    }
}
