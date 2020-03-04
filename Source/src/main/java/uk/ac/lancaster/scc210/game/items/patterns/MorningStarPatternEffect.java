package uk.ac.lancaster.scc210.game.items.patterns;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.MorningStarBulletPattern;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

/**
 * The type Morning star pattern effect.
 */
public class MorningStarPatternEffect extends FiringPatternEffect {
    /**
     * Instantiates a new Morning star pattern effect.
     *
     * @param pool the pool
     */
    public MorningStarPatternEffect(Pool pool) {
        super(pool, Time.getSeconds(3));
    }

    @Override
    protected void setPattern(Entity entity) {
        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

        pattern = new MorningStarBulletPattern(pool, entity, spaceShipComponent.getBulletName());
    }
}