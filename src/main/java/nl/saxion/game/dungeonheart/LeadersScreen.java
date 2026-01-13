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
    private int scrollIndex = 0;
    private static final int VISIBLE_COUNT = 8;
    public LeadersScreen() {
        super(1280, 720);
    }
    private final Texture background = new Texture(1280, 720, "mainScreenBackground");
    private final Button backButton = new Button(560, 150, "buttonOne", "backButton", "BACK", "jumpsWinter");
    private final Button upButton = new Button(120, 120, "buttonOne", "upButton", "Up", "jumpsWinter");
    private final Button downButton = new Button(120, 120, "buttonOne", "downButton", "DOWN", "jumpsWinter");


    private ArrayList<UserSchema> users;

    @Override
    public void show() {
        GameApp.addFont("jumpswintersmaller", "fonts/jumpsWinter.ttf", 55);
        GameApp.addFont("basic", "fonts/basic.ttf", 35);
        GameApp.addFont("grinched", "fonts/grinched.otf", 50);
        Component.register(background, backButton, upButton, downButton);

        backButton.onClick = () -> GameApp.switchScreen("MainMenuScreen");

        users = Database.Users.getAllUsers();
        users.sort((u1, u2) -> Integer.compare(u2.level, u1.level));

        upButton.onClick = () -> {
            if (scrollIndex > 0) {
                scrollIndex--;
            }
        };

        downButton.onClick = () -> {
            if (scrollIndex < users.size() - VISIBLE_COUNT) {
                scrollIndex++;
            }
        };
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        LogsManager.clearBuffer();
        GameApp.clearScreen("black");

        GameApp.startSpriteRendering();
        background.render(0, 0);
        backButton.render(40, 40);

        upButton.render(1000, 150);
        downButton.render(1000, 40);

        GameApp.drawText("grinched", "Leaderboard", 500, 650, Color.WHITE);

        int startY = 580;
        int spacing = 50;

        int endIndex = Math.min(scrollIndex + VISIBLE_COUNT, users.size());

        for (int i = scrollIndex; i < endIndex; i++) {
            UserSchema user = users.get(i);

            String text = (i + 1) + ". " + user.username + " - Level " + user.level;

            int drawIndex = i - scrollIndex;
            GameApp.drawText("jumpsWinterSmaller", text, 100, startY - (drawIndex * spacing), Color.WHITE);
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
