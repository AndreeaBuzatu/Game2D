package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.EnemyComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiringPatternComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

public class EnemyFiringSystem extends IterativeSystem {
    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public EnemyFiringSystem(World world) {
        super(world, SpriteComponent.class, EnemyComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        //elapsedTime = Time.add(elapsedTime, deltaTime);
        for (Entity entity : entities) {
            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            Pattern firingPattern = firingPatternComponent.getPattern();

            firingPattern.setElapsedTime(Time.add(firingPattern.getElapsedTime(), deltaTime));

            System.out.println("Elapsed Time: " + firingPattern.getElapsedTime().asSeconds() + ", " + firingPattern.getFiringGap().asSeconds());

            if (firingPattern.getElapsedTime().asSeconds() > firingPattern.getFiringGap().asSeconds()) {
                System.out.println("Hello");

                if (entity.hasComponent(SpaceShipComponent.class)) {
                    SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

                    spaceShipComponent.playFiringSound();
                }

                world.addEntities(firingPatternComponent.getPattern().create());

                firingPattern.setElapsedTime(Time.ZERO);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
