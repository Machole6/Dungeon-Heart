package nl.saxion.game.dungeonheart.database.Schemas;

import org.bson.types.ObjectId;

public class UserSchema {
    public String username = "";
    public int level = 0;
    public ObjectId _id = null;

    public UserSchema(ObjectId objectId) {
        this._id = objectId;
    }

    public UserSchema() {}
}