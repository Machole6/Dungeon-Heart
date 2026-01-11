package nl.saxion.game.dungeonheart.managers;

import com.google.gson.Gson;
import nl.saxion.game.dungeonheart.database.Schemas.UserSchema;
import nl.saxion.gameapp.GameApp;
import org.bson.types.ObjectId;

import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataManager {
    public static UserSchema getCurrentUserData() {
        String JSON_PATH = "src/main/java/nl/saxion/game/dungeonheart/json/user_data.json";
        Gson gson = new Gson();
        if (!Files.exists(Paths.get(JSON_PATH))) {
            final UserSchema newUser = new UserSchema(new ObjectId());
            try (FileWriter writer = new FileWriter(JSON_PATH)) {
                gson.toJson(newUser, writer);
            } catch (Exception e) {
                LogsManager.addToBuffer("ERROR: " + e.getMessage());
            }
            return newUser;
        } else {
            try (FileReader reader = new FileReader(JSON_PATH)) {
                return gson.fromJson(reader, UserSchema.class);
            } catch (Exception e) {
                LogsManager.addToBuffer("ERROR: " + e.getMessage());
            }
        }
        return null;
    }

    public static ObjectId getCurrentUserID() {
        return getCurrentUserData()._id;
    }
}
