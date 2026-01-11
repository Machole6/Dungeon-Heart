
package nl.saxion.game.dungeonheart.combat;

import java.util.HashMap;
import java.util.Map;

public final class HeroCatalog {

    public static final String NINJA  = "Ninja";
    public static final String MAGE  = "Mage";
    public static final String KNIGHT  = "Knight";
    public static final String BRAWLER  = "Brawler";
    public static final String SAMURAI  = "Samurai";

    private static final Map<String, int[]> stats = new HashMap<>();
    static {
        stats.put(KNIGHT, new int[]{100, 20});   // health, attack
        stats.put(MAGE,   new int[]{70, 30});
        stats.put(NINJA,  new int[]{100, 20});
    }

    private static final Map<String, String> textures = new HashMap<>();
    static {
        textures.put(KNIGHT, "Knight");
        textures.put(MAGE,   "Mage");
        textures.put(NINJA,  "Ninja");
    }

    public static Hero createHero(String heroId) {
        int[] s = stats.getOrDefault(heroId, new int[]{100, 20});
        return new Hero(heroId, s[0], s[1]);
    }

    public static String getTexture(String heroId) {
        return textures.getOrDefault(heroId, "heroTexture");
    }

    public static void loadTextures() {
        nl.saxion.gameapp.GameApp.addTexture("heroTexture", "textures/heroTexture.png");
        nl.saxion.gameapp.GameApp.addTexture("enemyTexture",           "textures/enemyTexture.png");
        nl.saxion.gameapp.GameApp.addTexture("Level1background",       "textures/Level1background.png");
        nl.saxion.gameapp.GameApp.addTexture("Skeleton",               "textures/Skeleton.png");
        nl.saxion.gameapp.GameApp.addTexture("heart",                  "textures/heart.png");
        nl.saxion.gameapp.GameApp.addTexture("Ninja",                  "textures/Ninja/Ninja attack-4.png");
        nl.saxion.gameapp.GameApp.addTexture("Knight",                  "textures/Knight/Knight attack-0.png");
        nl.saxion.gameapp.GameApp.addTexture("Mage",                  "textures/Mage/Mage attack-2.png");
    }
}

