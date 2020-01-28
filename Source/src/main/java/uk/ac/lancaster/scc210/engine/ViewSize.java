package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.service.Service;

/**
 * Service for accessing the current size of the window
 */
public class ViewSize implements Service {
    private final int X_MIN = 0;

    private final int Y_MIN = 0;

    private final FloatRect viewBounds;

    /**
     * Instantiates the WindowSize service
     *
     * @param viewBounds bounds of the view
     */
    ViewSize(final FloatRect viewBounds) {
        this.viewBounds = viewBounds;
    }

    /**
     * Given a sprite, see if the sprite is within the bounds of the window
     *
     * @param sprite the sprite to consider
     * @return if the sprite is out of bounds or not
     */
    public boolean outOfBounds(Sprite sprite) {
        FloatRect globalBounds = sprite.getGlobalBounds();

        float maxRight = viewBounds.width - globalBounds.width;

        float maxDown = viewBounds.height - globalBounds.height;

        return globalBounds.left < X_MIN || globalBounds.top < Y_MIN
                || globalBounds.left > maxRight || globalBounds.top > maxDown;
    }
}
