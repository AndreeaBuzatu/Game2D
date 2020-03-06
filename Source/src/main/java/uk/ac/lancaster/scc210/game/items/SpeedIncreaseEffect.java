package uk.ac.lancaster.scc210.game.items;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpeedComponent;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

/**
 * The type Speed increase effect.
 */
public class SpeedIncreaseEffect extends TimedItemEffect {
    private int oldSpeed;

    private Time oldGap;

    /**
     * Instantiates a new Speed increase effect.
     */
    public SpeedIncreaseEffect() {
        super(Time.getSeconds(5));

        // Entities shouldn't have a speed of -1
        oldSpeed = -1;

        oldGap = null;
    }

    @Override
    public void react(Entity entity) {
        super.react(entity);

        if (entity.hasComponent(SpeedComponent.class)) {
            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);

            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            Pattern pattern = firingPatternComponent.getPattern();

            if (oldSpeed == -1) {
                oldSpeed = speedComponent.getSpeed();
            }

            if (oldGap == null) {
                oldGap = pattern.getFiringGap();
            }

            pattern.setFiringGap(Time.div(pattern.getFiringGap(), 4));

            speedComponent.setSpeed(20);
        }
    }

    @Override
    public void reset(Entity entity) {
        if (entity.hasComponent(SpeedComponent.class)) {
            SpeedComponent speedComponent = (SpeedComponent) entity.findComponent(SpeedComponent.class);
            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            // Let's not assign a null value for time shall we
            if (oldGap != null) {
                firingPatternComponent.getPattern().setFiringGap(oldGap);

            } else {
                // Worst case scenario: 1 second gap.
                firingPatternComponent.getPattern().setFiringGap(Time.getSeconds(1));
            }

            speedComponent.setSpeed(oldSpeed);
        }
    }
}
