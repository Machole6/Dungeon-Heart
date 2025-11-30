package nl.saxion.game;

import nl.saxion.game.dungeonheart.CombatScreen;
import nl.saxion.game.dungeonheart.LeadersScreen;
import nl.saxion.game.dungeonheart.LevelsScreen;
import nl.saxion.game.dungeonheart.MainMenuScreen;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.game.dungeonheart.database.Schemas.UserSchema;
import nl.saxion.game.dungeonheart.managers.DataManager;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;

public class Main {
    public static void main(String[] args) {
        GameApp.addScreen("MainMenuScreen", new MainMenuScreen());
        GameApp.addScreen("LeadersScreen", new LeadersScreen());
        GameApp.addScreen("LevelsScreen", new LevelsScreen());
        GameApp.addScreen("CombatScreen", new CombatScreen());

        UserSchema userData = DataManager.getCurrentUserData();
        LogsManager.runWithLogging("Refreshing the database", () -> {
            Database.Users.createIfDoesntExists(userData._id);
        });

        GameApp.start("Dungeon Heart", 1280, 720, 60, false, "MainMenuScreen");
    }
}