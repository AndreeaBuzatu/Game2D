package uk.ac.lancaster.scc210.game.items.patterns;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.items.TimedItemEffect;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

/**
 * The type Firing pattern effect.
 */
public abstract class FiringPatternEffect extends TimedItemEffect {
    /**
     * The Pool.
     */
    final Pool pool;

    /**
     * The Pattern.
     */
    Pattern pattern;

    private Pattern oldPattern;

    /**
     * Instantiates a new Firing pattern effect.
     *
     * @param pool     the pool
     * @param duration the duration
     */
    FiringPatternEffect(Pool pool, Time duration) {
        super(duration);

        this.pool = pool;
    }

    /**
     * Describes what should happen to the entity when it collides with an item
     *
     * @param entity the entity to react to
     */
    @Override
    public void react(Entity entity) {
        if (entity.hasComponent(FiringPatternComponent.class) && entity.hasComponent(SpaceShipComponent.class)) {
            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            if (oldPattern == null) {
                oldPattern = firingPatternComponent.getPattern();
            }

            if (pattern == null) {
                setPattern(entity);
            }

            firingPatternComponent.setPattern(pattern);
        }
    }

    /**
     * At certain stages (e.g. at the end of a level) the item effects are reset to the default. This should be used
     * to reset.
     *
     * @param entity the entity to reset
     */
    @Override
    public void reset(Entity entity) {
        if (entity.hasComponent(FiringPatternComponent.class) && entity.hasComponent(SpaceShipComponent.class)) {
            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            firingPatternComponent.setPattern(oldPattern);
        }
    }

    /**
     * Sets pattern.
     *
     * @param entity the entity
     */
    protected abstract void setPattern(Entity entity);
}
