package nl.saxion.game.dungeonheart.componenets;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class Component {
    protected Map<String, Component> componentsMap = new HashMap<>();
    protected int height;
    protected int width;
    protected String textureId;
    protected String id;
    protected float x;
    protected float y;
    protected boolean isEnabled = true;
    protected  boolean isHovered = false;
    private Consumer<Component>onHover;
    private Consumer<Component> onUnhover;
    public Runnable onClick;

    protected Component(int width, int height, String textureId, String id) {
        this.height = height;
        this.width = width;
        this.textureId = textureId;
        this.id = id;
    }

    public void render(float x, float y) {
        this.x = x;
        this.y = y;

        if (this.onClick != null && GameApp.isButtonJustPressed(Input.Buttons.LEFT) && checkIfMouseIsOverComponent() && isEnabled) {
            LogsManager.runWithLogging("Clicked " + this.id, () -> onClick.run());
        } else if (this.onHover != null && checkIfMouseIsOverComponent() && isEnabled) {
            this.isHovered = true;
            LogsManager.runWithLogging("Hovered " + this.id, () -> onHover.accept(this));
            GameApp.drawTexture(this.textureId, this.x, this.y, this.width, this.height);
        } else if (this.onUnhover != null && this.isHovered && !checkIfMouseIsOverComponent() && isEnabled)  {
            this.isHovered = false;
            LogsManager.runWithLogging("Unhovered " + this.id, () -> onUnhover.accept(this));
            GameApp.drawTexture(this.textureId, this.x, this.y, this.width, this.height);
        } else {
            GameApp.drawTexture(this.textureId, this.x, this.y, this.width, this.height);
        }
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

    protected boolean checkIfMouseIsOverComponent() {
        final int mousePosX = GameApp.getMousePositionInWindowX();
        final int mousePosY = GameApp.getWindowHeight() - GameApp.getMousePositionInWindowY();
        final float buttonAreaX = this.x + this.width;
        final float buttonAreaY = this.y + this.height;
        return (mousePosX > this.x && mousePosX < buttonAreaX && mousePosY > this.y && mousePosY < buttonAreaY);
    }

    public void changeTexture(String newTextureId) {
        this.textureId = newTextureId;
        if (!GameApp.hasTexture(newTextureId)) {
            GameApp.addTexture(newTextureId, "textures/" + newTextureId + ".png");
        }
    }

    public static void setOnHoverFor(Consumer<Component> action, Component ... components) {
        for (Component component : components) {
            component.onHover = action;
        }
    }

    public static void setOnUnhoverFor(Consumer<Component> action, Component ... components) {
        for (Component component : components) {
            component.onUnhover = action;
        }
    }
}