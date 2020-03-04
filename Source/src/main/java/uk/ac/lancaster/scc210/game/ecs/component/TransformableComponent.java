package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.graphics.Transformable;
import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * The type Transformable component.
 */
public class TransformableComponent implements Component {
    private final Transformable transformable;

    /**
     * Instantiates a new Transformable component.
     *
     * @param transformable the transformable
     */
    public TransformableComponent(Transformable transformable) {
        this.transformable = transformable;
    }

    /**
     * Gets transformable.
     *
     * @return the transformable
     */
    public Transformable getTransformable() {
        return transformable;
    }
}
