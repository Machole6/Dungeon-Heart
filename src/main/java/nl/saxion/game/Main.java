package nl.saxion.game;

import nl.saxion.game.dungeonheart.*;
import nl.saxion.game.dungeonheart.combat.Hero;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.game.dungeonheart.database.Schemas.UserSchema;
import nl.saxion.game.dungeonheart.managers.DataManager;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;

import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GameApp.addScreen("MainMenuScreen", new MainMenuScreen());
        GameApp.addScreen("LeadersScreen", new LeadersScreen());
        GameApp.addScreen("LevelsScreen", new LevelsScreen());
        GameApp.addScreen("UsernameInputScreen", new UsernameInputScreen());
        GameApp.addScreen("LevelHUD", new levelHUD());

        List<Hero> heroes = Arrays.asList(
                new Hero("Knight", 100, 20),
                new Hero("Archer", 80, 25),
                new Hero("Mage", 70, 30),
                new Hero("Priest", 60, 15),
                new Hero("Rogue", 90, 18)
        );
        GameApp.addScreen("CombatScreen", new CombatScreen(1, heroes));

        UserSchema userData = DataManager.getCurrentUserData();
        LogsManager.runWithLogging("Refreshing the database", () -> {
            Database.Users.createIfDoesntExists(userData._id);
        });

        GameApp.start("Dungeon Heart", 1280, 720, 60, false, "MainMenuScreen");

        try {
            com.badlogic.gdx.graphics.Texture test = new com.badlogic.gdx.graphics.Texture("textures/heroTexture.png");
            System.out.println("Loaded heroTexture successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
