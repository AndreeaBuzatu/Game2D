package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.service.Service;

/**
 * Service for accessing the current size of the window
 */
public class WindowSize implements Service {
    private final int X_MIN = 0;

    private final int Y_MIN = 0;

    private final int width, height;

    /**
     * Instantiates the WindowSize service
     *
     * @param width  width of the window
     * @param height height of the window
     */
    WindowSize(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Given a sprite, see if the sprite is within the bounds of the window
     *
     * @param sprite the sprite to consider
     * @return if the sprite is out of bounds or not
     */
    public boolean outOfBounds(Sprite sprite) {
        FloatRect globalBounds = sprite.getGlobalBounds();

        float maxRight = width - globalBounds.width;

        float maxDown = height - globalBounds.height;

        return globalBounds.left < X_MIN || globalBounds.top < Y_MIN
                || globalBounds.left > maxRight || globalBounds.top > maxDown;
    }
}
