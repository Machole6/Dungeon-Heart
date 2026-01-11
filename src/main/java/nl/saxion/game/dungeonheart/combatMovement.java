package nl.saxion.game.dungeonheart;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;


public class combatMovement extends ScalableGameScreen {


    int x = 200;
    int magex = 800;
    int magey = 200;
    int y = 200;
    boolean render = true;
    float setHeight = 0;
    float setWidth = 0;


    public combatMovement() {
        super(1280, 720);

    }

    @Override
    public void show() {

        GameApp.addTextureAtlas("Knight", "textures/Knight/attack_1.atlas");
        GameApp.addAnimationFromAtlas("KnightAttack", "Knight", "attack", 1f, false);
        GameApp.addTextureAtlas("Mage", "textures/Mage/mageattack.atlas");
        GameApp.addAnimationFromAtlas("MageAttack", "Mage", "attack", 1f, false);
        GameApp.addTextureAtlas("Brawler", "textures/Brawler/brawlerattack.atlas");
        GameApp.addAnimationFromAtlas("BrawlerAttack", "Brawler", "attack", 1f, false);
        GameApp.addTextureAtlas("Ninja", "textures/Ninja/Ninjaattack.atlas");
        GameApp.addAnimationFromAtlas("NinjaAttack", "Ninja", "attack", 1f, false);
        GameApp.addTextureAtlas("Samurai", "textures/Samurai/SamuraiAttack.atlas");
        GameApp.addAnimationFromAtlas("SamuraiAttack", "Samurai", "attack", 1f, false);

    }

    @Override
    public void render(float delta) {

        super.render(delta);
        GameApp.clearScreen(Color.BLACK);

        GameApp.updateAnimation("KnightAttack");
        GameApp.updateAnimation("MageAttack");
        GameApp.updateAnimation("NinjaAttack");
        GameApp.updateAnimation("BrawlerAttack");
        GameApp.updateAnimation("SamuraiAttack");


        GameApp.startSpriteRendering();


        GameApp.drawAnimation("NinjaAttack", magex, magey, setWidth, setHeight, 0, true, false);

        GameApp.drawAnimation("MageAttack", x, y);
        if (!(x == magex)) {
            x += ((magex - 200) / 360);
        }
        GameApp.endSpriteRendering();

    }


    @Override
    public void hide() {
        GameApp.disposeAtlas("Knight");
        GameApp.disposeAtlas("Mage");
        GameApp.disposeAtlas("Brawler");
        GameApp.disposeAtlas("Ninja");
        GameApp.disposeAtlas("Samurai");

    }

}