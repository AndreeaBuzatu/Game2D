package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.content.SoundManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;
import uk.ac.lancaster.scc210.game.patterns.Pattern;

import java.util.Collection;

public class EnemyFiringSystem extends IterativeSystem {
    private final SoundManager soundManager;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public EnemyFiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class, EnemyComponent.class, FiringPatternComponent.class);

        soundManager = (SoundManager) world.getServiceProvider().get(SoundManager.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesFor(SpriteComponent.class, AnimationComponent.class, EnemyComponent.class, FiringPatternComponent.class);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesFor(SpriteComponent.class, AnimationComponent.class, EnemyComponent.class, FiringPatternComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {

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

                    //soundManager.playSound(spaceShipComponent.getFiringSound());
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
