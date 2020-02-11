package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.graphics.Shape;
import org.jsfml.graphics.Sprite;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.ecs.Component;

public class OrientatedBoxComponent implements Component {
    private final OrientatedBox orientatedBox;

    public OrientatedBoxComponent(Sprite sprite) {
        this.orientatedBox = new OrientatedBox(sprite);
    }

    public OrientatedBoxComponent(Shape shape) {
        this.orientatedBox = new OrientatedBox(shape);
    }

    public OrientatedBox getOrientatedBox() {
        return orientatedBox;
    }
}
