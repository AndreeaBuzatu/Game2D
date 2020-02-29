package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

public class EnemyFiringSystem extends IterativeSystem {
    private final Time FIRING_GAP = Time.getSeconds(1);

    private Time elapsedTime;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public EnemyFiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class, EnemyComponent.class, FiringPatternComponent.class);

        elapsedTime = Time.ZERO;
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            // Return early if the player can't fire
            EnemyComponent enemyComponent = (EnemyComponent) entity.findComponent(EnemyComponent.class);

            FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

            if (!enemyComponent.canFire()) {
                return;
            }

            Pattern firingPattern = firingPatternComponent.getPattern();

            firingPattern.setElapsedTime(Time.add(firingPattern.getElapsedTime(), deltaTime));

            if (firingPattern.getElapsedTime().asSeconds() > firingPattern.getFiringGap().asSeconds()) {
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
