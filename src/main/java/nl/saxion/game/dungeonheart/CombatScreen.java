package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.combat.*;
import nl.saxion.game.dungeonheart.componenets.*;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;
import nl.saxion.game.dungeonheart.managers.LevelManager;

import java.util.ArrayList;
import java.util.List;

public class CombatScreen extends ScalableGameScreen {

    private final List<HeroComponent> heroComponents = new ArrayList<>();
    private final List<EnemyComponent> enemyComponents = new ArrayList<>();
    private CombatSystem combatSystem;

    private final int levelNumber;
    private final Texture background;

    private final int UI_BUTTON_WIDTH = 360;
    private final int UI_BUTTON_HEIGHT = 120;
    private final String BUTTON_FONT = "jumpsWinterEvenSmaller";
    private final String BUTTON_TEXTURE = "buttonOne";
    private final String BUTTON_TEXTURE_HOVER = "buttonTwo";

    private final Button backButton = new Button(UI_BUTTON_WIDTH, UI_BUTTON_HEIGHT, BUTTON_TEXTURE, "combatBackButton", "BACK TO LEVELS", BUTTON_FONT);
    private final Button menuButton = new Button(UI_BUTTON_WIDTH, UI_BUTTON_HEIGHT, BUTTON_TEXTURE, "combatMenuButton", "MAIN MENU", BUTTON_FONT);
    private final Button nextLevelButton = new Button(UI_BUTTON_WIDTH, UI_BUTTON_HEIGHT, BUTTON_TEXTURE, "combatNextLevelButton", "Next level", BUTTON_FONT);

    public CombatScreen(int levelNumber, List<Hero> selectedHeroes) {
        super(1280, 720);

        this.levelNumber = levelNumber;
        this.background = new Texture(1280, 720, "Level" + levelNumber + "background");

        int y = 100;
        for (Hero hero : selectedHeroes) {
            String textureId = HeroCatalog.getTexture(hero.getName());
            HeroComponent hc = new HeroComponent(hero, 100, 100, textureId, hero.getName());
            heroComponents.add(hc);
            HeroComponent.register(hc);
            y += 120;
        }
    }

    @Override
    public void show() {
        GameApp.addTexture("Level" + levelNumber + "background", "textures/Level1background.png");
        HeroCatalog.loadTextures();
//        EnemyCatalog.loadTextures();

        Component.register(background);

        List<Enemy> enemies = LevelManager.getEnemiesForLevel(levelNumber);

        int index = 1;
        for (Enemy enemy : enemies) {
            String textureId = EnemyCatalog.getTexture(enemy.getName());

            EnemyComponent ec = new EnemyComponent(
                    enemy,
                    100,
                    100,
                    textureId,
                    enemy.getName().toLowerCase() + index
            );

            enemyComponents.add(ec);
            EnemyComponent.register(ec);
            index++;
        }


        combatSystem = new CombatSystem(heroComponents, enemyComponents);

        for (HeroComponent hc : heroComponents) {
            hc.onClick = () -> {
                if (combatSystem.getCurrentState() != CombatSystem.State.END_COMBAT) {
                    combatSystem.selectHero(hc);
                }
            };
        }

        for (EnemyComponent ec : enemyComponents) {
            ec.onClick = () -> {
                if (combatSystem.getCurrentState() != CombatSystem.State.END_COMBAT) {
                    combatSystem.selectEnemy(ec);
                }
            };
        }

        Component.register(backButton, menuButton, nextLevelButton);
        Component.setOnHoverFor((b) -> b.changeTexture(BUTTON_TEXTURE_HOVER), backButton, menuButton, nextLevelButton);
        Component.setOnUnhoverFor((b) -> b.changeTexture(BUTTON_TEXTURE), backButton, menuButton, nextLevelButton);

        backButton.onClick = () -> {
            if (combatSystem.getCurrentState() == CombatSystem.State.END_COMBAT) {
                GameApp.switchScreen("LevelsScreen");
            }
        };

        menuButton.onClick = () -> {
            if (combatSystem.getCurrentState() == CombatSystem.State.END_COMBAT) {
                GameApp.switchScreen("MainMenuScreen");
            }
        };

        nextLevelButton.onClick = () -> {
            if (combatSystem.getCurrentState() != CombatSystem.State.END_COMBAT) return;

            boolean heroesAlive = !combatSystem.getHeroComponents().isEmpty();
            boolean enemiesAlive = !combatSystem.getEnemyComponents().isEmpty();
            boolean playerWon = heroesAlive && !enemiesAlive;

            if (!playerWon) return;

            if (!LevelManager.hasNext(levelNumber)) {
                GameApp.switchScreen("MainMenuScreen");
                return;
            }

            int next = LevelManager.next(levelNumber);
            List<Hero> nextParty = new ArrayList<>();
            for (HeroComponent hc : combatSystem.getHeroComponents()) {
                nextParty.add(hc.getHero());
            }

            GameApp.addScreen("CombatScreen", new CombatScreen(next, nextParty));
            GameApp.switchScreen("CombatScreen");
        };
    }

