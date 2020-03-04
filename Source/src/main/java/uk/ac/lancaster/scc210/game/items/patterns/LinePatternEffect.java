package uk.ac.lancaster.scc210.game.items.patterns;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.StraightLineBulletPattern;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

/**
 * The type Line pattern effect.
 */
public class LinePatternEffect extends FiringPatternEffect {
    /**
     * Instantiates a new Line pattern effect.
     *
     * @param pool the pool
     */
    public LinePatternEffect(Pool pool) {
        super(pool, Time.getSeconds(5));
    }

    @Override
    protected void setPattern(Entity entity) {
        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

        pattern = new StraightLineBulletPattern(pool, entity, spaceShipComponent.getBulletName());
    }
}
