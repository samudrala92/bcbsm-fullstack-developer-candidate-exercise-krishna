package com.sample.demo.repo;

import com.sample.demo.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<Document, String> {
}
