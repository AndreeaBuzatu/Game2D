package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * The type Orientated box component.
 */
public class OrientatedBoxComponent implements Component {
    private final OrientatedBox orientatedBox;

    /**
     * Instantiates a new Orientated box component.
     *
     * @param sprite the sprite
     */
    public OrientatedBoxComponent(Sprite sprite) {
        this.orientatedBox = new OrientatedBox(sprite);
    }

    /**
     * Instantiates a new Orientated box component.
     *
     * @param shape the shape
     */
    public OrientatedBoxComponent(Shape shape) {
        this.orientatedBox = new OrientatedBox(shape);
    }

    /**
     * Gets orientated box.
     *
     * @return the orientated box
     */
    public OrientatedBox getOrientatedBox() {
        return orientatedBox;
    }
}
