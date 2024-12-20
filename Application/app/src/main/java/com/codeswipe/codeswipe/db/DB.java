package com.codeswipe.codeswipe.db;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.connection.ClusterConnectionMode;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class DB {

    private static final String CONNECTION_STRING = "mongodb://spamsharmask:skqwerty@test.zt5blxl.mongodb.net/test?retryWrites=true&w=majority&connectTimeoutMS=30000&socketTimeoutMS=30000";
    private static final String DATABASE_NAME = "UserData";
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> db;

    static MongoClientSettings settings =MongoClientSettings.builder().applyToClusterSettings(builder -> builder.mode(ClusterConnectionMode.SINGLE)).build();

    static {
        try {
            mongoClient = MongoClients.create(CONNECTION_STRING);
            database = mongoClient.getDatabase(DATABASE_NAME);
            db = database.getCollection("Users");
        } catch (Exception e) {
            // Handle initialization error gracefully
            e.printStackTrace();
            throw new RuntimeException("Failed to initialize MongoDB client or database", e);
        }
    }

    public Boolean login(String username, String password) {
        if (checkIfNotPresent(username)) {
            return false;
        }
        Document filter = new Document("username", username);
        Document data = db.find(filter).first();
        if (data != null) {
            return data.getString("password").equals(password);
        }
        return false;
    }

    public Boolean checkIfNotPresent(String username) {
        Document filter = new Document("username", username);
        return db.find(filter).first() == null;
    }

    public Boolean signup(String username, String password, List<String> techStack, List<String> favProject, String snippet) {
        if (checkIfNotPresent(username)) {
            Document doc = new Document("username", username)
                    .append("password", password)
                    .append("techStack", techStack)
                    .append("favProject", favProject)
                    .append("snippet", snippet);
            db.insertOne(doc);
            return true;
        }
        return false;
    }

    public List<String> fetchTechStack(String username) {
        Document filter = new Document("username", username);
        Document data = db.find(filter).first();
        if (data != null && data.containsKey("techStack")) {
            return (List<String>) data.get("techStack");
        }
        return new ArrayList<>();
    }

    public List<String> fetchFavProjects(String username) {
        Document filter = new Document("username", username);
        Document data = db.find(filter).first();
        if (data != null && data.containsKey("favProject")) {
            return (List<String>) data.get("favProject");
        }
        return new ArrayList<>();
    }

    public Boolean updateSnippet(String username, String newSnippet) {
        if (!checkIfNotPresent(username)) {
            Document filter = new Document("username", username);
            Document update = new Document("$set", new Document("snippet", newSnippet));
            db.updateOne(filter, update);
            return true;
        }
        return false;
    }

    public Boolean deleteUser(String username) {
        if (!checkIfNotPresent(username)) {
            Document filter = new Document("username", username);
            db.deleteOne(filter);
            return true;
        }
        return false;
    }

    public Document fetchUserDetails(String username) {
        Document filter = new Document("username", username);
        return db.find(filter).first();
    }
}
