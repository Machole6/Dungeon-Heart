
package nl.saxion.game.dungeonheart.combat;

public class Enemy {
    private String type;
    private int health;
    private int attackPower;

    public Enemy(String type, int health, int attackPower) {
        this.type = type;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void attack(Hero hero) {
        hero.takeDamage(attackPower);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getType() { return type; }
    public int getHealth() { return health; }
}

