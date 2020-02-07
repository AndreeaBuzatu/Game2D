package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.graphics.Transformable;
import uk.ac.lancaster.scc210.engine.ecs.Component;

public class TransformableComponent implements Component {
    private final Transformable transformable;

    public TransformableComponent(Transformable transformable) {
        this.transformable = transformable;
    }

    public Transformable getTransformable() {
        return transformable;
    }
}
