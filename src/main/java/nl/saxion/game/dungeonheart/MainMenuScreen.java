package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.componenets.Button;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.game.dungeonheart.componenets.Texture;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.lang.reflect.Array;

public class MainMenuScreen extends ScalableGameScreen {
    public MainMenuScreen() {
        super(1280, 720);
    }
    private final int BUTTON_WIDTH = 560;
    private final int BUTTON_HEIGHT = 150;
    private final String BUTTON_FONT = "jumpsWinter";
    private final String BUTTON_TEXTURE = "buttonOne";
    private final String BUTTON_TEXTURE_ON_HOVER = "buttonTwo";

    private final Button exitButton = new Button(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_TEXTURE, "exitButton", "EXIT", BUTTON_FONT);
    private final Button leadersBoardButton = new Button(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_TEXTURE, "leadersBoarButton", "Leaders", BUTTON_FONT);
    private final Button startButton = new Button(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_TEXTURE, "startButton", "START", BUTTON_FONT);
    private final Texture background = new Texture(1280, 720, "mainScreenBackground");
    private final Texture heart = new Texture(300, 300, "heart");

    @Override
    public void show() {
        GameApp.addFont("basic", "fonts/basic.ttf", 100);
        GameApp.addFont("jumpsWinter", "fonts/jumpsWinter.ttf", 90);
        GameApp.addFont("jumpsWinterSmaller", "fonts/jumpsWinter.ttf", 70);
        GameApp.addFont("grinched", "fonts/grinched.otf", 150);
        Component.register(exitButton, leadersBoardButton, startButton, background, heart);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        LogsManager.clearBuffer();

        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();

        background.render(0, 0);
        exitButton.render(40, 40);
        leadersBoardButton.render(40, 200);
        startButton.render(40, 360);
        GameApp.drawText("jumpsWinterSmaller", "Your current\nlevel is: 1", 40, 540, Color.WHITE);
        GameApp.drawText("grinched", "Dungeon\n   Heart", 745, 300, Color.WHITE);
        heart.render(830, 20);

        exitButton.onClick = GameApp::quit;

        exitButton.onHover = () -> exitButton.changeTexture(BUTTON_TEXTURE_ON_HOVER);
        exitButton.onUhnover = () -> exitButton.changeTexture(BUTTON_TEXTURE);
        startButton.onHover = () -> startButton.changeTexture(BUTTON_TEXTURE_ON_HOVER);
        startButton.onUhnover = () -> startButton.changeTexture(BUTTON_TEXTURE);
        leadersBoardButton.onHover = () -> leadersBoardButton.changeTexture(BUTTON_TEXTURE_ON_HOVER);
        leadersBoardButton.onUhnover = () -> leadersBoardButton.changeTexture(BUTTON_TEXTURE);
        // make a function that sets on hover effect for every button on screen

        GameApp.endSpriteRendering();
    }

    @Override
    public void hide() {
        GameApp.disposeFont("basic");
        Component.dispose(exitButton, leadersBoardButton, startButton, background, heart);
    }
}
