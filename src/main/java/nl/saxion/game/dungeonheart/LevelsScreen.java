
package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.combat.Hero;
import nl.saxion.game.dungeonheart.combat.HeroCatalog;
import nl.saxion.game.dungeonheart.componenets.Button;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.game.dungeonheart.componenets.Texture;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;
import java.util.List;

public class LevelsScreen extends ScalableGameScreen {
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
    private final Button backButton = new Button(BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_TEXTURE, "levelsBackButton", "BACK", BUTTON_FONT);

    private final ArrayList<Button> levelButtons = new ArrayList<>();

    @Override
    public void show() {
        for (int i = 0; i < 7; i++) {
            final Button createdButton = new Button(LEVEL_BUTTON_WIDTH, LEVEL_BUTTON_HEIGHT, LEVEL_BUTTON_TEXTURE,
                    "levelButton" + (i + 1), String.valueOf(i + 1), BUTTON_FONT);
            levelButtons.add(createdButton);
            Component.register(createdButton);
        }
        GameApp.addFont("grinched", "fonts/grinched.otf", 150);
        GameApp.addFont("jumpsWinter", "fonts/jumpsWinter.ttf", 90);
        GameApp.addFont("jumpsWinterSmaller", "fonts/jumpsWinter.ttf", 70);
        GameApp.addFont("jumpsWinterEvenSmaller", "fonts/jumpsWinter.ttf", 30);

        Component.register(background, heart, backButton);

        backButton.onClick = () -> GameApp.switchScreen("MainMenuScreen");

        final int userLevel = Database.Users.getCurrentUserLevel();

        for (int i = 0; i < levelButtons.size(); i++) {
            Button btn = levelButtons.get(i);
            int levelNumber = i + 1;

            if (userLevel < levelNumber) {
                btn.isEnabled = false;
                btn.changeTexture(DISABLED_LEVEL_BUTTON_TEXTURE);
                btn.onClick = null;
            } else {
                btn.isEnabled = true;
                btn.changeTexture(LEVEL_BUTTON_TEXTURE);

                btn.onClick = () -> {
                    List<Hero> party = defaultParty();
                    GameApp.addScreen("CombatScreen", new CombatScreen(levelNumber, party));
                    GameApp.switchScreen("CombatScreen");
                };

                Component.setOnHoverFor((b) -> b.changeTexture(LEVEL_BUTTON_ON_HOVER), btn);
                Component.setOnUnhoverFor((b) -> b.changeTexture(LEVEL_BUTTON_TEXTURE), btn);
            }
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        LogsManager.clearBuffer();
        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();

        background.render(0, 0);

        final int userLevel = Database.Users.getCurrentUserLevel();
        GameApp.drawText("jumpsWinterSmaller", "Your current\nlevel is: " + userLevel, 40, 540, Color.WHITE);
        GameApp.drawText("grinched", "Dungeon\n   Heart", 745, 300, Color.WHITE);

        backButton.render(40, 40);

        levelButtons.get(0).render(50, 360);
        levelButtons.get(1).render(250, 360);
        levelButtons.get(2).render(450, 360);
        levelButtons.get(3).render(50, 200);
        levelButtons.get(4).render(250, 200);
        levelButtons.get(5).render(450, 200);

        Button bossBtn = levelButtons.get(6);
        bossBtn.width = (float) (LEVEL_BUTTON_WIDTH * 1.2);
        bossBtn.height = (float) (LEVEL_BUTTON_HEIGHT * 1.2);
        bossBtn.render(630, 280);

        heart.render(830, 20);

        GameApp.endSpriteRendering();
    }
    @Override
    public void hide() {
        GameApp.disposeFont("grinched");
        GameApp.disposeFont("jumpsWinter");
        GameApp.disposeFont("jumpsWinterSmaller");
        GameApp.disposeFont("jumpsWinterEvenSmaller");
        for (Button button : levelButtons) {
            Component.dispose(button);
        }
        Component.dispose(background, heart, backButton); // ✅ dispose backButton too
    }
    private List<Hero> defaultParty() {
        List<Hero> party = new ArrayList<>();
        party.add(HeroCatalog.createHero(HeroCatalog.KNIGHT));
        party.add(HeroCatalog.createHero(HeroCatalog.ARCHER));
        party.add(HeroCatalog.createHero(HeroCatalog.MAGE));
        party.add(HeroCatalog.createHero(HeroCatalog.PRIEST));
        party.add(HeroCatalog.createHero(HeroCatalog.ROGUE));
        return party;
    }
}
