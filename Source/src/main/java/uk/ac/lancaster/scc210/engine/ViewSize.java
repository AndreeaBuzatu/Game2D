package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.service.Service;
import uk.ac.lancaster.scc210.game.states.Playing;

/**
 * Service for accessing the current size of the window
 */
public class ViewSize implements Service {
    private final int X_MIN = 0;

    // Prevent the player from going above the playing area
    private final int Y_MIN = Playing.INFO_BOX_HEIGHT;

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

        float maxRight = getMaxRight(globalBounds);

        float maxDown = getMaxDown(globalBounds);

        return tooFarLeft(globalBounds) || tooFarUp(globalBounds)
                || tooFarRight(globalBounds, maxRight) || tooFarDown(globalBounds, maxDown);
    }

    public void resetSprite(Sprite sprite) {
        FloatRect globalBounds = sprite.getGlobalBounds();

        Vector2f spritePos = sprite.getPosition();

        if (tooFarLeft(globalBounds)) {
            sprite.setPosition(0, spritePos.y);
        }

        if (tooFarUp(globalBounds)) {
            sprite.setPosition(spritePos.x, Y_MIN);
        }

        float maxRight = getMaxRight(globalBounds);

        if (tooFarRight(globalBounds, maxRight)) {
            sprite.setPosition(maxRight, spritePos.y);
        }

        float maxDown = getMaxDown(globalBounds);

        if (tooFarDown(globalBounds, maxDown)) {
            sprite.setPosition(spritePos.x, maxDown);
        }
    }

    private boolean tooFarLeft(FloatRect globalBounds) {
        return globalBounds.left < X_MIN;
    }

    private boolean tooFarUp(FloatRect globalBounds) {
        return globalBounds.top < Y_MIN;
    }

    private boolean tooFarRight(FloatRect globalBounds, float maxRight) {
        return globalBounds.left > maxRight;
    }

    private boolean tooFarDown(FloatRect globalBounds, float maxDown) {
        return globalBounds.top > maxDown;
    }

    private float getMaxRight(FloatRect globalBounds) {
        return viewBounds.width - globalBounds.width;
    }

    private float getMaxDown(FloatRect globalBounds) {
        return viewBounds.height - globalBounds.height;
    }

    public FloatRect getViewBounds() {
        return viewBounds;
    }
}