    @Override
    public void render(float delta) {

        if (combatSystem == null) {
            return;
        }

        super.render(delta);
        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();

        background.render(0, 0);

        int heroBaseX = 100;
        int heroBaseY = 100;
        int rowSpacing = 120;
        int heroXStagger = 200;
        int heroYStagger = 30;

        for (int i = 0; i < heroComponents.size(); i++) {
            HeroComponent hc = heroComponents.get(i);
            int x = heroBaseX + ((i % 2 == 0) ? 0 : heroXStagger);
            int y = heroBaseY + i * rowSpacing + ((i % 2 == 0) ? 0 : heroYStagger);
            hc.render(x, y);
        }

        int enemyBaseX = 1280 - 200;
        int enemyBaseY = 100;
        int enemyXStagger = 200;
        int enemyYStagger = 30;

        for (int i = 0; i < enemyComponents.size(); i++) {
            EnemyComponent ec = enemyComponents.get(i);
            int x = enemyBaseX - ((i % 2 == 0) ? 0 : enemyXStagger);
            int y = enemyBaseY + i * rowSpacing + ((i % 2 == 0) ? 0 : enemyYStagger);
            ec.render(x, y);
        }

        GameApp.drawText("jumpsWinter", "LEVEL " + levelNumber, 900, 640, Color.WHITE);

        if (combatSystem.getCurrentState() == CombatSystem.State.END_COMBAT) {
            boolean heroesAlive = !combatSystem.getHeroComponents().isEmpty();
            boolean enemiesAlive = !combatSystem.getEnemyComponents().isEmpty();
            boolean playerWon = heroesAlive && !enemiesAlive;

            String title = playerWon ? "VICTORY!" : "DEFEAT...";
            Color titleColor = playerWon ? Color.LIME : Color.SALMON;
            GameApp.drawText("grinched", title, 420, 480, titleColor);

            int centerX = 1280 / 2;
            int btnY1 = 180;
            int btnY2 = 300;
            int btnY3 = 60;

            backButton.render(centerX - (UI_BUTTON_WIDTH / 2), btnY1);
            menuButton.render(centerX - (UI_BUTTON_WIDTH / 2), btnY2);

            if (playerWon && LevelManager.hasNext(levelNumber)) {
                nextLevelButton.render(centerX - (UI_BUTTON_WIDTH / 2), btnY3);
            }
        }

        GameApp.endSpriteRendering();

        if (combatSystem.getCurrentState() != CombatSystem.State.END_COMBAT &&
                GameApp.isButtonJustPressed(Input.Buttons.LEFT)) {

            int mouseX = GameApp.getMousePositionInWindowX();
            int mouseY = GameApp.getWindowHeight() - GameApp.getMousePositionInWindowY();

            for (HeroComponent hc : new ArrayList<>(heroComponents)) {
                hc.handleClick(mouseX, mouseY);
            }
            for (EnemyComponent ec : new ArrayList<>(enemyComponents)) {
                ec.handleClick(mouseX, mouseY);
            }
        }
    }

    @Override
    public void hide() {
        HeroComponent.dispose(heroComponents.toArray(new HeroComponent[0]));
        EnemyComponent.dispose(enemyComponents.toArray(new EnemyComponent[0]));
        Component.dispose(background);
        Component.dispose(backButton, menuButton, nextLevelButton);
    }
}
