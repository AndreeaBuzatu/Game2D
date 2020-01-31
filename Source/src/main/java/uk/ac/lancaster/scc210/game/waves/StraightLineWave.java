package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

public class StraightLineWave extends Wave {
    private final Clock clock;

    public StraightLineWave(Vector2f origin, Vector2f destination) {
        super(origin, destination);

        System.out.println("Origin: " + origin);

        clock = new Clock();
    }

    @Override
    public void update(Entity entity) {
        SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

        SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

        Sprite sprite = spriteComponent.getSprite();

        float speed = speedComponent.getSpeed();

        Vector2f spritePos = sprite.getPosition();

        System.out.println("Position: " + sprite.getPosition());

        calculateMoveToPoint();

        // If the entity goes out of bounds, reset the entity back to it's starting position
        if (pastDestination(sprite.getPosition())) {
            //sprite.setPosition(origin);

            clock.restart();

        } else {
            ///System.out.println("Direction: " + direction);
            sprite.move(direction.x * speed, direction.y * speed);
            //sprite.move(1 * speed, 1 * speed);
        }
    }
}
