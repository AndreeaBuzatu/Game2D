package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;

/**
 * The type Speed increase effect.
 */
public class SpeedIncreaseEffect extends TimedItemEffect {
    private int oldSpeed;

    /**
     * Instantiates a new Speed increase effect.
     */
    public SpeedIncreaseEffect() {
        super(Time.getSeconds(5));

        // Entities shouldn't have a speed of -1
        oldSpeed = -1;
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(SpeedComponent.class)) {
            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            if (oldSpeed == -1) {
                oldSpeed = speedComponent.getSpeed();
            }

            speedComponent.setSpeed(20);
        }
    }

    @Override
    public void reset(Entity entity) {
        if (entity.hasComponent(SpeedComponent.class)) {
            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            speedComponent.setSpeed(oldSpeed);
        }
    }
}
