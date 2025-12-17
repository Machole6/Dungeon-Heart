
package nl.saxion.game.dungeonheart.combat;

import java.util.HashMap;
import java.util.Map;

public final class HeroCatalog {

    public static final String KNIGHT = "Knight";
    public static final String ARCHER = "Archer";
    public static final String MAGE   = "Mage";
    public static final String PRIEST = "Priest";
    public static final String ROGUE  = "Rogue";

    private static final Map<String, int[]> stats = new HashMap<>();
    static {
        stats.put(KNIGHT, new int[]{100, 20});   // health, attack
        stats.put(ARCHER, new int[]{809, 25});
        stats.put(MAGE,   new int[]{70, 30});
        stats.put(PRIEST, new int[]{60, 15});
        stats.put(ROGUE,  new int[]{90, 18});
    }

    private static final Map<String, String> textures = new HashMap<>();
    static {
        textures.put(KNIGHT, "knight");
        textures.put(ARCHER, "Archer");
        textures.put(MAGE,   "heroTexture");
        textures.put(PRIEST, "heroTexture");
        textures.put(ROGUE,  "heroTexture");
    }

    public static Hero createHero(String heroId) {
        int[] s = stats.getOrDefault(heroId, new int[]{100, 20});
        return new Hero(heroId, s[0], s[1]);
    }

    public static String getTexture(String heroId) {
        return textures.getOrDefault(heroId, "heroTexture");
    }

    public static void loadTextures() {
        nl.saxion.gameapp.GameApp.addTexture("knight",      "textures/knight.png");
        nl.saxion.gameapp.GameApp.addTexture("Archer",      "textures/Archer.png");
        nl.saxion.gameapp.GameApp.addTexture("heroTexture", "textures/heroTexture.png");
        nl.saxion.gameapp.GameApp.addTexture("enemyTexture",           "textures/enemyTexture.png");
        nl.saxion.gameapp.GameApp.addTexture("Level1background",       "textures/Level1background.png");
        nl.saxion.gameapp.GameApp.addTexture("Skeleton",               "textures/Skeleton.png");
        nl.saxion.gameapp.GameApp.addTexture("heart",                  "textures/heart.png");
    }
}

