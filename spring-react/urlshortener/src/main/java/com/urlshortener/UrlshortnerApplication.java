package com.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
public class UrlshortnerApplication {

  public static void main(String[] args) {
    SpringApplication.run(UrlshortnerApplication.class, args);
  }

}
