package nl.saxion.game.dungeonheart.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import nl.saxion.game.dungeonheart.managers.LogsManager;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    static {
        Logger logger = Logger.getLogger("org.mongodb.driver");
        logger.setLevel(Level.SEVERE);
    }

    private static final MongoClient mongoClient =  MongoClients.create("mongodb+srv://andrew:bdc7pQVIo98EN2GE@gameapp.5ihenih.mongodb.net/dungeon_heart?retryWrites=true&w=majority");;
    private static final MongoDatabase database = mongoClient.getDatabase("dungeon_heart");;
    public static Users Users = new Users(database);
}
