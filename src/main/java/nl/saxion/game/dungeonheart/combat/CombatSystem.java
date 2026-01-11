package nl.saxion.game.dungeonheart.combat;

import nl.saxion.game.dungeonheart.componenets.EnemyComponent;
import nl.saxion.game.dungeonheart.componenets.HeroComponent;

import java.util.List;
import java.util.Random;

public class CombatSystem {

    public enum State {
        SELECT_HERO,
        SELECT_ENEMY,
        ENEMY_TURN,
        END_COMBAT
    }

    private State currentState = State.SELECT_HERO;

    private HeroComponent selectedHero;
    private Skill selectedSkill; // NEW: selected skill of the hero

    private final List<HeroComponent> heroComponents;
    private final List<EnemyComponent> enemyComponents;

    private final Random random = new Random();

    public CombatSystem(List<HeroComponent> heroes, List<EnemyComponent> enemies) {
        this.heroComponents = heroes;
        this.enemyComponents = enemies;
    }

    public void selectHero(HeroComponent hero) {
        if (currentState != State.SELECT_HERO) return;
        if (!hero.getHero().isAlive()) return;

        selectedHero = hero;
        selectedSkill = hero.getHero().getSkills().get(0);
        currentState = State.SELECT_ENEMY;
    }

    public void selectEnemy(EnemyComponent enemy) {
        if (currentState != State.SELECT_ENEMY) return;
        if (selectedHero == null || selectedSkill == null) return;
        if (!enemy.getEnemy().isAlive()) return;

        selectedSkill.use(selectedHero.getHero(), enemy.getEnemy());

        selectedHero = null;
        selectedSkill = null;

        removeDeadUnits();
        checkEndConditions();

        if (currentState != State.END_COMBAT) {
            currentState = State.ENEMY_TURN;
            enemyTurn();
        }
    }

    // Enemy turn logic
    public void enemyTurn() {
        for (EnemyComponent enemyComponent : enemyComponents) {
            if (!enemyComponent.getEnemy().isAlive()) continue;
            if (heroComponents.isEmpty()) break;

            HeroComponent target =
                    heroComponents.get(random.nextInt(heroComponents.size()));

            enemyComponent.getEnemy().attack(target.getHero());
            removeDeadUnits();
        }

        checkEndConditions();

        if (currentState != State.END_COMBAT) {
            currentState = State.SELECT_HERO;
        }
    }

    // Remove dead units from the board
    public void removeDeadUnits() {
        enemyComponents.removeIf(ec -> !ec.getEnemy().isAlive());
        heroComponents.removeIf(hc -> !hc.getHero().isAlive());
    }

    // Check for win/loss
    public void checkEndConditions() {
        if (heroComponents.isEmpty()) {
            currentState = State.END_COMBAT;
            System.out.println("Enemies win!");
        } else if (enemyComponents.isEmpty()) {
            currentState = State.END_COMBAT;
            System.out.println("Heroes win!");
        }
    }

    public void selectSkill(Skill skill) {
        if (selectedHero != null) {
            selectedSkill = skill;
        }
    }

    // Getters
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
