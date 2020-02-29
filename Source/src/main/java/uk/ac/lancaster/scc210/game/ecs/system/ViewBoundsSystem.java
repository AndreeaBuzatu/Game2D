package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Time;
import uk.ac.lancaster.scc210.engine.ViewSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.FiredComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

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
        super(world, SpriteComponent.class);

        viewSize = (ViewSize) world.getServiceProvider().get(ViewSize.class);
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
                    world.removeEntity(entity);

                    continue;
                }

                // Remove any bullets from screen
                if (entity.hasComponent(BulletComponent.class)) {
                    world.removeEntity(entity);

                    if (entity.hasComponent(PooledComponent.class)) {
                        PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                        Pool entityPool = world.getPool(pooledComponent.getPoolClass());

                        entityPool.returnEntity(entity);
                    }
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
