package nl.saxion.game.dungeonheart.componenets;

import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;

public abstract class Component {
    protected int height;
    protected int width;
    protected String textureId;
    protected String id;

    protected Component(int width, int height, String textureId, String id) {
        this.height = height;
        this.width = width;
        this.textureId = textureId;
        this.id = id;
    }

   public void render(float x, float y) {
        LogsManager.runWithLogging("Rendering " + id, () -> {
            GameApp.drawTexture(this.textureId, x, y, width, height);
        });
    }

    public static void register(Component ... components) {
        for (Component component : components) {
            LogsManager.runWithLogging("Registering " + component.id, () -> {
                GameApp.addTexture(component.textureId, "textures/" + component.textureId + ".png");
            });
        }
    }

    public static void dispose(Component ... components) {
        for (Component component : components) {
            LogsManager.runWithLogging("Disposing " + component.id, () -> {
                GameApp.disposeTexture(component.textureId);
            });
        }
    }
}