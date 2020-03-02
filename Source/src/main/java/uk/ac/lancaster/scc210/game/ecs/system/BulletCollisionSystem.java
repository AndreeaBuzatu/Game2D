package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.collision.Cell;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.collision.UniformGrid;
import uk.ac.lancaster.scc210.engine.content.SoundManager;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class BulletCollisionSystem extends IterativeSystem {
    private final SoundManager soundManager;

    private final UniformGrid uniformGrid;

    private Set<Entity> transformables;
    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world containing entities to use
     */
    public BulletCollisionSystem(World world) {
        super(world, BulletComponent.class);

        transformables = world.getEntitiesFor(TransformableComponent.class, FlashComponent.class);

        soundManager = (SoundManager) world.getServiceProvider().get(SoundManager.class);

        uniformGrid = new UniformGrid((ViewSize) world.getServiceProvider().get(ViewSize.class));
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesFor(BulletComponent.class);

        transformables = world.getEntitiesFor(TransformableComponent.class, FlashComponent.class);

        //uniformGrid.addEntity(entity);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesFor(BulletComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {

    }

    @Override
    public void update(Time deltaTime) {
        for (Entity transformable : transformables) {
            uniformGrid.addEntity(transformable);
        }

        for (Entity bullets : entities) {
            uniformGrid.addEntity(bullets);
        }

        for (List<Cell> cells : uniformGrid.getGrid()) {
            for (Cell cell : cells) {
                if (!cell.getEntities().isEmpty()) {
                    Entity[] collided = cell.checkCollision();

                    if (collided != null) {
                        Entity bullet = findBulletEntity(collided);

                        Entity transformable = findTransformableEntity(collided);

                        if (bullet != null && transformable != null) {
                            boolean isItem = transformable.hasComponent(ItemEffectsComponent.class);

                            Entity bulletCreator = ((BulletComponent) bullet.findComponent(BulletComponent.class)).getCreator();

                            // Enemies are impervious to each others bullets
                            boolean bothEnemies = bulletCreator.hasComponent(EnemyComponent.class) && transformable.hasComponent(EnemyComponent.class);

                            // Just in case the bullet creator hits the current entity. (Compare references).
                            boolean sameEntity = bulletCreator == transformable;

                            if (isItem || bothEnemies || sameEntity) {
                                continue;
                            }

                            PooledComponent pooledComponent = (PooledComponent) bullet.findComponent(PooledComponent.class);

                            FlashComponent flashComponent = (FlashComponent) transformable.findComponent(FlashComponent.class);

                            flashComponent.flash(deltaTime);

                            if (bulletCreator.hasComponent(PlayerComponent.class)) {
                                PlayerComponent playerComponent = (PlayerComponent) bulletCreator.findComponent(PlayerComponent.class);

                                playerComponent.getBulletEffect().react(transformable);
                            }

                            if (transformable.hasComponent(SpaceShipComponent.class)) {
                                SpaceShipComponent spaceShipComponent = (SpaceShipComponent) transformable.findComponent(SpaceShipComponent.class);

                                soundManager.playSound(spaceShipComponent.getHitSound());

                                spaceShipComponent.getBulletEffect().react(transformable);
                            }

                            // Return the bullet back to the pool and remove it from the world
                            world.getPool(pooledComponent.getPoolClass()).returnEntity(bullet);

                            world.removeEntity(bullet);
                        }
                    }
                }
            }
        }

        uniformGrid.clear();
        /*
        for (Entity entity : entities) {
            OrientatedBoxComponent entityOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            BulletComponent bulletComponent = (BulletComponent) entity.findComponent(BulletComponent.class);

            uniformGrid.addEntity(entity);

            Entity bulletCreator = bulletComponent.getCreator();

            for (Entity transformable : transformables) {
                // Enemies are impervious to each others bullets
                boolean bothEnemies = bulletCreator.hasComponent(EnemyComponent.class) && transformable.hasComponent(EnemyComponent.class);

                // Just in case the bullet creator hits the current entity. (Compare references).
                boolean sameEntity = bulletCreator == transformable;

                boolean isItem = transformable.hasComponent(ItemEffectsComponent.class);

                if (bothEnemies || sameEntity || isItem) {
                    continue;
                }

                uniformGrid.addEntity(transformable);

                uniformGrid.clear();
            }
        }
         */
    }

    @Override
    public void draw(RenderTarget target) {

    }

    private Entity findBulletEntity(Entity[] entity) {
        if (entity[0].hasComponent(BulletComponent.class)) {
            return entity[0];

        } else if (entity[1].hasComponent(BulletComponent.class)) {
            return entity[1];
        }

        return null;
    }

    private Entity findTransformableEntity(Entity[] entity) {
        if (entity[0].hasComponent(TransformableComponent.class)) {
            return entity[0];

        } else if (entity[1].hasComponent(TransformableComponent.class)) {
            return entity[1];
        }

        return null;
    }
}
