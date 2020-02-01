package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

import java.util.Set;

public class StraightLineWave extends Wave {
    public StraightLineWave(Vector2f origin, Vector2f destination) {
        super(origin, destination);
    }

    @Override
    public void update(Set<Entity> entities) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            Sprite sprite = spriteComponent.getSprite();

            calculateMoveToPoint(sprite.getPosition());

            float speed = speedComponent.getSpeed();

            sprite.setRotation(rotateSprite(direction));

            // If the entity goes out of bounds, reset the entity back to it's starting position
            if (passedDestination(sprite.getPosition())) {
                sprite.setPosition(origin);

            } else {
                sprite.move(direction.x * speed, direction.y * speed);
            }
        }
    }
}
