package uk.ac.lancaster.scc210.game.items.patterns;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.StarBulletPattern;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

public class StarPatternEffect extends FiringPatternEffect {
    public StarPatternEffect(Pool pool) {
        super(pool, Time.getSeconds(4));
    }

    @Override
    protected void setPattern(Entity entity) {
        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

        pattern = new StarBulletPattern(pool, entity, spaceShipComponent.getBulletName());
    }
}
