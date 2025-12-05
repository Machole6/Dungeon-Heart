package nl.saxion.game.dungeonheart;

<<<<<<< Updated upstream
=======
import ch.qos.logback.core.joran.conditional.IfAction;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
>>>>>>> Stashed changes
import com.badlogic.gdx.graphics.Color;
import nl.saxion.gameapp.GameApp;
import nl.saxion.gameapp.screens.ScalableGameScreen;


public class Text extends ScalableGameScreen {

    float TimeBetweenFrames = 0.100f;
    float textAnimationFrames = 0;
    int letterIndex = 0;
    String renderedString = "";
    int screenNumber;
<<<<<<< Updated upstream


    public Text(int number) {
        super(1100, 1100);
=======
    float delay = -1;
    float setDelay = 0;


    public Text(int number) {
        super(1280, 720);
>>>>>>> Stashed changes
        screenNumber = number;
    }

    public Text() {
<<<<<<< Updated upstream
        super(1100, 1100);
=======
        super(1280, 720);
>>>>>>> Stashed changes
    }

    @Override
    public void show() {
<<<<<<< Updated upstream
=======
        GameApp.addTimer("Timer1", 30f, false);
        GameApp.addFont("grinched", "fonts/grinched.otf", 50);
>>>>>>> Stashed changes
    }


    public void render(float delta) {
        super.render(delta);

        GameApp.clearScreen(Color.BLACK);


        GameApp.startSpriteRendering();


<<<<<<< Updated upstream
        text(Scene.Story(screenNumber), delta);



=======

        text(Scene.Story(screenNumber), delta);

//        setDelay = 30f * renderedString.length();

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
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
=======
        delay += delta;
>>>>>>> Stashed changes
    }


    @Override
    public void hide() {

<<<<<<< Updated upstream
=======
        GameApp.disposeFont("grinched");
>>>>>>> Stashed changes
    }
}
