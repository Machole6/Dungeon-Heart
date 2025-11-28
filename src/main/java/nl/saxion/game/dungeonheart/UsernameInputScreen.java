package nl.saxion.game.dungeonheart;

import nl.saxion.game.dungeonheart.database.Database;
import nl.saxion.gameapp.screens.ScalableGameScreen;

public class UsernameInputScreen extends ScalableGameScreen {
    public UsernameInputScreen() {
        super(1280, 720);
    }


    @Override
    public void show() {
        // this is how you get the username from the database (maybe you'll need it idk just showing)
        final String username = Database.Users.getCurrentUsername();
        /// this is how you set a username, you will aboslutely need this one)
        Database.Users.updateCurrentUsername("megagayboy123");
    }

    @Override
    public void hide() {

    }
}
