package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.componenets.Button;
import nl.saxion.game.dungeonheart.componenets.Component;
import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;

import javax.xml.crypto.Data;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Shop extends ScalableGameScreen {


    int spriteHeight = 150;
    int spriteWidth = 120;
    int itemx = 220;
    int itemy = 300;
    int spritex = 270;
    int counter = 0;
    private final int Frame_width = 200;
    private final int Frame_height = 200;
    private final String BUTTON_FONT = "jumpsWinter";
    private final String Sprite_frame = "SpriteFrame";
    private final String Sprite_frame_onHover = "SoldSpriteFrame";
    private final String Potion_frame = "PotionFrame";
    private final String Potion_frame_onHover = "SoldPotionFrame";
    ArrayList<Item> shopItems = new ArrayList<>();
    ArrayList<Integer> randomNumbers = new ArrayList<>();
    int size = 7;

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
        GameApp.addTexture("Brawler", "textures/Sprites/Brawler/Brawler attack-0.png");
        GameApp.addTexture("Samurai", "textures/Sprites/Samurai/Samurai attack-1.png");
        GameApp.addTexture("Ninja", "textures/Sprites/Ninja/Ninja attack-4.png");
        GameApp.addTexture("Mage", "textures/Sprites/Mage/Mage attack-2.png");
        GameApp.addTexture("Knight", "textures/Sprites/Knight/Knight attack-0.png");
        GameApp.addTexture("Shogun", "textures/Sprites/Shogun/Shogun-idle.png");
        GameApp.addTexture("Vampire", "textures/Sprites/Vampire/Vampire-idle.png");


        GameApp.addFont("grinched", "fonts/grinched.otf", 50);
        GameApp.addFont("Arial", "fonts/pixel-arial-14.ttf", 50);
        GameApp.addTextureAtlas("Gladiator", "textures/Sprites/Gladiator/Idle/Gidle.atlas");
        GameApp.addAnimationFromAtlas("Gladiator", "Gladiator", "idle", 1f, true);
        GameApp.addTextureAtlas("Elf", "textures/Sprites/Elf/Idle/EIdle.atlas");
        GameApp.addAnimationFromAtlas("Elf", "Elf", "idle", 1f, true);
        GameApp.addTextureAtlas("DarkElf", "textures/Sprites/Dark Elf/Idle/DEIdle.atlas");
        GameApp.addAnimationFromAtlas("DarkElf", "DarkElf", "idle", 1f, true);
        loadItems();

    }


    @Override
    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen();

        GameApp.updateAnimation("Gladiator");
        GameApp.updateAnimation("Elf");
        GameApp.updateAnimation("DarkElf");
        ArrayList<String> shopSprites = new ArrayList<>(Arrays.asList("Gladiator","Elf", "DarkElf", "Brawler", "Samurai", "Vampire", "Shogun"));
        List<String> HeroesInParty = Database.Users.getUserHeroes();

//        for (int i = shopSprites.size() - 1; i >= 0; i--) {
//            if (HeroesInParty.contains(shopSprites.get(i))) {
//                shopSprites.remove(i);
//                shopItems.remove(i);
//            }
//        }

