package nl.saxion.game.dungeonheart;

import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;


public class Text extends ScalableGameScreen {

    float TimeBetweenFrames = 0.100f;
    float textAnimationFrames = 0;
    int letterIndex = 0;
    String renderedString = "";
    int screenNumber;


    public Text(int number) {
        super(1100, 1100);
        screenNumber = number;
    }

    public Text() {
        super(1100, 1100);
    }

    @Override
    public void show() {
    }


    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen(Color.BLACK);


        GameApp.startSpriteRendering();


        text(Scene.Story(screenNumber), delta);




        GameApp.endSpriteRendering();

    }


    public void text(String sampleText, float delta) {

        if (letterIndex != sampleText.length()) {
            if (letterIndex < sampleText.length() - 1) {
                GameApp.drawText("basic", renderedString, 0, 25, "red-500");
                if (textAnimationFrames > TimeBetweenFrames) {
                    renderedString += sampleText.split("")[letterIndex];
                    textAnimationFrames = 0f;
                    letterIndex++;
                }
            }
        }
        textAnimationFrames += delta;
    }


    @Override
    public void hide() {

    }
}
