package nl.saxion.game.dungeonheart.database.Schemas;

import nl.saxion.game.dungeonheart.combat.Hero;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserSchema {
    private static class UserSchemaOptions {
        private static final String[] BASIC_CHARACTERS = { "Knight", "Ninja", "Mage" };
        private static final String[] BASIC_POTIONS = { "HarmPotion" };
    }

    public String username = "";
    public int level = 1;
    public ObjectId _id = null;
    public List<String> heroesList = new ArrayList<>(Arrays.asList(UserSchemaOptions.BASIC_CHARACTERS));
    public List<String> potionsList = new ArrayList<>(Arrays.asList(UserSchemaOptions.BASIC_POTIONS));

    public UserSchema(ObjectId objectId) {
        this._id = objectId;
    }

    public UserSchema() { }
}