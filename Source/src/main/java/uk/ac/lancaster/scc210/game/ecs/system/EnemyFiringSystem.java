package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;

public class EnemyFiringSystem extends IterativeSystem {
    private final Time FIRING_GAP = Time.getSeconds(1);

    private Time elapsedTime;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public EnemyFiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class, EnemyComponent.class);

        elapsedTime = Time.ZERO;
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            // Return early if the player can't fire
            EnemyComponent enemyComponent = (EnemyComponent) entity.findComponent(EnemyComponent.class);

            if (!enemyComponent.canFire()) {
                return;
            }

            elapsedTime = Time.add(elapsedTime, deltaTime);

            if (elapsedTime.asSeconds() > FIRING_GAP.asSeconds()) {
                FiringPatternComponent firingPatternComponent = (FiringPatternComponent) entity.findComponent(FiringPatternComponent.class);

                if (entity.hasComponent(SpaceShipComponent.class)) {
                    SpaceShipComponent spaceShipComponent = (SpaceShipComponent) entity.findComponent(SpaceShipComponent.class);

                    spaceShipComponent.playFiringSound();
                }

                world.addEntities(firingPatternComponent.getPattern().create());

                elapsedTime = Time.ZERO;
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
