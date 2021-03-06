package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.graphics.Transformable;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;

import java.util.Set;

/**
 * The type Straight line wave.
 */
public class StraightLineWave extends Wave {
    /**
     * Instantiates a new Straight line wave.
     *
     * @param origin      the origin
     * @param destination the destination
     */
    public StraightLineWave(Vector2f origin, Vector2f destination) {
        super(origin, destination);
    }

    @Override
    public void update(Set<Entity> entities, Time deltaTime) {
        for (Entity entity : entities) {
            if (toRespawn.contains(entity)) {
                continue;
            }

            TransformableComponent transformableComponent = (TransformableComponent) entity.findComponent(TransformableComponent.class);

            Transformable transformable = transformableComponent.getTransformable();

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            calculateMoveToPoint(transformable.getPosition());

            float speed = speedComponent.getSpeed();

            transformable.setRotation(rotateSprite(0));

            // If the entity goes out of bounds, reset the entity back to it's starting position
            if (passedDestination(transformable.getPosition())) {
                transformable.setPosition(-origin.x, -origin.y);

                toRespawn.add(entity);

            } else {
                transformable.move(direction.x * speed, direction.y * speed);
            }
        }
    }
}
