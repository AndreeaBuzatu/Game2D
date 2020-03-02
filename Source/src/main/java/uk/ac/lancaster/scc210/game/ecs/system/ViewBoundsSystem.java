package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.*;

import java.util.Collection;

/**
 * System used to prevent an entity from going off screen
 */
public class ViewBoundsSystem extends IterativeSystem {
    private final ViewSize viewSize;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public ViewBoundsSystem(World world) {
        super(world);

        viewSize = (ViewSize) world.getServiceProvider().get(ViewSize.class);

        entities = world.getEntitiesWithAny(PlayerComponent.class, FiredComponent.class);
    }

    @Override
    public void entityAdded(Entity entity) {
        entities = world.getEntitiesWithAny(PlayerComponent.class, FiredComponent.class);
    }

    @Override
    public void entitiesAdded(Collection<? extends Entity> entities) {
        this.entities = world.getEntitiesWithAny(PlayerComponent.class, FiredComponent.class);
    }

    @Override
    public void entityRemoved(Entity entity) {
        entities = world.getEntitiesWithAny(PlayerComponent.class, FiredComponent.class);
    }

    @Override
    public void update(Time deltaTime) {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            // Player sticks within the window, bullets are removed, wave components can go in and out bounds
            if (viewSize.outOfBounds(spriteComponent.getSprite())) {
                // Keep the player within the bounds of the window
                if (entity.hasComponent(PlayerComponent.class)) {
                    viewSize.resetSprite(spriteComponent.getSprite());

                    continue;
                }

                // Remove any enemy spaceships which leave the screen but aren't in a wave (i.e. fired by another ship) and is not a bullet
                // Bullet entities (which also have FiredComponent) might contain a Pool which needs special handling
                if (entity.hasComponent(FiredComponent.class) && !entity.hasComponent(BulletComponent.class)) {
                    entity.addComponent(new OutOfBoundsComponent());

                    world.removeEntity(entity);

                    continue;
                }

                // Remove any bullets from screen
                if (entity.hasComponent(BulletComponent.class) && entity.hasComponent(PooledComponent.class)) {
                    PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                    Pool entityPool = world.getPool(pooledComponent.getPoolClass());

                    entityPool.returnEntity(entity);

                    world.removeEntity(entity);
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
