package nl.saxion.game.dungeonheart.combat;

public class Hero {
    private String name;
    private int health;
    private  int attackPower;

    public Hero(String name,int health,int attackPower){
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public void attack(Enemy enemy) {
        enemy.takeDamage(attackPower);
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public String getName() { return name; }
    public int getHealth() { return health; }
}

