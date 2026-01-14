package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.componenets.Button;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;

public class Shop extends ScalableGameScreen {


    int spriteHeight = 150;
    int spriteWidth = 120;
    int itemx = 220;
    int itemy = 300;
    int current;
    private final int Frame_width = 200;
    private final int Frame_height = 200;
    private final String BUTTON_FONT = "jumpsWinter";
    private final String Sprite_frame = "SpriteFrame";
    private final String Sprite_frame_onHover = "SoldSpriteFrame";
    private final String Potion_frame = "PotionFrame";
    private final String Potion_frame_onHover = "SoldPotionFrame";
    ArrayList<Item> shopItems = new ArrayList<>();


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
        GameApp.addTexture("HPPotion", "textures/Potions/HP.png");
        GameApp.addTexture("HarmPotion", "textures/Potions/Harm.png");
        GameApp.addFont("grinched", "fonts/grinched.otf", 50);
        GameApp.addFont("Arial", "fonts/pixel-arial-14.ttf", 50);
        GameApp.addTextureAtlas("Gladiator", "textures/Sprites/Gladiator/Idle/Gidle.atlas");
        GameApp.addAnimationFromAtlas("GladIdle", "Gladiator", "idle", 1f, true);
        GameApp.addTextureAtlas("Elf", "textures/Sprites/Elf/Idle/EIdle.atlas");
        GameApp.addAnimationFromAtlas("ElfIdle", "Elf", "idle", 1f, true);
        GameApp.addTextureAtlas("DarkElf", "textures/Sprites/Dark Elf/Idle/DEIdle.atlas");
        GameApp.addAnimationFromAtlas("DEIdle", "DarkElf", "idle", 1f, true);
        loadItems();

    }


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
        itemBackground();
        GameApp.drawAnimation("DEIdle", 910, 300, spriteWidth, spriteHeight);
        GameApp.drawAnimation("ElfIdle", 590, 300, spriteWidth, spriteHeight);
        GameApp.drawAnimation("GladIdle", 270, 300, spriteWidth, spriteHeight);
        GameApp.drawTexture("HPPotion", 260, 115, spriteWidth-20, spriteHeight-25);
        GameApp.drawTexture("HarmPotion", 580, 115, spriteWidth-20, spriteHeight-25);


        System.out.println(Database.Users.getUserHeroes() + "\n" + Database.Users.getUserPotions());





        GameApp.endSpriteRendering();
    }


    public void loadItems(){

        Item Gladiator = new Item();
        Gladiator.Button = new Button(Frame_width, Frame_height, Sprite_frame, "Glad", "", BUTTON_FONT);
        Gladiator.Description = Scene.ItemDescription[0];
        Gladiator.cost = 300;
        Gladiator.unit = true;

        Item Elf = new Item();
        Elf.Button = new Button(Frame_width, Frame_height, Sprite_frame, "Elf", "", BUTTON_FONT);
        Elf.Description = Scene.ItemDescription[1];
        Elf.cost = 300;
        Elf.unit = true;

        Item DarkElf = new Item();
        DarkElf.Button = new Button(Frame_width, Frame_height, Sprite_frame, "DarkElf", "", BUTTON_FONT);
        DarkElf.Description = Scene.ItemDescription[2];
        DarkElf.cost = 300;
        DarkElf.unit = true;

        Item HP = new Item();
        HP.Button = new Button(Frame_width, Frame_height, Potion_frame, "HP", "", BUTTON_FONT);
        HP.Description = Scene.ItemDescription[3];
        HP.cost = 300;
        HP.unit = false;

        Item Harm = new Item();
        Harm.Button = new Button(Frame_width, Frame_height, Potion_frame, "Harm", "", BUTTON_FONT);
        Harm.Description = Scene.ItemDescription[4];
        Harm.cost = 300;
        Harm.unit = false;

        shopItems.add(Gladiator);
        shopItems.add(Elf);
        shopItems.add(DarkElf);
        shopItems.add(HP);
        shopItems.add(Harm);

    }


    public void purchase(Item selectedItem){

        if (!Database.Users.getUserHeroes().contains(selectedItem.Description) && !Database.Users.getUserPotions().contains(selectedItem.Description)) {
            if (!selectedItem.unit){
                Database.Users.addPotionToUser(selectedItem.Description);
            }
            else {
                Database.Users.addHeroToUser(selectedItem.Description);

            }
        }


    }

    public void itemBackground(){
        for (int i=0; i<3; i++) {
            final int index = i;
            shopItems.get(i).Button.render(itemx + (i*320), itemy);
            current = i;
            Component.setOnHoverFor((button) -> {
                button.changeTexture(Sprite_frame_onHover);
                GameApp.drawText("Arial", shopItems.get(index).Description, 850, 100, Color.BLACK);
            }, shopItems.get(i).Button);
        }
        for (int i=3; i<5; i++) {
            shopItems.get(i).Button.render(220 + (320*(i-3)), 100);
            current = i;
            final int index = i;
            Component.setOnHoverFor((button) -> {
                button.changeTexture(Potion_frame_onHover);
                GameApp.drawText("Arial", shopItems.get(index).Description, 850, 100, Color.BLACK);
            }, shopItems.get(i).Button);

            for (Item item: shopItems){
                item.Button.onClick = () -> purchase(item);
            }
        }
        Component.setOnUnhoverFor((button) -> {
            button.changeTexture(Potion_frame);
        }, shopItems.get(3).Button, shopItems.get(4).Button);

        Component.setOnUnhoverFor((button) -> {
            button.changeTexture(Sprite_frame);
        }, shopItems.get(0).Button, shopItems.get(1).Button, shopItems.get(2).Button);


    }
    @Override
    public void hide() {

        GameApp.disposeTexture("Scroll");
    }
}
