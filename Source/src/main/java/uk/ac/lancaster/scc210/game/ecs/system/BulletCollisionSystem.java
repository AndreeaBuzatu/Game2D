package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.FloatRect;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.collision.OrientatedBox;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AsteroidComponent;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.ecs.component.TransformableComponent;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.OrientatedBoxComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpaceShipComponent;
import uk.ac.lancaster.scc210.game.ecs.component.WaveComponent;

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

            OrientatedBoxComponent entityOrientedBox = (OrientatedBoxComponent) entity.findComponent(OrientatedBoxComponent.class);

            BulletComponent bulletComponent = (BulletComponent) entity.findComponent(BulletComponent.class);

            Entity bulletCreator = bulletComponent.getCreator();

            for (Entity transformable : transformables) {
                OrientatedBoxComponent spaceShipOrientedBox = (OrientatedBoxComponent) spaceShip.findComponent(OrientatedBoxComponent.class);

                boolean colliding = OrientatedBox.areColliding(entityOrientedBox.getOrientatedBox(), spaceShipOrientedBox.getOrientatedBox());

                boolean sameWave = false;

                /*
                TODO: Change this to use EnemyComponent instead
                If the creator of the bullet has a wave and the spaceShip belongs to a wave, find if the creator
                of the bullet and the current spaceship belong to the same wave. If so, prevent colliding with spaceships
                of the same wave.
                 */
                if (bulletComponent.getCreator().hasComponent(WaveComponent.class) && spaceShip.hasComponent(WaveComponent.class)) {
                    WaveComponent bulletWaveComponent = (WaveComponent) entity.findComponent(WaveComponent.class);

                    WaveComponent spaceShipWaveComponent = (WaveComponent) spaceShip.findComponent(WaveComponent.class);

                    sameWave = bulletWaveComponent.getWave() == spaceShipWaveComponent.getWave();
                }

                if (colliding && bulletCreator != spaceShip && !sameWave) {
                    PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                    world.removeEntity(spaceShip);

                    // Return the bullet back to the pool and remove it from the world
                    world.getPool(pooledComponent.getPoolClass()).returnEntity(entity);

                    world.removeEntity(entity);
                }

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
