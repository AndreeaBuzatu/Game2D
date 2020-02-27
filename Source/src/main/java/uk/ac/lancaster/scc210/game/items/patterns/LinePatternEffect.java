package uk.ac.lancaster.scc210.game.items.patterns;

import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.StraightLinePattern;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

public class LinePatternEffect extends FiringPatternEffect {
    public LinePatternEffect(Pool pool) {
        super(pool, Time.getSeconds(5));
    }

    @Override
    protected void setPattern(Entity entity) {
        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

        pattern = new StraightLinePattern(pool, entity, spaceShipComponent.getBulletName());
    }
}
