package tech.igor.urlshortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import tech.igor.urlshortener.entity.UrlEntity;

public interface UrlRepository extends MongoRepository<UrlEntity, String> {}
