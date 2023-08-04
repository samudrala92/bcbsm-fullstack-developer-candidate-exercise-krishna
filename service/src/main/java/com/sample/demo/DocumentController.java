package com.sample.demo;

import com.sample.demo.model.Document;
import com.sample.demo.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {
    private final DocumentService documentService;

    //private  MongoClientConnection  mongoClientConnection = new MongoClientConnection();

    @Autowired
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping
    public ResponseEntity<List<Document>> addDocument(@RequestBody List<Document> documentList) {
        List<Document> savedDocument = documentService.saveDocument(documentList);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
//        return new ResponseEntity<>(mongoClientConnection.connectDB(documentList), HttpStatus.CREATED);
    }
}
