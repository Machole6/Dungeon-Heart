package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.Game;
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
    private final Button backButton = new Button(560, 150, "buttonOne", "backButton", "BACK", "jumpsWinter");
    private ArrayList<UserSchema> users;

    @Override
    public void show() {
        GameApp.addFont("jumpswintersmaller", "fonts/jumpsWinter.ttf", 55);
        GameApp.addFont("basic", "fonts/basic.ttf", 50);
        GameApp.addFont("grinched", "fonts/grinched.otf", 50);
        Component.register(background, backButton);

        backButton.onClick = () -> GameApp.switchScreen("MainMenuScreen");

        users = Database.Users.getAllUsers();
        users.sort((u1, u2) -> Integer.compare(u2.level, u1.level));
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        LogsManager.clearBuffer();
        GameApp.clearScreen("black");

        GameApp.startSpriteRendering();
        background.render(0, 0);
        backButton.render(40, 40);

        GameApp.drawText("grinched", "Leaderboard", 500, 650, Color.WHITE);

        int startY = 580;
        int spacing = 50;

        for (int i = 0; i < users.size(); i++) {
            UserSchema user = users.get(i);
            String text = (i + 1) + ". " + user.username + " - Level " + user.level;
            GameApp.drawText("jumpsWinterSmaller", text, 100, startY - (i * spacing), Color.WHITE);
        }

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeFont("jumpsWinterSmaller");
        GameApp.disposeFont("basic");
        GameApp.disposeFont("grinched");
        Component.dispose(background, backButton);
    }
}
