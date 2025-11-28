package nl.saxion.game.dungeonheart.combat;

import nl.saxion.game.dungeonheart.componenets.HeroComponent;
import nl.saxion.game.dungeonheart.componenets.EnemyComponent;

import java.util.List;
import java.util.Random;

public class CombatSystem {

    public enum State {
        SELECT_HERO,
        SELECT_ENEMY,
        ENEMY_TURN,
        CHECK_WIN
    }

    private State currentState = State.SELECT_HERO;
    private HeroComponent selectedHero = null;

    private final List<HeroComponent> heroComponents;
    private final List<EnemyComponent> enemyComponents;
    private final Random random = new Random();

    public CombatSystem(List<HeroComponent> heroes, List<EnemyComponent> enemies) {
        this.heroComponents = heroes;
        this.enemyComponents = enemies;

        for (HeroComponent hc : heroComponents) {
            hc.onClick = () -> {
                if (currentState == State.SELECT_HERO) {
                    selectedHero = hc;
                    currentState = State.SELECT_ENEMY;
                }
            };
        }

        // Assign click behavior for enemies
        for (EnemyComponent ec : enemyComponents) {
            ec.onClick = () -> {
                if (currentState == State.SELECT_ENEMY && selectedHero != null) {
                    // Hero attacks enemy
                    selectedHero.getHero().attack(ec.getEnemy());
                    selectedHero = null;

                    // Remove dead enemy if applicable
                    if (!ec.getEnemy().isAlive()) {
                        enemyComponents.remove(ec);
                    }

                    // Next state: enemy turn or next hero
                    currentState = State.ENEMY_TURN;
                    enemyTurn();
                }
            };
        }
    }

    private void enemyTurn() {
        // Each alive enemy attacks a random alive hero
        for (EnemyComponent ec : enemyComponents) {
            if (!heroComponents.isEmpty()) {
                HeroComponent target = heroComponents.get(random.nextInt(heroComponents.size()));
                ec.getEnemy().attack(target.getHero());

                // Remove dead hero if applicable
                if (!target.getHero().isAlive()) {
                    heroComponents.remove(target);
                }
            }
        }

        // After enemy turn, check for win/loss
        checkWin();
    }

    private void checkWin() {
        if (heroComponents.isEmpty()) {
            currentState = State.CHECK_WIN;
            System.out.println("Enemies win!");
        } else if (enemyComponents.isEmpty()) {
            currentState = State.CHECK_WIN;
            System.out.println("Heroes win!");
        } else {
            currentState = State.SELECT_HERO;
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    public List<HeroComponent> getHeroComponents() {
        return heroComponents;
    }

    public List<EnemyComponent> getEnemyComponents() {
        return enemyComponents;
    }
}
