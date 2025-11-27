package nl.saxion.game.dungeonheart.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;

public abstract class Collection {
    MongoCollection<Document> collection;
    protected Object collectionSchema;

    Collection(String collectionName, MongoDatabase database) {
        collection = database.getCollection(collectionName);
    }

    abstract public Document createIfDoesntExists(Object id);
    abstract public Document find(Object Id);
    abstract public UpdateResult updateField(String fieldName, Object fieldValue, Object id);
    abstract public void delete(String id);
}