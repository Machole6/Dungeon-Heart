package nl.saxion.game.dungeonheart.componenets;

import nl.saxion.game.dungeonheart.combat.Enemy;
import nl.saxion.gameapp.GameApp;
import com.badlogic.gdx.graphics.Color;

public class EnemyComponent extends Component {
    private final Enemy enemy;

    public Runnable onClick = null;

    public EnemyComponent(Enemy enemy, int width, int height, String textureId, String id) {
        super(width, height, textureId, id);
        this.enemy = enemy;
    }

    @Override
    public void render(float x, float y) {
        super.render(x, y);

        GameApp.drawText(
                "basic",
                "HP: " + enemy.getHealth(),
                this.x,
                this.y - 0.2f * this.height,
                Color.RED
        );
    }
    public void handleClick(float mouseX, float mouseY) {
        if (mouseX >= x && mouseX <= x + width &&
                mouseY >= y && mouseY <= y + height) {
            if (onClick != null)
                onClick.run();
        }
    }

    public Enemy getEnemy() {
        return enemy;
    }
}
