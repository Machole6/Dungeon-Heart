package nl.saxion.game.dungeonheart;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;


public class Text extends ScalableGameScreen {

    float TimeBetweenFrames = 0.100f;
    float textAnimationFrames = 0;
    int letterIndex = 0;
    String renderedString = "";
    int screenNumber;
    float delay = -1;
    float setDelay = 0;
    float timer;


    public Text(int number) {
        super(1280, 720);
        screenNumber = number;
    }

    @Override
    public void show() {
        GameApp.addTimer("Timer1", 30f, false);
        GameApp.addFont("grinched", "fonts/grinched.otf", 50);
        GameApp.addTexture("Scroll", "textures/scrollpaper.png");
    }


    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen(Color.BLACK);

        timer = (float) (Scene.Story(screenNumber).length() / 6.56);
        GameApp.setTimerDuration("Timer1", (timer - 7f));

        GameApp.startSpriteRendering();

        GameApp.drawTextureCentered("Scroll", (float) GameApp.getWindowWidth() /2, (float) GameApp.getWindowHeight() /2, 1280, 720);

        text(Scene.Story(screenNumber), delta);



        GameApp.updateTimer("Timer1");

        if(GameApp.timerWentOff("Timer1")) {
            setDelay+=1;
        }

        if (setDelay == 1){
            GameApp.drawTextCentered("grinched", "Press Enter to Continue", (float) GameApp.getWindowWidth() / 2 -40, (float) GameApp.getWindowHeight() / 2, "red-500");

        }

        if (GameApp.isKeyPressed(Input.Keys.ENTER)){
            GameApp.switchScreen("LeadersScreen");
        }
        GameApp.endSpriteRendering();

    }


    public void text(String sampleText, float delta) {

        if (letterIndex != sampleText.length()) {
            if (letterIndex < sampleText.length() - 1) {
                GameApp.drawTextCentered("basic", renderedString, getWorldWidth()/2,getWorldHeight()/2, Color.BLACK);
                if (textAnimationFrames > TimeBetweenFrames) {
                    renderedString += sampleText.split("")[letterIndex];
                    textAnimationFrames = 0f;
                    letterIndex++;
                }
            }
        }
        textAnimationFrames += delta;

        delay += delta;
    }


    @Override
    public void hide() {

        GameApp.disposeTexture("Scroll");
        GameApp.disposeFont("grinched");
        GameApp.disposeAtlas("Dark Elf");

    }
}
