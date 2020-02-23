package uk.ac.lancaster.scc210.game.items;

import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.bullets.patterns.MorningStarPattern;
import uk.ac.lancaster.scc210.game.bullets.patterns.Pattern;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;

public class MorningStarPatternEffect implements ItemEffect {
    private Pattern oldPattern;

    private Pool pool;

    public MorningStarPatternEffect(Pool pool) {
        this.pool = pool;
    }

    @Override
    public void react(Entity entity) {


        if (entity.hasComponent(FiringPatternComponent.class) && entity.hasComponent(SpaceShipComponent.class)) {


            SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            if (oldPattern == null) {
                oldPattern = firingPatternComponent.getPattern();
            }

            firingPatternComponent.setPattern(new MorningStarPattern(pool, entity, spaceShipComponent.getBulletName()));

        }
    }

    @Override
    public void reset(Entity entity) {
        if (entity.hasComponent(FiringPatternComponent.class) && entity.hasComponent(SpaceShipComponent.class)) {


            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            System.out.println("reset pattern");

            firingPatternComponent.setPattern(oldPattern);

        }
    }
}