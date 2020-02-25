package uk.ac.lancaster.scc210.game.waves;

import org.jsfml.graphics.Transformable;
import org.jsfml.system.Time;
import org.jsfml.system.Vector2f;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;

import java.util.Set;

public class SineWave extends Wave {
    private final float waveAmp;

    private Time elapsedTime;

    public SineWave(Vector2f origin, Vector2f destination) {
        super(origin, destination);

        waveAmp = 5f;

        elapsedTime = Time.ZERO;
    }

    @Override
    public void update(Set<Entity> entities, Time deltaTime) {
        elapsedTime = Time.add(elapsedTime, deltaTime);

        double waveAngle = elapsedTime.asSeconds() * Math.PI;

        for (Entity entity : entities) {
            TransformableComponent transformableComponent = (TransformableComponent) entity.findComponent(TransformableComponent.class);

            Transformable transformable = transformableComponent.getTransformable();

            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            float speed = speedComponent.getSpeed();

            calculateMoveToPoint(transformable.getPosition());

            Vector2f perpendicular = new Vector2f(-direction.y, direction.x);

            double waveCurve = Math.sin(waveAngle) * waveAmp;

            Vector2f wave = new Vector2f((float) (perpendicular.x * waveCurve), (float) (perpendicular.y * waveCurve));

            transformable.setRotation(rotateSprite(waveAngle));

            // If the entity goes out of bounds, reset the entity back to it's starting position
            if (passedDestination(transformable.getPosition())) {
                transformable.setPosition(origin);

            } else {
                transformable.move((direction.x * speed) + wave.x, (direction.y * speed) + wave.y);
            }
        }
    }
}
