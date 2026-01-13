package nl.saxion.game.dungeonheart.combat;

public abstract class Unit {

    protected String name;
    protected int maxHealth;
    protected int currentHealth;
    protected int attackPower;

    public Unit(String name, int maxHealth, int attackPower) {
        this.name = name;
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
        this.attackPower = attackPower;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    public void takeDamage(int damage) {
        currentHealth -= damage;
        if (currentHealth < 0) currentHealth = 0;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return currentHealth;
    }

    public int getAttackPower() {
        return attackPower;
    }
}
