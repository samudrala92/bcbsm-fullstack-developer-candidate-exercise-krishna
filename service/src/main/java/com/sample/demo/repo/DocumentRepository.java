package com.sample.demo.repo;

import com.sample.demo.model.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface DocumentRepository extends MongoRepository<Document, String> {

    @Query(value="{'documentId': ?0}")
    Document findDocuments(String documentID);
}
