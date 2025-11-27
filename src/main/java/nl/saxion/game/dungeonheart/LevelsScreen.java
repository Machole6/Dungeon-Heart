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

public class LevelsScreen extends ScalableGameScreen  {
    public LevelsScreen() {
        super(1280, 720);
    }
    private final int BUTTON_WIDTH = 560;
    private final int BUTTON_HEIGHT = 150;
    private final String BUTTON_FONT = "jumpsWinter";
    private final String BUTTON_TEXTURE = "buttonOne";
    private final String BUTTON_TEXTURE_ON_HOVER = "buttonTwo";

    private final Texture background = new Texture(1280, 720, "mainScreenBackground");
    private final Texture heart = new Texture(300, 300, "heart");
    private final Button backButton = new Button(BUTTON_WIDTH, BUTTON_HEIGHT, "buttonOne", "levelsBackButton", "BACK", BUTTON_FONT);
//    private final Button roundButtonUnlocked = new Button(100, 100, "roundButtonOne");
//    private final Texture roundButtonLocked = new Texture(100, 100, "roundButtonTwo");

    @Override
    public void show() {
        GameApp.addFont("grinched", "fonts/grinched.otf", 150);
        GameApp.addFont("jumpsWinter", "fonts/jumpsWinter.ttf", 90);
        Component.register(background, heart);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        LogsManager.clearBuffer();
        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();

        background.render(0, 0);
        GameApp.drawText("jumpsWinterSmaller", "Your current\nlevel is: 1", 40, 540, Color.WHITE);
        GameApp.drawText("grinched", "Dungeon\n   Heart", 745, 300, Color.WHITE);
        backButton.render(40, 40);

        backButton.onClick = () -> GameApp.switchScreen("MainMenuScreen");
        Component.setOnHoverFor((button) -> {
            button.changeTexture(BUTTON_TEXTURE_ON_HOVER);
        }, backButton);
        Component.setOnUnhoverFor((button) -> {
            button.changeTexture(BUTTON_TEXTURE);
        }, backButton);

        heart.render(830, 20);

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeFont("grinched");
        Component.dispose(background, heart);
    }
}
