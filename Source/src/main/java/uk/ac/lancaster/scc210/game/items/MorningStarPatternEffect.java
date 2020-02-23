package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.MorningStarPattern;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

public class MorningStarPatternEffect extends FiringPatternEffect {
    public MorningStarPatternEffect(Pool pool) {
        super(pool);
    }

    @Override
    void setPattern(Entity entity) {
        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

        pattern = new MorningStarPattern(pool, entity, spaceShipComponent.getBulletName());
    }
}