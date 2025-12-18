package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.componenets.Button;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.game.dungeonheart.componenets.Texture;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.game.dungeonheart.database.Schemas.UserSchema;
import nl.saxion.game.dungeonheart.managers.DataManager;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class LevelsScreen extends ScalableGameScreen  {
    public LevelsScreen() {
        super(1280, 720);
    }
    private final int BUTTON_WIDTH = 560;
    private final int BUTTON_HEIGHT = 150;
    private final int LEVEL_BUTTON_WIDTH = 150;
    private final int LEVEL_BUTTON_HEIGHT = 150;
    private final String BUTTON_FONT = "jumpsWinter";
    private final String BUTTON_TEXTURE = "buttonOne";
    private final String BUTTON_TEXTURE_ON_HOVER = "buttonTwo";
    private final String LEVEL_BUTTON_TEXTURE = "roundButtonOne";
    private final String DISABLED_LEVEL_BUTTON_TEXTURE = "roundButtonTwo";
    private final String LEVEL_BUTTON_ON_HOVER = "roundButtonThree";

    private final Texture background = new Texture(1280, 720, "mainScreenBackground");
    private final Texture heart = new Texture(300, 300, "heart");
    private final Button backButton = new Button(BUTTON_WIDTH, BUTTON_HEIGHT, "buttonOne", "levelsBackButton", "BACK", BUTTON_FONT);

    private final  ArrayList<Button> levelButtons = new ArrayList<>();

    @Override
    public void show() {
        for (int i = 0; i <= 7; i++) {
            final Button createdButton = new Button(LEVEL_BUTTON_WIDTH, LEVEL_BUTTON_HEIGHT, "roundButtonOne", "levelButton" + i, String.valueOf(i + 1), BUTTON_FONT);
            levelButtons.add(createdButton);
            Component.register(createdButton);
        }

        GameApp.addFont("grinched", "fonts/grinched.otf", 150);
        GameApp.addFont("jumpsWinter", "fonts/jumpsWinter.ttf", 90);
        GameApp.addFont("jumpsWinterSmaller", "fonts/jumpsWinter.ttf", 70);
        Component.register(background, heart);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        LogsManager.clearBuffer();
        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();

        final int userLevel = Database.Users.getCurrentUserLevel();

        for (Button button : levelButtons) {
            if (userLevel < Integer.parseInt(button.text)) {
                button.isEnabled = false;
                button.changeTexture(DISABLED_LEVEL_BUTTON_TEXTURE);
            } else {
                Component.setOnHoverFor((localButton) -> {
                    localButton.changeTexture(LEVEL_BUTTON_ON_HOVER);
                }, button);
                Component.setOnUnhoverFor((localButton) -> {
                    localButton.changeTexture(LEVEL_BUTTON_TEXTURE);
                }, button);
            }
        }

        background.render(0, 0);
        GameApp.drawText("jumpsWinterSmaller", "Your current\nlevel is: " + userLevel, 40, 540, Color.WHITE);
        GameApp.drawText("grinched", "Dungeon\n   Heart", 745, 300, Color.WHITE);
        backButton.render(40, 40);

        backButton.onClick = () -> GameApp.switchScreen("MainMenuScreen");
        Component.setOnHoverFor((button) -> {
            button.changeTexture(BUTTON_TEXTURE_ON_HOVER);
        }, backButton);
        Component.setOnUnhoverFor((button) -> {
            button.changeTexture(BUTTON_TEXTURE);
        }, backButton);

        levelButtons.getFirst().render(50, 360);
        levelButtons.get(1).render(250, 360);
        levelButtons.get(2).render(450, 360);
        levelButtons.get(3).render(50, 200);
        levelButtons.get(4).render(250, 200);
        levelButtons.get(5).render(450, 200);
        levelButtons.get(6).width = (float) (LEVEL_BUTTON_WIDTH * 1.2);
        levelButtons.get(6).height = (float) (LEVEL_BUTTON_WIDTH * 1.2);
        levelButtons.get(6).render(630, 280);

        GameApp.addScreen("Move", new combatMovement());


        levelButtons.get(1).onClick = () -> GameApp.switchScreen("Move");


        heart.render(830, 20);

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeFont("grinched");
        GameApp.disposeFont("jumpsWinter");
        GameApp.disposeFont("jumpsWinterSmaller");
        for (Button button : levelButtons) {
            Component.dispose(button);
        }
        Component.dispose(background, heart);
    }
}
