package uk.ac.lancaster.scc210.game.gui;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.content.FontManager;

public class MenuHeader implements Drawable {
    private final int MENU_TEXT_SIZE = 100;

    private final Text menuHeader;

    private final Vector2f position;

    public MenuHeader(String label, FontManager fontManager, FloatRect viewBounds) {
        menuHeader = new Text();

        menuHeader.setString(label);

        menuHeader.setFont(fontManager.get("font"));

        menuHeader.setCharacterSize(MENU_TEXT_SIZE);

        FloatRect headerBounds = menuHeader.getGlobalBounds();

        position = new Vector2f((viewBounds.width / 2) - (headerBounds.width / 2), viewBounds.height / 5f);

        menuHeader.setPosition(position);
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        renderTarget.draw(menuHeader);
    }

    public Vector2f getPosition() {
        return position;
    }
}
