package nl.saxion.game.dungeonheart.database;
import com.mongodb.DuplicateKeyException;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.result.UpdateResult;
import nl.saxion.game.dungeonheart.database.Schemas.UserSchema;
import nl.saxion.game.dungeonheart.managers.DataManager;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import org.bson.BsonArray;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import static com.mongodb.client.model.Filters.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Users extends Collection {
    public Users(MongoDatabase database) {
        super("users", database);
        collectionSchema = UserSchema.class;
    }


    @Override
    public Document createIfDoesntExists(Object id) {
        Document document = collection.find(eq("_id", id)).first();
        if (document != null) return document;

        document = new Document();
        document.append("_id", id);

        try {
            Object schemaInstance = ((Class<?>) collectionSchema).getDeclaredConstructor().newInstance();
            for (Field field : ((Class<?>) collectionSchema).getDeclaredFields()) {
                field.setAccessible(true);
                if (field.getName().equals("_id")) continue;

                Object value = field.get(schemaInstance);
                document.append(field.getName(), value);
            }
            collection.insertOne(document);
        } catch (ReflectiveOperationException e) {
            LogsManager.addToBuffer("ERROR: Failed to create document with id " + id + ": " + e.getMessage());
            throw new RuntimeException("Failed to create document", e);
        }
        return document;
    }


    @Override
    public Document find(Object id) {
        return createIfDoesntExists(id);
    }

    @Override
    public UpdateResult updateField(String fieldName, Object fieldValue, Object id) {
        return collection.updateOne(eq("_id", id), new Document("$set", new Document(fieldName, fieldValue)));
    }

    @Override
    public void delete(String id) {
        collection.deleteOne(eq("_id", id));
    }

    public ArrayList<UserSchema> getAllUsers() {
        ArrayList<Document> documents = new ArrayList<>();
        collection.find().forEach(documents::add);

        ArrayList<UserSchema> userSchemas = new ArrayList<>();
        for (Document document : documents) {
            UserSchema user = new UserSchema(document.getObjectId("_id"));
            user.username = document.getString("username");
            user.level = document.getInteger("level");
            userSchemas.add(user);
        }
        return userSchemas;
    }

    public int getCurrentUserLevel() {
        final ObjectId userId = DataManager.getCurrentUserID();
        final Document userData = Database.Users.find(userId);
        final int userLevel = Integer.parseInt(userData.get("level").toString());
        return userLevel;
    }

    public String getCurrentUsername() {
        final ObjectId userId = DataManager.getCurrentUserID();
        final Document userData = Database.Users.find(userId);
        return userData.get("username").toString();
    }

    public List<String> getUserHeroes() {
        final ObjectId userId = DataManager.getCurrentUserID();
        final Document userData = Database.Users.find(userId);
        BsonArray BsonHeroesList = userData.toBsonDocument().getArray("heroesList");
        return BsonHeroesList
                .stream()
                .map(v -> v.asString().getValue())
                .toList();
    }

    public List<String> getUserPotions() {
        final ObjectId userId = DataManager.getCurrentUserID();
        final Document userData = Database.Users.find(userId);
        BsonArray BsonPotionsList = userData.toBsonDocument().getArray("potionsList");
        return BsonPotionsList
                .stream()
                .map(v -> v.asString().getValue())
                .toList();
    }

    public void updateCurrentUsername(String username) {
        final ObjectId userId = DataManager.getCurrentUserID();
        Database.Users.updateField("username", username, userId);
    }

    public void addHeroToUser(String heroName) {
        final ObjectId userId = DataManager.getCurrentUserID();
        final Document userData = Database.Users.find(userId);
        BsonArray heroesList = userData.toBsonDocument().getArray("heroesList");
        heroesList.add(new BsonString(heroName));
        Database.Users.updateField("heroesList", heroesList, userId);
    }

    public void addPotionToUser(String potionName) {
        final ObjectId userId = DataManager.getCurrentUserID();
        final Document userData = Database.Users.find(userId);
        BsonArray potionsList = userData.toBsonDocument().getArray("potionsList");
        potionsList.add(new BsonString(potionName));
        Database.Users.updateField("potionsList", potionsList, userId);
    }
}