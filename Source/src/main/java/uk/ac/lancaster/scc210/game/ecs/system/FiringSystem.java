package uk.ac.lancaster.scc210.game.ecs.system;

import org.jsfml.graphics.RenderTarget;
import org.jsfml.system.Vector2f;
import org.jsfml.window.Keyboard;
import uk.ac.lancaster.scc210.engine.controller.ControllerButton;
import uk.ac.lancaster.scc210.engine.ecs.Entity;
import uk.ac.lancaster.scc210.engine.ecs.World;
import uk.ac.lancaster.scc210.engine.ecs.system.IterativeSystem;
import uk.ac.lancaster.scc210.engine.pooling.Pool;
import uk.ac.lancaster.scc210.game.ecs.component.AnimationComponent;
import uk.ac.lancaster.scc210.game.ecs.component.BulletComponent;
import uk.ac.lancaster.scc210.game.ecs.component.PlayerComponent;
import uk.ac.lancaster.scc210.game.ecs.component.SpriteComponent;
import uk.ac.lancaster.scc210.game.pooling.BulletPool;

/**
 * System which handles Entities firing. This system has a pool of pre-allocated bullets which it draws from.
 * The system places Bullets (Entities) into the front-middle of the entity.
 */
public class FiringSystem extends IterativeSystem {
    private final int BULLET_Y_PADDING = -20;

    private final Pool bulletPool;

    /**
     * Instantiates a new Iterative system.
     *
     * @param world the world to draw entities from
     */
    public FiringSystem(World world) {
        super(world, SpriteComponent.class, AnimationComponent.class, PlayerComponent.class);

        bulletPool = world.getPool(BulletPool.class);
    }

    @Override
    public void update() {
        for (Entity entity : entities) {
            SpriteComponent entitySprite = (SpriteComponent) entity.findComponent(SpriteComponent.class);

            if (Keyboard.isKeyPressed(Keyboard.Key.SPACE) || ControllerButton.A_BUTTON.isPressed()) {
                Entity bullet = bulletPool.borrowEntity();

                bullet.addComponent(new BulletComponent());

                SpriteComponent bulletSprite = (SpriteComponent) bullet.findComponent(SpriteComponent.class);

                // Find the half-width of the entity sprite and the half-width of the bullet sprite
                float halfWidth = (entitySprite.getSprite().getLocalBounds().width / 2) - (bulletSprite.getSprite().getLocalBounds().width / 2);

                Vector2f middleCentre = new Vector2f(halfWidth, BULLET_Y_PADDING);

                Vector2f bulletPos = entitySprite.getSprite().getTransform().transformPoint(middleCentre);

                bulletSprite.getSprite().setPosition(bulletPos);

                bulletSprite.getSprite().setRotation(entitySprite.getSprite().getRotation());

                world.addEntity(bullet);
            }
        }
    }

    @Override
    public void draw(RenderTarget target) {
    }
}
