package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.graphics.Transformable;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;

import java.util.Set;

public class StraightLineWave extends Wave {
    public StraightLineWave(Vector2f origin, Vector2f destination) {
        super(origin, destination);
    }

    @Override
    public void update(Set<Entity> entities, Time deltaTime) {
        for (Entity entity : entities) {
            TransformableComponent transformableComponent = (TransformableComponent) entity.findComponent(TransformableComponent.class);

            Transformable transformable = transformableComponent.getTransformable();

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            calculateMoveToPoint(transformable.getPosition());

            float speed = speedComponent.getSpeed();

            transformable.setRotation(rotateSprite());

            // If the entity goes out of bounds, reset the entity back to it's starting position
            if (passedDestination(transformable.getPosition())) {
                transformable.setPosition(origin);

            } else {
                transformable.move(direction.x * speed, direction.y * speed);
            }
        }
    }
}
