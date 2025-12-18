package nl.saxion.game.dungeonheart;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;


public class moveAnimation extends ScalableGameScreen {

    float animationFrames;
    float timeBetweenFrames = 0.5f;
    int i = 0;
    int x = 200;
    int magex = 800;
    int magey = 200;
    int y = 200;
    boolean Animationfinished = false;
    boolean render = true;
    float setHeight = 0;
    float setWidth = 0;



    public moveAnimation() {
        super(1280, 720);

    }

    @Override
    public void show() {
        for (int i = 0; i< 5; i++) {
            GameApp.addTexture("Knight" + i, "textures/Knight/Knight attack-" + i + ".png");
        }
        for (int i = 0; i< 9; i++) {
            GameApp.addTexture("Mage" + i, "textures/Mage/Mage attack-" + i + ".png");
        }
        for (int i = 0; i< 4; i++) {
            GameApp.addTexture("Ninja" + i, "textures/Ninja/Ninja attack-" + i + ".png");
        }
        for (int i = 0; i< 5; i++) {
            GameApp.addTexture("Samurai" + i, "textures/Samurai/Samurai attack-" + i + ".png");
        }
        for (int i = 0; i< 3; i++) {
            GameApp.addTexture("Brawler" + i, "textures/Brawler/Brawler attack-" + i + ".png");
        }

    }

    @Override
    public void render(float delta) {

        super.render(delta);

        setHeight = GameApp.getTextureHeight("Knight0");
        setWidth = GameApp.getTextureWidth("Knight0");


        GameApp.clearScreen(Color.BLACK);


        GameApp.startSpriteRendering();

        if(render){
        GameApp.drawTexture("Knight0", magex, magey, setWidth, setHeight, 0, true, false);
    }

        heroMovement(delta, "Mage");

        //System.out.println(setHeight + "\n" + setWidth);


        GameApp.endSpriteRendering();

    }


    @Override
    public void hide() {
        for (int i = 0; i <= 4; i++) {
            GameApp.disposeTexture("Knight" + i);
            GameApp.disposeTexture("Ninja" + i);
        }
        for (int i = 0; i <= 9; i++) {
            GameApp.disposeTexture("Mage" + i);
        }
        for (int i = 0; i< 5; i++) {
            GameApp.disposeTexture("Samurai" + i);
        }
        for (int i = 0; i< 3; i++) {
            GameApp.disposeTexture("Brawler" + i);
        }
    }


    public void heroMovement(float delta, String heroClass) {

        int frames = switch (heroClass) {
            case "Knight", "Samurai" -> 4;
            case "Mage" -> 8;
            case "Ninja" -> 3;
            case "Brawler" -> 2;
            default -> 0;
        };

        if(heroClass.equals("Brawler")){
            setWidth = (float) (GameApp.getTextureWidth("Knight0") * 2.8);
            setHeight= (float) (GameApp.getTextureHeight("Knight0") * 1.86);

        }
        else{
            setWidth = GameApp.getTextureWidth(heroClass + 0);
            setHeight = GameApp.getTextureHeight(heroClass + 0);
        }

        if(Animationfinished){
            GameApp.drawTexture(heroClass + 0, 200, 200);
            return;
        }

            GameApp.drawTexture((heroClass + i), x, y, setWidth, setHeight);

            if (animationFrames > timeBetweenFrames && i < frames) {
                x += ((magex - 200) / frames);
                y += ((magey - 200) / frames);
                animationFrames = 0f;
                i++;
            }
            else if (animationFrames > timeBetweenFrames && x >= magex) {
                Animationfinished = true;
                render = false;
            }

            animationFrames += delta;
        }


}
