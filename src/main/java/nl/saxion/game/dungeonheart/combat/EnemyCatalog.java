package nl.saxion.game.dungeonheart.combat;

import nl.saxion.gameapp.GameApp;

import java.util.HashMap;
import java.util.Map;

public final class EnemyCatalog {

    private static final Map<String, int[]> stats = new HashMap<>();
    private static final Map<String, String> textures = new HashMap<>();

    static {
        stats.put("Goblin", new int[]{50, 10});
        stats.put("Skeleton", new int[]{70, 15});
        stats.put("Orc", new int[]{100, 18});
        stats.put("Orc Captain", new int[]{140, 24});
        stats.put("Elite Skeleton", new int[]{130, 22});
        stats.put("Boss", new int[]{250, 28});

        textures.put("Goblin", "enemyTexture");
        textures.put("Skeleton", "Skeleton");
        textures.put("Orc", "Orc");
        textures.put("Orc Captain", "OrcCaptain");
        textures.put("Elite Skeleton", "EliteSkeleton");
        textures.put("Boss", "Boss");
    }

    public static Enemy createEnemy(String type) {
        int[] s = stats.getOrDefault(type, new int[]{50, 10});
        return new Enemy(type, s[0], s[1]);
    }

    public static String getTexture(String type) {
        return textures.getOrDefault(type, "enemyTexture");
    }

    public static void loadTextures() {
        GameApp.addTexture("enemyTexture", "textures/enemyTexture.png");
        GameApp.addTexture("Skeleton", "textures/Skeleton.png");
        GameApp.addTexture("Orc", "textures/Orc.png");
        GameApp.addTexture("OrcCaptain", "textures/OrcCaptain.png");
        GameApp.addTexture("EliteSkeleton", "textures/EliteSkeleton.png");
        GameApp.addTexture("Boss", "textures/Boss.png");
    }
}
