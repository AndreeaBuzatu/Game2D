package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;

import java.util.Set;

public class BulletCollisionSystem extends IterativeSystem {
    private Set<Entity> transformables;
    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public BulletCollisionSystem(World world) {
        super(world, BulletComponent.class);

        transformables = world.getEntitiesFor(TransformableComponent.class, FlashComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        transformables = world.getEntitiesFor(TransformableComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            OrientatedBoxComponent entityOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            BulletComponent bulletComponent = (BulletComponent) entity.findComponent(BulletComponent.class);

            Entity bulletCreator = bulletComponent.getCreator();

            if (bulletComponent.getBulletEffect().isDead()) {
                bulletComponent.getBulletEffect().reset();

                bulletComponent.setBulletEffectToDefault();

                System.out.println("Is dead");

            } else {
                bulletComponent.getBulletEffect().update(deltaTime);
            }

            for (Entity transformable : transformables) {
                OrientatedBoxComponent transformableOrientatedBox = (OrientatedBoxComponent) transformable.findComponent(OrientatedBoxComponent.class);

                boolean colliding = OrientatedBox.areColliding(entityOrientedBox.getOrientatedBox(), transformableOrientatedBox.getOrientatedBox());

                // Enemies are impervious to each others bullets
                boolean bothEnemies = bulletCreator.hasComponent(EnemyComponent.class) && transformable.hasComponent(EnemyComponent.class);

                boolean isItem = transformable.hasComponent(ItemEffectsComponent.class);

                // Just in case the bullet creator hits the current entity. (Compare references).
                boolean sameEntity = bulletCreator == transformable;

                if (colliding && !bothEnemies && !isItem && !sameEntity) {
                    PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                    FlashComponent flashComponent = (FlashComponent) transformable.findComponent(FlashComponent.class);

                    flashComponent.flash(deltaTime);

                    bulletComponent.getBulletEffect().react(transformable);

                    if (transformable.hasComponent(SpaceShipComponent.class)) {
                        SpaceShipComponent spaceShipComponent = (SpaceShipComponent) transformable.findComponent(SpaceShipComponent.class);

                        spaceShipComponent.playHitSound();
                    }

                    // Return the bullet back to the pool and remove it from the world
                    world.getPool(pooledComponent.getPoolClass()).returnEntity(entity);

                    world.removeEntity(entity);
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
