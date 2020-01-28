package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.WindowSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.component.PooledComponent;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;

/**
 * System used to prevent an entity from going off screen
 */
public class WindowBoundsSystem extends IterativeSystem {
    private final WindowSize windowSize;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public WindowBoundsSystem(World world) {
        super(world, SpriteComponent.class);

        windowSize = (WindowSize) world.getServiceProvider().get(WindowSize.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            if (windowSize.outOfBounds(spriteComponent.getSprite())) {
                world.removeEntity(entity);

                if (entity.hasComponent(PooledComponent.class)) {
                    PooledComponent pooledComponent = (PooledComponent) entity.findComponent(PooledComponent.class);

                    Pool entityPool = world.getPool(pooledComponent.getPoolClass());

                    entityPool.returnEntity(entity);
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
