package tech.igor.urlshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import tech.igor.urlshortener.entity.UrlEntity;
import tech.igor.urlshortener.repository.UrlRepository;

import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public String shortenUrl(String url) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, url, LocalDateTime.now().plusMinutes(1)));

        return id;
    }

    public String getFullUrl(String id) throws Exception {
        return urlRepository.findById(id)
                .map(UrlEntity::getFullUrl)
                .orElseThrow(() -> new IllegalArgumentException("URL not found"));
    }
}
