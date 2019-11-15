package uk.ac.lancaster.game.screens.mainmenu;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.graphics.Text;
import uk.ac.lancaster.game.Game;

public class MenuOption {
    private final char SELECTED_LEFT = '>';

    private final char SELECTED_RIGHT = '<';

    private String optionString;

    private Text text;

    private float distanceDown;

    private boolean selected;

    MenuOption(Text text, float distanceDown) {
        this.text = text;
        this.distanceDown = distanceDown;

        optionString = text.getString();

        selected = false;

        centreText();
    }

    public void draw(RenderTarget target) {
        target.draw(text);
    }

    public void setTextSelected() {
        text.setString(String.format("%c%s%c", SELECTED_LEFT, text.getString(), SELECTED_RIGHT));
    }

    public void clearText() {
        text.setString(optionString);
    }

    public void centreText() {
        float halfWidth = text.getLocalBounds().width / 2;

        float xPos = (Game.WINDOW_WIDTH / 2) - halfWidth;

        text.setPosition(xPos, distanceDown);
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected() {
        selected = true;
    }

    public void setUnselected() {
        selected = false;
    }
}