//        for (int i = shopItems.size() - 1; i >= 0; i--) {
//            Item item = shopItems.get(i);
//
//            if (item.unit && HeroesInParty.contains(item.Description)) {
//                shopItems.remove(i);
//                shopSprites.remove(item.Description);
//            }
//        }



        GameApp.startSpriteRendering();

        GameApp.drawTextureCentered("Bg", (float) GameApp.getWindowWidth() /2, (float) GameApp.getWindowHeight() /2, 1280, 720);
        GameApp.drawTextureCentered("sign", (float) GameApp.getWindowWidth()/2, 620);
        GameApp.drawTextCentered("basic", "Shop", getWorldWidth()/2,660, Color.BLACK);
        itemBackground();
        for (int i=0; i<3; i++){
            if (shopSprites.get(randomNumbers.get(i)).equals("Gladiator") || shopSprites.get(randomNumbers.get(i)).equals("Elf") || shopSprites.get(randomNumbers.get(i)).equals("DarkElf")) {
                GameApp.drawAnimation(shopSprites.get(randomNumbers.get(i)), (spritex + (i * 320)), 300, spriteWidth, spriteHeight);
            }
            else {
                GameApp.drawTexture(shopSprites.get(randomNumbers.get(i)), (spritex + (i * 320)), 300, spriteWidth, spriteHeight);
            }
        }

        GameApp.drawTexture("HPPotion", 260, 115, spriteWidth-20, spriteHeight-25);
        GameApp.drawTexture("HarmPotion", 580, 115, spriteWidth-20, spriteHeight-25);

        if (counter == 2){
            GameApp.switchScreen("MainMenuScreen");
        }

        System.out.println(HeroesInParty + "\n" + Database.Users.getUserPotions());




        GameApp.endSpriteRendering();
    }


    public void loadItems(){

        for (int i =0; i<7; i++){

            Item character = new Item();
            character.Button = new Button(Frame_width, Frame_height, Sprite_frame, Scene.ItemDescription[i], "", BUTTON_FONT);
            character.Description = Scene.ItemDescription[i];
            character.cost = 300;
            character.unit = true;
            shopItems.add(character);
        }

        Item HP = new Item();
        HP.Button = new Button(Frame_width, Frame_height, Potion_frame, "HP", "", BUTTON_FONT);
        HP.Description = Scene.ItemDescription[7];
        HP.cost = 300;
        HP.unit = false;

        Item Harm = new Item();
        Harm.Button = new Button(Frame_width, Frame_height, Potion_frame, "Harm", "", BUTTON_FONT);
        Harm.Description = Scene.ItemDescription[8];
        Harm.cost = 300;
        Harm.unit = false;


        shopItems.add(HP);
        shopItems.add(Harm);

        while (randomNumbers.size()<3){
            int random = GameApp.randomInt(0, size);
            if (!randomNumbers.contains(random)){
                randomNumbers.add(random);
            }
        }
    }


    public void purchase(Item selectedItem){

        if (selectedItem.unit){
            if(!Database.Users.getUserHeroes().contains(selectedItem.Description)){
                Database.Users.addHeroToUser(selectedItem.Description);
                counter++;
            }
        }
        else if (!selectedItem.unit){
            Database.Users.addPotionToUser(selectedItem.Description);
            counter++;

        }


    }

    public void itemBackground(){
        for (int i=0; i<3; i++) {
            final int index = randomNumbers.get(i);
            shopItems.get(index).Button.render(itemx + (i*320), itemy);

            Component.setOnHoverFor((button) -> {
                button.changeTexture(Sprite_frame_onHover);
                GameApp.drawText("Arial", shopItems.get(index).Description, 850, 100, Color.BLACK);
            }, shopItems.get(index).Button);

            Component.setOnUnhoverFor((button) -> {
                button.changeTexture(Sprite_frame);
            }, shopItems.get(index).Button);
        }
        for (int i=7; i<9; i++) {
            shopItems.get(i).Button.render(220 + (320*(i-7)), 100);

            final int index = i;
            
            Component.setOnHoverFor((button) -> {
                button.changeTexture(Potion_frame_onHover);
                GameApp.drawText("Arial", shopItems.get(index).Description, 850, 100, Color.BLACK);
            },shopItems.get(i).Button);

            Component.setOnUnhoverFor((button) -> {
                button.changeTexture(Potion_frame);
            }, shopItems.get(i).Button);




        }

        for (Item item: shopItems){
            item.Button.onClick = () -> purchase(item);
        }


    }


    @Override
    public void hide() {

        GameApp.disposeTexture("Scroll");
    }
}
