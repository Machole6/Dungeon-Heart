package nl.saxion.game.dungeonheart.combat;

public class Enemy extends Unit {

    public Enemy(String type, int health, int attackPower) {
        super(type, health, attackPower);
    }

    public void attack(Hero hero) {
        hero.takeDamage(attackPower);
    }
}
