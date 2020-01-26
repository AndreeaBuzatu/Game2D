package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * Indicates that an Entity has a Sprite. Usually used to indicate that an Entity is drawable
 */
public class SpriteComponent implements Component {
    private final Sprite sprite;

    /**
     * Instantiates a new Sprite component.
     *
     * @param sprite the sprite
     */
    public SpriteComponent(Sprite sprite) {
        this.sprite = sprite;
    }

    /**
     * Gets sprite.
     *
     * @return the sprite
     */
    public Sprite getSprite() {
        return sprite;
    }
}
