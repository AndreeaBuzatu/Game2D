package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.graphics.Sprite;
import org.jsfml.system.Clock;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

import java.util.Set;

public class SineWave extends Wave {
    private final Clock clock;

    private final float waveAmp;

    public SineWave(Vector2f origin, Vector2f destination) {
        super(origin, destination);

        clock = new Clock();

        waveAmp = 10f;
    }

    @Override
    public void update(Set<Entity> entities) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            Sprite sprite = spriteComponent.getSprite();

            float speed = speedComponent.getSpeed();

            calculateMoveToPoint(sprite.getPosition());

            double waveAngle = clock.getElapsedTime().asSeconds() * Math.PI * 2;

            Vector2f perpendicular = new Vector2f(-direction.y, direction.x);

            double waveCurve = Math.sin(waveAngle) * waveAmp;

            Vector2f wave = new Vector2f((float) (perpendicular.x * waveCurve), (float) (perpendicular.y * waveCurve));

            sprite.setRotation(rotateSprite(direction));

            // If the entity goes out of bounds, reset the entity back to it's starting position
            if (passedDestination(sprite.getPosition())) {
                sprite.setPosition(origin);

                clock.restart();

            } else {
                sprite.move((direction.x * speed) + wave.x, (direction.y * speed) + wave.y);
            }
        }
    }
}
