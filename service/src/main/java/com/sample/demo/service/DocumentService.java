package com.sample.demo.service;

import com.sample.demo.model.Document;
import com.sample.demo.repo.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<Document> saveDocument(List<Document> documentList) {
        for (Document document: documentList) {
            document.setUploadDate(new Date());
            documentRepository.save(document);

        }
        /*for (Document document: documentList) {
            Optional<Document> doc = documentRepository.findById(document.getDocumentID());
            //documentRepository.save(document);

        }*/
        return documentList;
    }
}

