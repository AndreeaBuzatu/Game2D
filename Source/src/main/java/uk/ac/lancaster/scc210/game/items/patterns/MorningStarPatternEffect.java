package uk.ac.lancaster.scc210.game.items.patterns;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.MorningStarPattern;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

public class MorningStarPatternEffect extends FiringPatternEffect {
    public MorningStarPatternEffect(Pool pool) {
        super(pool, Time.getSeconds(3));
    }

    @Override
    protected void setPattern(Entity entity) {
        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

        pattern = new MorningStarPattern(pool, entity, spaceShipComponent.getBulletName());
    }
}