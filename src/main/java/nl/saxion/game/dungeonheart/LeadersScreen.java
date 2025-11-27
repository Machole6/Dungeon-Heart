package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.componenets.Button;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.game.dungeonheart.componenets.Texture;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.game.dungeonheart.database.Schemas.UserSchema;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;

public class LeadersScreen extends ScalableGameScreen  {
    public LeadersScreen() {
        super(1280, 720);
    }
    private final Texture background = new Texture(1280, 720, "mainScreenBackground");

    @Override
    public void show() {
        Component.register(background);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        LogsManager.clearBuffer();
        GameApp.clearScreen("black");

        GameApp.startSpriteRendering();
        background.render(0, 0);

        // This is how you get all the users from the database
        ArrayList<UserSchema> users = Database.Users.getAllUsers();
        for (UserSchema user : users) {
//            System.out.printf(user.username, user.level);
        }

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        Component.dispose(background);
    }
}
