package uk.ac.lancaster.scc210.engine;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import org.jsfml.system.Vector2i;
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
        Vector2i spriteSize = sprite.getTexture().getSize();

        Vector2i halfSpriteSize = Vector2i.div(spriteSize, 2);

        float maxRight = getMaxRight(spriteSize);

        float maxDown = getMaxDown(spriteSize);

        return tooFarLeft(sprite, halfSpriteSize) || tooFarUp(sprite, halfSpriteSize)
                || tooFarRight(sprite, halfSpriteSize, maxRight) || tooFarDown(sprite, halfSpriteSize, maxDown);
    }

    public void resetSprite(Sprite sprite) {
        Vector2f spritePos = sprite.getPosition();

        Vector2i spriteSize = sprite.getTexture().getSize();

        Vector2i halfSpriteSize = Vector2i.div(spriteSize, 2);

        if (tooFarLeft(sprite, halfSpriteSize)) {
            sprite.setPosition(halfSpriteSize.x, spritePos.y);
        }

        if (tooFarUp(sprite, halfSpriteSize)) {
            sprite.setPosition(spritePos.x, (halfSpriteSize.y) + Y_MIN);
        }

        float maxRight = getMaxRight(spriteSize);

        if (tooFarRight(sprite, halfSpriteSize, maxRight)) {
            sprite.setPosition(maxRight + (halfSpriteSize.x), spritePos.y);
        }

        float maxDown = getMaxDown(spriteSize);

        if (tooFarDown(sprite, halfSpriteSize, maxDown)) {
            sprite.setPosition(spritePos.x, maxDown + (halfSpriteSize.y));
        }
    }

    private boolean tooFarLeft(Sprite sprite, Vector2i halfSpriteSize) {
        return (sprite.getPosition().x - (halfSpriteSize.x)) < X_MIN;
    }

    private boolean tooFarUp(Sprite sprite, Vector2i halfSpriteSize) {
        return (sprite.getPosition().y - (halfSpriteSize.y)) < Y_MIN;
    }

    private boolean tooFarRight(Sprite sprite, Vector2i halfSpriteSize, float maxRight) {
        return (sprite.getPosition().x - (halfSpriteSize.x)) > maxRight;
    }

    private boolean tooFarDown(Sprite sprite, Vector2i halfSpriteSize, float maxDown) {
        return (sprite.getPosition().y - (halfSpriteSize.y)) > maxDown;
    }

    private float getMaxRight(Vector2i spriteSize) {
        return viewBounds.width - spriteSize.x;
    }

    private float getMaxDown(Vector2i spriteSize) {
        return viewBounds.height - spriteSize.y;
    }

    public FloatRect getViewBounds() {
        return viewBounds;
    }
}
