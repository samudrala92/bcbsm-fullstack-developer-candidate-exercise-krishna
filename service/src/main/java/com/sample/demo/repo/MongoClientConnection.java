package com.sample.demo.repo;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Configuration
@EnableMongoRepositories
@EnableScheduling
public class MongoClientConnection {
    public List<com.sample.demo.model.Document> connectDB(List<com.sample.demo.model.Document> docList) {

        String connectionString = "mongodb+srv://samudrala92:Krishna92@cluster0.en8im0c.mongodb.net/?retryWrites=true&w=majority";

        ServerApi serverApi = ServerApi.builder()
                .version(ServerApiVersion.V1)
                .build();

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .serverApi(serverApi)
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("DocsDB");
                database.runCommand(new Document("ping", 1));

                // Access the collection
//                MongoCollection<Document> collection = database.getCollection("documents");
//
//                for(com.sample.demo.model.Document document: docList){
//
//                    // Prepare a document to insert
//                    Document doc = new Document("documentName", document.getDocumentName())
//                            .append("document", document.getDocument())
//                            .append("documentID", document.getDocumentID())
//                            .append("uploadTime", new Date())
//                            .append("uploadUser", document.getUploadUser());
//
//                    // Insert the document into the collection
//                    collection.insertOne(doc);
//                }
//
//                for(com.sample.demo.model.Document document: docList) {
//                    for (Document book : collection.find()) {
//                        if (book.containsValue(document.getDocumentID())){
//                            document.setUploadDate((Date) book.get("uploadTime"));
//                        }
//                    }
//                }

                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");

            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
        return mongoClient;
    }
}


