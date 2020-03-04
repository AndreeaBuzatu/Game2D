package uk.ac.lancaster.scc210.game.ecs.component;

import org.jsfml.graphics.CircleShape;
import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.Texture;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Component;

/**
 * The type Asteroid component.
 */
public class AsteroidComponent implements Component {
    private final CircleShape circle;

    /**
     * Instantiates a new Asteroid component.
     *
     * @param texture the texture
     */
    public AsteroidComponent(Texture texture) {
        // Assume the textures have fixed width and height
        circle = new CircleShape(texture.getSize().x);

        // Set the origin to the centre-middle of the circle
        FloatRect localBounds = circle.getLocalBounds();

        Vector2f centreMiddle = new Vector2f(localBounds.width / 2, localBounds.height / 2);

        circle.setTexture(texture);

        circle.setOrigin(centreMiddle);
    }

    /**
     * Gets circle.
     *
     * @return the circle
     */
    public CircleShape getCircle() {
        return circle;
    }
}
