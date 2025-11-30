package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.Input;
import nl.saxion.game.dungeonheart.combat.CombatSystem;
import nl.saxion.game.dungeonheart.combat.Hero;
import nl.saxion.game.dungeonheart.combat.Enemy;
import nl.saxion.game.dungeonheart.componenets.HeroComponent;
import nl.saxion.game.dungeonheart.componenets.EnemyComponent;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.util.ArrayList;
import java.util.List;

public class CombatScreen extends ScalableGameScreen {

    private final List<HeroComponent> heroComponents = new ArrayList<>();
    private final List<EnemyComponent> enemyComponents = new ArrayList<>();
    private CombatSystem combatSystem;

    public CombatScreen() {
        super(1280, 720);
    }

    @Override
    public void show() {

        Hero hero = new Hero("Warrior", 100, 20);
        HeroComponent hc = new HeroComponent(hero, 100, 100, "heroTexture", "hero1");
        heroComponents.add(hc);

        Enemy enemy = new Enemy("Goblin", 50, 10);
        EnemyComponent ec = new EnemyComponent(enemy, 100, 100, "enemyTexture", "enemy1");
        enemyComponents.add(ec);

        Enemy enemy1 = new Enemy("Goblin", 50, 10);
        EnemyComponent ec1 = new EnemyComponent(enemy1, 100, 100, "enemyTexture", "enemy2");
        enemyComponents.add(ec1);

        HeroComponent.register(hc);
        EnemyComponent.register(ec);
        EnemyComponent.register(ec1);

        combatSystem = new CombatSystem(heroComponents, enemyComponents);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen("black");
        GameApp.startSpriteRendering();

        int x = 100;
        int y = 300;
        for (HeroComponent hc : heroComponents) {
            hc.render(x, y);
            x += 150;
        }

        x = 1280 - 200 - 100;
        y = 300;
        for (EnemyComponent ec : enemyComponents) {
            ec.render(x, y);
            x -= 150;
        }

        GameApp.endSpriteRendering();

        if (GameApp.isButtonJustPressed(Input.Buttons.LEFT)) {
            int mouseX = GameApp.getMousePositionInWindowX();
            int mouseY = GameApp.getWindowHeight() - GameApp.getMousePositionInWindowY();

            for (HeroComponent hc : heroComponents) {
                hc.handleClick(mouseX, mouseY);
            }

            for (EnemyComponent ec : enemyComponents) {
                ec.handleClick(mouseX, mouseY);
            }

        }
    }

    @Override
    public void hide() {
//        HeroComponent.dispose(heroComponents.toArray(new HeroComponent[0]));
//        EnemyComponent.dispose(enemyComponents.toArray(new EnemyComponent[0]));
    }
}
