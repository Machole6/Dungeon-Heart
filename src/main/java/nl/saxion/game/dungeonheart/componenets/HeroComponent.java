package nl.saxion.game.dungeonheart.componenets;

import nl.saxion.game.dungeonheart.combat.Hero;
import nl.saxion.gameapp.GameApp;
import com.badlogic.gdx.graphics.Color;

public class HeroComponent extends Component {
    private final Hero hero;


    public Runnable onClick = null;

    public HeroComponent(Hero hero, int width, int height, String textureId, String id) {
        super(width, height, textureId, id);
        this.hero = hero;
    }

    @Override
    public void render(float x, float y) {
        super.render(x, y);

        GameApp.drawText(
                "basic",
                "HP: " + hero.getHealth(),
                this.x,
                this.y - 0.2f * this.height,
                Color.WHITE
        );
    }
    public void handleClick(float mouseX, float mouseY) {
        if (mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height) {
            if (onClick != null)
                onClick.run();
        }
    }

    public Hero getHero() {
        return hero;
    }
}
