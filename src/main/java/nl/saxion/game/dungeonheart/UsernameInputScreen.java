package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
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

import java.awt.*;

public class UsernameInputScreen extends ScalableGameScreen {
    private final Texture background = new Texture(1280, 720, "mainScreenBackground");
    private final TextInput usernameInput = new TextInput(100, 300, 100, 80, "Type username...");
    private final Button submitButton = new Button(400, 200, "buttonOne", "submitButton", "SUBMIT", "jumpsWinter");

    public UsernameInputScreen() {
        super(1280, 720);
    }
    private String currentText = "";

    @Override
    public void show() {
        String currentUsername = Database.Users.getCurrentUsername();
        if (currentUsername != null && !currentUsername.isEmpty()) {
            GameApp.switchScreen("MainMenuScreen");
            return;
        }
        Component.register(background, submitButton);

        submitButton.onClick = () -> {
            currentText = usernameInput.get();
            handleUsernameSubmission();
        };
    }

    @Override
    public boolean keyTyped(char character) {
        if(character == '\b') { // Backspace
            if(currentText.length() > 0) {
                // Remove the last character
                currentText = currentText.substring(0, currentText.length() - 1);
                usernameInput.text = currentText;
            }
        } else if(character == '\r' || character == '\n') { // Enter characters
            currentText = usernameInput.get();
        } else {
            // Add the typed character
            currentText += character;
            usernameInput.text = currentText;
        }
        return true;
    }

    private void handleUsernameSubmission() {
        String name = currentText.trim();
        if (name.length() >= 3) {
            Database.Users.updateCurrentUsername(name);
            GameApp.switchScreen("LevelHUD");
        } else {
            System.out.println("Username too short.");
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();
        background.render(0, 0);
        GameApp.drawText("basic", "Choose your username", 100, 500, Color.WHITE);
        submitButton.render(0,0);
        usernameInput.render_text();
        GameApp.endSpriteRendering();



        if (GameApp.isKeyJustPressed(Input.Keys.ENTER)) {
            Database.Users.updateCurrentUsername(currentText);
            GameApp.switchScreen("LevelHUD");
        }
    }

//    @Override
//    public boolean keyTyped(char character) {
//        if(character == '\b') { // Backspace
//            if(currentText.length() > 0) {
//                // Remove the last character
//                currentText = currentText.substring(0, currentText.length() - 1);
//                usernameInput.text = currentText;
//            }
//        } else if(character == '\r' || character == '\n') { // Enter characters
//            currentText = usernameInput.get();
//        } else {
//            // Add the typed character
//            currentText += character;
//            usernameInput.text = currentText;
//        }
//        return true;
//    }
//    private void handleUsernameSubmission() {
//        String name = currentText.trim();
//        if (name.length() >= 3) {
//            Database.Users.updateCurrentUsername(name);
//            GameApp.switchScreen("MainMenuScreen");
//        } else {
//            System.out.println("Username too short.");
//        }
//    }


    @Override
    public void hide() {
        GameApp.disposeFont("Basic");
        Component.dispose(background, submitButton);
    }

}