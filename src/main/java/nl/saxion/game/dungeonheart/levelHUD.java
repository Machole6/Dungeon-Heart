package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.game.dungeonheart.componenets.Texture;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.game.dungeonheart.database.Schemas.UserSchema;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;

public class levelHUD extends ScalableGameScreen {
    private final Texture background = new Texture(1280, 720, "mainScreenBackground");
    private final Texture background2 = new Texture(1280, 80, "wood2");
    private String leveltext;
    private int level;
    public levelHUD() {
        super (1280, 720);}

    @Override
    public void show() {
        GameApp.addFont("grinched", "fonts/grinched.otf", 30);
        GameApp.addFont("jumpsWinter", "fonts/jumpsWinter.ttf", 30);
        Component.register(background, background2);

        String username = Database.Users.getCurrentUsername();

        if (username == null || username.isEmpty()) {
            GameApp.switchScreen("UsernameInputScreen");
            return;
        }



        ArrayList<UserSchema> allUsers = Database.Users.getAllUsers();
        UserSchema user = null;

        for (UserSchema u : allUsers) {
            if (u.username.equals(username)) {
                user = u;
                break;
            }
        }

        int level = (user != null) ? user.level : 0;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        LogsManager.clearBuffer();
        GameApp.clearScreen("black");

        GameApp.startSpriteRendering();
        background.render(0, 0);
        background2.render(0, 0); // wooden plank

        // Text setup
        String leftText = "It is your turn now.";
        String rightText = "Level " + level;

        // Positioning
        int leftX = 40;
        int textY = 30;

        int screenWidth = 1280;
        int rightTextWidth = (int) GameApp.getTextWidth("jumpsWinter", rightText);
        int rightX = screenWidth - rightTextWidth - 40;

        // Draw left text
        GameApp.drawText("jumpsWinter", leftText, leftX, textY, Color.WHITE);

        // Draw right text
        GameApp.drawText("jumpsWinter", rightText, rightX, textY, Color.WHITE);

        GameApp.endSpriteRendering();
    }



    @Override
    public void hide() {
        GameApp.disposeFont("grinched");
        GameApp.disposeFont("jumpsWinter");
        Component.dispose(background, background2);
    }
}
