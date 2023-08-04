package com.sample.demo.model;

import org.springframework.data.annotation.Id;
import lombok.Data;

import java.util.Date;

@Data
@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
public class Document {
    @Id
    private String documentID;
    private String documentName;
    private String document;
    private String uploadUser;
    private Date uploadDate;
}
