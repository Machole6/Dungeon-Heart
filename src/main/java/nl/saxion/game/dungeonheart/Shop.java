package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.componenets.Button;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import java.awt.*;

public class Shop extends ScalableGameScreen {


    public Shop() {
        super(1280, 720);

    }

    @Override
    public void show() {
        GameApp.addTexture("sign", "textures/Shop/transparentShopSign.png");
        GameApp.addTexture("Bg", "textures/Shop/woodenwall.png");
        GameApp.addTexture("SpriteFrame", "textures/Shop/Sprite_frame.png");
        GameApp.addTexture("SoldSpriteFrame", "textures/Shop/Red Sprite frame.png");
        GameApp.addTexture("PotionFrame", "textures/Shop/Potion Frame.png");
        GameApp.addTexture("SoldPotionFrame", "textures/Shop/Red Potion Frame.png");
        GameApp.addFont("grinched", "fonts/grinched.otf", 50);
        GameApp.addTextureAtlas("Gladiator", "textures/Sprites/Gladiator/Idle/Gidle.atlas");
        GameApp.addAnimationFromAtlas("GladIdle", "Gladiator", "idle", 1f, true);
        GameApp.addTextureAtlas("Elf", "textures/Sprites/Elf/Idle/EIdle.atlas");
        GameApp.addAnimationFromAtlas("ElfIdle", "Elf", "idle", 1f, true);
        GameApp.addTextureAtlas("DarkElf", "textures/Sprites/Dark Elf/Idle/DEIdle.atlas");
        GameApp.addAnimationFromAtlas("DEIdle", "DarkElf", "idle", 1f, true);

    }

    int spriteHeight = 150;
    int spriteWidth = 120;
    private final int Frame_width = 560;
    private final int Frame_height = 150;
    private final String BUTTON_FONT = "jumpsWinter";
    private final String Sprite_frame = "SpriteFrame";
    private final String Sprite_frame_onHover = "SoldSpriteFrame";
    private final String Potion_frame = "PotionFrame";
    private final String Potion_frame_onHover = "SoldPotionFrame";
    private final Button sprite1 = new Button(Frame_width, Frame_height, Sprite_frame, "Glad", "", BUTTON_FONT);


    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen();

        GameApp.updateAnimation("GladIdle");
        GameApp.updateAnimation("ElfIdle");
        GameApp.updateAnimation("DEIdle");


        GameApp.startSpriteRendering();

        GameApp.drawTextureCentered("Bg", (float) GameApp.getWindowWidth() /2, (float) GameApp.getWindowHeight() /2, 1280, 720);
        GameApp.drawTextureCentered("sign", (float) GameApp.getWindowWidth()/2, 620);
        GameApp.drawTextCentered("basic", "Shop", getWorldWidth()/2,660, Color.BLACK);
        GameApp.drawTextureCentered("SpriteFrame", 300, 300);
        GameApp.drawAnimation("DEIdle", 200, 200, spriteWidth, spriteHeight);
        GameApp.drawAnimation("ElfIdle", 500, 200, spriteWidth, spriteHeight);
        GameApp.drawAnimation("GladIdle", 700, 200, spriteWidth, spriteHeight);
        //sprite1.render(200, 200);
//        Component.setOnHoverFor((button) -> {
//            button.changeTexture(Sprite_frame_onHover);
//        }, sprite1);
//
//        Component.setOnUnhoverFor((button) -> {
//            button.changeTexture(Sprite_frame);
//        }, sprite1);







        GameApp.endSpriteRendering();
    }



    @Override
    public void hide() {

        GameApp.disposeTexture("Scroll");
    }
}
