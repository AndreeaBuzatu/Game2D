package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

public class FiringSystem extends IterativeSystem {
    private BulletPool bulletPool;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world
     */
    public FiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class);

        bulletPool = (BulletPool) world.getServiceProvider().get(BulletPool.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent entitySprite = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            if (Keyboard.isKeyPressed(Keyboard.Key.SPACE)) {
                Entity bullet = bulletPool.borrowEntity();

                SpriteComponent bulletSprite = (SpriteComponent) bullet.findComponent(SpriteComponent.class);

                bulletSprite.getSprite().setPosition(entitySprite.getSprite().getPosition());

                world.addEntity(bullet);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {

    }
}
