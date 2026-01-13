package nl.saxion.game.dungeonheart;

import nl.saxion.gameapp.GameApp;

import java.awt.*;

public class TextInput {

    public int x, y, width, height;
    public String text = "";
    public String placeholder;
    public boolean focused = false;

    public TextInput(int x, int y, int width, int height, String placeholder) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.placeholder = placeholder;
    }

//    public void render_rect() {
//        // draw background
//        GameApp.drawRect(x, y, width, height);
//    }

    public void render_text() {
        String display = text.isEmpty() ? placeholder : text;
        GameApp.drawText("basic", display, x + 10, y + height - 20, "white");
    }

    public boolean inside(int mx, int my) {
        return mx >= x && mx <= x + width &&
                my >= y && my <= y + height;
    }

    public void key(char c) {
        if (!focused) return;

        if (c == '\b') {
            if (text.length() > 0)
                text = text.substring(0, text.length() - 1);
            return;
        }

        if (Character.isLetterOrDigit(c))
            text += c;
    }

    public String get() { return text.trim(); }
}
