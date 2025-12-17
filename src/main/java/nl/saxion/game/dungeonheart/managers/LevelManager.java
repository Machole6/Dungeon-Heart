package nl.saxion.game.dungeonheart.managers;

import nl.saxion.game.dungeonheart.combat.Enemy;
import nl.saxion.game.dungeonheart.combat.EnemyCatalog;

import java.util.ArrayList;
import java.util.List;

public class LevelManager {

    public static final int MIN_LEVEL = 1;
    public static final int MAX_LEVEL = 7;

    public static boolean hasNext(int level) {
        return level < MAX_LEVEL;
    }

    public static int next(int level) {
        return Math.min(level + 1, MAX_LEVEL);
    }

    public static List<Enemy> getEnemiesForLevel(int level) {
        List<Enemy> enemies = new ArrayList<>();

        switch (level) {
            case 1 -> {
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
            }
            case 2 -> {
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
                enemies.add(EnemyCatalog.createEnemy("Skeleton"));
            }
            case 3 -> {
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
                enemies.add(EnemyCatalog.createEnemy("Skeleton"));
                enemies.add(EnemyCatalog.createEnemy("Skeleton"));
            }
            case 4 -> {
                enemies.add(EnemyCatalog.createEnemy("Orc"));
                enemies.add(EnemyCatalog.createEnemy("Skeleton"));
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
            }
            case 5 -> {
                enemies.add(EnemyCatalog.createEnemy("Orc"));
                enemies.add(EnemyCatalog.createEnemy("Orc"));
                enemies.add(EnemyCatalog.createEnemy("Skeleton"));
            }
            case 6 -> {
                enemies.add(EnemyCatalog.createEnemy("Elite Skeleton"));
                enemies.add(EnemyCatalog.createEnemy("Orc Captain"));
            }
            case 7 -> { // Boss
                enemies.add(EnemyCatalog.createEnemy("Boss"));
            }
            default -> {
                enemies.add(EnemyCatalog.createEnemy("Goblin"));
            }
        }

        return enemies;
    }

    public static String backgroundIdForLevel(int level) {
        return "Level" + level + "background";
    }
}
