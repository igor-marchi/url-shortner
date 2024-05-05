package tech.igor.urlshortener.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.igor.urlshortener.controller.dto.ShortenUrlRequest;
import tech.igor.urlshortener.controller.dto.ShortenUrlResponse;
import tech.igor.urlshortener.service.UrlService;

import java.net.URI;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<ShortenUrlResponse> shortenUrl(@RequestBody ShortenUrlRequest request, HttpServletRequest httpServletRequest) {
        try {
            var id = urlService.shortenUrl(request.url());
            var redirectUrl = httpServletRequest.getRequestURL().toString().replace("shorten-url", id);

            return ResponseEntity.ok(new ShortenUrlResponse(redirectUrl));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirectToFullUrl(@PathVariable("id") String id) {
        try {
            var fullUrl = urlService.getFullUrl(id);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(fullUrl));
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
