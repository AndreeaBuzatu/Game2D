package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.service.Service;

// TODO: Review if this is a good idea or not
public class WindowSize implements Service {
    private final int X_MIN = 0;

    private final int Y_MIN = 0;

    private final int width, height;

    public WindowSize(final int width, final int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean outOfBounds(Sprite sprite) {
        FloatRect globalBounds = sprite.getGlobalBounds();

        float maxRight = width - globalBounds.width;

        float maxDown = height - globalBounds.height;

        return globalBounds.left < X_MIN || globalBounds.top < Y_MIN
                || globalBounds.left > maxRight || globalBounds.top > maxDown;
    }
}
