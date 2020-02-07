package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;

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

        transformables = world.getEntitiesFor(TransformableComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        super.entityAdded(entity);

        transformables = world.getEntitiesFor(TransformableComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            SpriteComponent bulletSpriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            for (Entity transformable : transformables) {
                //SpriteComponent spaceShipSprite = (SpriteComponent) spaceShip.findComponent(SpriteComponent.class);
                FloatRect globalBounds = null;

                // Unfortunately Transformable does not contain getGlobalBounds() nor are there any higher abstractions to find the bounds...
                if (transformable.hasComponent(SpriteComponent.class)) {
                    globalBounds = ((SpriteComponent) transformable.findComponent(SpriteComponent.class)).getSprite().getGlobalBounds();

                } else if (transformable.hasComponent(AsteroidComponent.class)) {
                    globalBounds = ((AsteroidComponent) transformable.findComponent(AsteroidComponent.class)).getCircle().getGlobalBounds();
                }

                // Only attempt to handle collision if global bounds
                if (globalBounds != null) {
                    boolean collision = globalBounds.contains(bulletSpriteComponent.getSprite().getPosition());

                    if (collision) {
                        PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                        world.removeEntity(transformable);

                        // Return the bullet back to the pool and remove it from the world
                        world.getPool(pooledComponent.getPoolClass()).returnEntity(entity);

                        world.removeEntity(entity);
                    }
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
