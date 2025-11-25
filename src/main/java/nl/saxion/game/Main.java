package nl.saxion.game;

import nl.saxion.game.dungeonheart.MainMenuScreen;
import nl.saxion.gameapp.GameApp;

public class Main {
    public static void main(String[] args) {
        GameApp.addScreen("MainMenuScreen", new MainMenuScreen());

        GameApp.start("Dungeon Heart", 1280, 720, 60, false, "MainMenuScreen");
    }
}