package nl.saxion.game.dungeonheart.componenets;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import nl.saxion.game.dungeonheart.managers.LogsManager;
import nl.saxion.gameapp.GameApp;

import java.util.function.Function;

public class Button extends Component {
    public String text;
    public String font;
    public Boolean isEnabled;
    public float x;
    public float y;

    public Button(int width, int height, String textureId, String id, String text, String font) {
        super(width, height, textureId, id);
        this.text = text;
        this.font = font;
        this.isEnabled = true;
        this.componentsMap.put("buttons", this);
    }

    @Override
    public void render(float x, float y) {
        this.x = x;
        this.y = y;

        super.render(x, y);
        GameApp.drawTextCentered(this.font, this.text, x + (float) this.width / 2, y + (float) this.height / 2 + 10, Color.WHITE);
    }

    public Button getButtonById(String id) {
        return (Button) componentsMap.get(id);
    }
}
