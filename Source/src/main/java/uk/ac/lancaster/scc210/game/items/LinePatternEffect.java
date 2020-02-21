package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.Pattern;
import uk.ac.lancaster.scc210.game.bullets.patterns.StarPattern;
import uk.ac.lancaster.scc210.game.bullets.patterns.StraightLinePattern;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

public class LinePatternEffect implements ItemEffect {
    private Pattern oldPatern;

    private Pool pool;

    public LinePatternEffect (Pool pool)
    {
        this.pool = pool;
    }
    @Override
    public void react(Entity entity) {

        if (entity.hasComponent(FiringPatternComponent.class ) && entity.hasComponent(SpaceShipComponent.class)){


            SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            if(oldPatern == null) {

                oldPatern = firingPatternComponent.getPattern();
            }

            firingPatternComponent.setPattern(new StraightLinePattern(pool, entity, spaceShipComponent.getBulletName()));

        }

    }

    @Override
    public void reset(Entity entity) {
        if (entity.hasComponent(FiringPatternComponent.class) && entity.hasComponent(SpaceShipComponent.class)) {


            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            firingPatternComponent.setPattern(oldPatern);

        }
    }
}
