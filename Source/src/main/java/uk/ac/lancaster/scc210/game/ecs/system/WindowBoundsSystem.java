package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import uk.ac.lancaster.scc210.engine.WindowSize;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

public class WindowBoundsSystem extends IterativeSystem {
    private WindowSize windowSize;

    private BulletPool bulletPool;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world      the world
     */
    public WindowBoundsSystem(World world) {
        super(world, SpriteComponent.class);

        windowSize = (WindowSize) world.getServiceProvider().get(WindowSize.class);

        bulletPool = (BulletPool) world.getServiceProvider().get(BulletPool.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent spriteComponent = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            if (windowSize.outOfBounds(spriteComponent.getSprite())) {
                world.removeEntity(entity);

                // TODO: Abstract this so that it works with any pooled entity
                if (entity.hasComponent(BulletComponent.class)) {
                    bulletPool.returnEntity(entity);
                }
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
