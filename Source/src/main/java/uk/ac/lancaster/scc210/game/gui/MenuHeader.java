package uk.ac.lancaster.scc210.game.gui;

import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.content.FontManager;
import uk.ac.lancaster.scc210.engine.gui.TextContainer;

/**
 * The type Menu header.
 */
public class MenuHeader implements Drawable {
    private final int MENU_TEXT_SIZE = 100;

    private final FontManager fontManager;

    private final TextContainer textContainer;

    private final FloatRect viewBounds;

    private Vector2f position;

    private final String label;

    /**
     * Instantiates a new Menu header.
     *
     * @param label       the label
     * @param fontManager the font manager
     * @param viewBounds  the view bounds
     */
    public MenuHeader(String label, FontManager fontManager, FloatRect viewBounds) {
        this.label = label;
        this.fontManager = fontManager;
        this.viewBounds = viewBounds;

        textContainer = new TextContainer();

        createText();
    }

    private void createText() {
        String[] items = label.split(" ");

        int i = 0;

        float widthSum = 0;

        float firstTextX = 0;

        FloatRect previousBounds = null;

        for (String item : items) {
            Text text = new Text();

            text.setString(item);

            text.setCharacterSize(MENU_TEXT_SIZE);

            text.setFont(fontManager.get("font"));

            FloatRect headerBounds = text.getGlobalBounds();

            if (i == 0) {
                widthSum = ((viewBounds.width/ 2) - (headerBounds.width));

                firstTextX = widthSum;

            } else {
                widthSum += ((previousBounds.width));
            }

            Vector2f position = new Vector2f(widthSum, viewBounds.height / 5f);

            text.setPosition(position);

            if (i % 2 == 0) {
                text.setStyle(Text.ITALIC);

                text.setColor(Color.CYAN);

            } else {
                text.setColor(Color.YELLOW);
            }

            textContainer.add(text);

            previousBounds = headerBounds;

            i++;
        }

        position = new Vector2f(firstTextX, viewBounds.height / 5f);
    }

    @Override
    public void draw(RenderTarget renderTarget, RenderStates renderStates) {
        //renderTarget.draw(menuHeader);
        renderTarget.draw(textContainer);
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Vector2f getPosition() {
        return position;
    }
}
